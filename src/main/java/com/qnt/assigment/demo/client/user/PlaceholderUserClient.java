package com.qnt.assigment.demo.client.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class PlaceholderUserClient {
    private final RestTemplate restTemplate;
    private final String hostUrl;
    private final String clientDetailUrl;

    public PlaceholderUserClient (RestTemplate restTemplate,
                                  @Value("${jsonplaceholder.api.host}") String hostUrl,
                                  @Value("${jsonplaceholder.api.service.users-details}") String clientDetailUrl) {

        this.restTemplate = restTemplate;
        this.hostUrl = hostUrl;
        this.clientDetailUrl = clientDetailUrl;
    }

    public PlaceholderUserDetail getDetail(Long id) {
        log.info("getDetail({})", id);

        String uri = hostUrl + clientDetailUrl;
        log.info("uri: {}", uri);

        return restTemplate.getForEntity(uri, PlaceholderUserDetail.class, id).getBody();
    }

}