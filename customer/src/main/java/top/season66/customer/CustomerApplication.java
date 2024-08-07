package top.season66.customer;

//import com.fasterxml.jackson.datatype.jodamoney.JodaMoneyModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class CustomerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(CustomerApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder
//                .setConnectTimeout(Duration.ofSeconds(1)) // 连接超时
//                .setReadTimeout(Duration.ofSeconds(5)) // 读取超时
//                .build();
//    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .requestFactory(this::requestFactory)
                .setConnectTimeout(Duration.ofSeconds(1)) // 连接超时
                .setReadTimeout(Duration.ofSeconds(5)) // 读取超时
                .build();
    }

    /**
     * 如果命令行里给了wait选项，返回0，否则返回1
     */
    @Bean
    public ExitCodeGenerator waitExitCodeGenerator(ApplicationArguments args) {
        return () -> (args.containsOption("wait") ? 0 : 1);
    }
    @Bean
    public ClientHttpRequestFactory requestFactory() {
        HttpClientBuilder builder = HttpClientBuilder.create()
                .disableAutomaticRetries() // 默认重试是开启的，建议关闭
                .evictIdleConnections(10, TimeUnit.MINUTES) // 空闲连接10分钟关闭
                .setConnectionTimeToLive(30, TimeUnit.SECONDS) // 连接的TTLS时间
                .setMaxConnTotal(200) // 连接池大小
                .setMaxConnPerRoute(20); // 每个主机的最大连接数

        // 调整Keep-Alive策略
        builder.setKeepAliveStrategy((response, context) ->
                Arrays.asList(response.getHeaders(HTTP.CONN_KEEP_ALIVE))
                        .stream()
                        .filter(h -> StringUtils.equalsIgnoreCase(h.getName(), "timeout")
                                && StringUtils.isNumeric(h.getValue()))
                        .findFirst()
                        .map(h -> NumberUtils.toLong(h.getValue(), 300L))
                        .orElse(300L) * 1000);

        SSLContext sslContext = null;
        try {
            sslContext = SSLContextBuilder.create()
                    // 放过所有证书校验
                    .loadTrustMaterial(null, (certificate, authType) -> true)
                    .build();
        } catch (GeneralSecurityException e) {
            log.error("Can NOT create SSLContext", e);
        }
        if (sslContext != null) {
            builder.setSSLContext(sslContext) // 设置SSLContext
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE); // 不校验主机名
        }

        return new HttpComponentsClientHttpRequestFactory(builder.build());
    }
}
