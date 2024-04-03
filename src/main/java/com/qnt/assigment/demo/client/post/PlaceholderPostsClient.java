package com.qnt.assigment.demo.client.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class PlaceholderPostsClient {
    private final RestTemplate restTemplate;
    private final String hostUrl;
    private final String clientDetailUrl;

    public PlaceholderPostsClient(RestTemplate restTemplate,
            @Value("${jsonplaceholder.api.host}") String hostUrl,
            @Value("${jsonplaceholder.api.service.posts-details}") String clientDetailUrl) {

        this.restTemplate = restTemplate;
        this.hostUrl = hostUrl;
        this.clientDetailUrl = clientDetailUrl;
    }

    public PlaceholderPostsDetail getPostById(Long id) {
        log.info("getDetail({})", id);

        String uri = hostUrl + clientDetailUrl;
        log.info("uri: {}", uri);

        return restTemplate.getForEntity(uri, PlaceholderPostsDetail.class, id).getBody();
    }

}