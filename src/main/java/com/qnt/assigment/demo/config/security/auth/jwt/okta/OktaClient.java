//package com.qnt.assigment.demo.config.security.auth.okta;
//
//import com.produgie.license.config.security.exception.InvalidTokenException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//
///*
// *
// * Okta Client
// */
//@Component
//@Slf4j
//public class OktaClient {
//	private final RestTemplate restTemplate;
//	private final String clientId;
//	private final String introspectUrl;
//
//	public OktaClient(RestTemplate restTemplate,
//					  @Value("${okta.clientId}") String clientId,
//					  @Value("${okta.introspect.url}") String introspectUrl) {
//
//		this.restTemplate = restTemplate;
//		this.clientId = clientId;
//		this.introspectUrl = introspectUrl;
//	}
//
//	public TokenClaims validateToken(String token) {
//		log.info("validateToken({})", token);
//		TokenClaims tokenClaims = getTokenClaims(token);
//		if (!tokenClaims.isActive()) {
//			throw new InvalidTokenException("Token Not Valid");
//		}
//		return tokenClaims;
//	}
//
//	private TokenClaims getTokenClaims(String token) {
//		log.info("getTokenClaims({})", token);
//		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		requestBody.add("client_id", clientId);
//		requestBody.add("token", token);
//		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);
//		TokenClaims tokenClaims = restTemplate.postForObject(introspectUrl, entity, TokenClaims.class);
//
//		log.info("tokenClaims: {}", tokenClaims);
//
//		return tokenClaims;
//	}
//
//}