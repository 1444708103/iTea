package top.season66.customer;

import top.season66.customer.model.NewOrderForm;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;

@Component
@Order(5)
@Setter
@Slf4j
public class OrderRunner implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${itea.url}")
    private String itea;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        callWithEntity();
        callForObject();
    }

    protected String callForObject() {
        NewOrderForm form = NewOrderForm.builder()
                .itemIdList(Arrays.asList("1"))
                .discount(90).build();
        String response = restTemplate.postForObject(itea + "/order", form, String.class);
        log.info("下订单：{}", response);
        return response;
    }

    protected ResponseEntity<String> callForEntity() {
        NewOrderForm form = NewOrderForm.builder()
                .itemIdList(Arrays.asList("1"))
                .discount(90).build();

        ResponseEntity<String> response = restTemplate.postForEntity(itea + "/order",
                form, String.class);
        log.info("HTTP Status: {}, Headers: ", response.getStatusCode());
        response.getHeaders().entrySet().forEach(e -> log.info("{}: {}", e.getKey(), e.getValue()));
        log.info("Body: {}", response.getBody());
        return response;
    }

    protected ResponseEntity<String> callWithEntity() {
        NewOrderForm form = NewOrderForm.builder()
                .itemIdList(Arrays.asList("1"))
                .discount(90).build();
        URI uri = UriComponentsBuilder.fromUriString(itea + "/order").build().toUri();
        RequestEntity<NewOrderForm> request = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(form);
        ResponseEntity<String> response = restTemplate.postForEntity(itea + "/order",
                request, String.class);
        log.info("HTTP Status: {}, Headers: ", response.getStatusCode());
        response.getHeaders().entrySet().forEach(e -> log.info("{}: {}", e.getKey(), e.getValue()));
        log.info("Body: {}", response.getBody());
        return response;
    }
}
