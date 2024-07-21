package top.season66.itea;

import org.springframework.boot.actuate.info.SimpleInfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.actuate.info.InfoContributor;

@Configuration
public class InfoContributorConfig {
    //向端点提供一些信息
    @Bean
    public InfoContributor simpleInfoContributor() {
        return new SimpleInfoContributor("simple", "Hello world");
    }
}
