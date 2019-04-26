package jp.co.dyn.api.repository;

import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import jp.co.dyn.api.repository.client.CustomClientHttpRequestInterceptor;
import jp.co.dyn.api.repository.client.GmoClient;
import jp.co.dyn.api.repository.client.JBIClient;
import jp.co.dyn.api.repository.client.ZeusClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(@Value("${client.gmo.connectionTimeout:60}") Long connectionTimeout,
                                     @Value("${client.gmo.readTimeout:120}") Long readTimeout) {
        return new RestTemplateBuilder().additionalInterceptors(new CustomClientHttpRequestInterceptor())
                .setConnectTimeout(Duration.ofSeconds(connectionTimeout))
                .setReadTimeout(Duration.ofSeconds(readTimeout))
                .build();
    }

    @Bean
    public GmoClient gmoClient(@Value("${client.gmo.baseUrl:http://localhost:7070}") String baseUrl) {
        return new GmoClient(baseUrl, restTemplate(null, null));
    }

    @Bean
    public ZeusClient zeusClient(@Value("${client.zeus.baseUrl:http://localhost:7070}") String baseUrl) {
        return new ZeusClient(baseUrl, restTemplate(null, null));
    }

    @Bean
    public JBIClient jbiClient(@Value("${client.jbi.baseUrl:http://localhost:7070}") String baseUrl) {
        JBIClient client = new JBIClient();
        client.setBaseUrl(baseUrl);
        return client;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson() {
        return (builder) -> builder.modules(new JaxbAnnotationModule());
    }
}
