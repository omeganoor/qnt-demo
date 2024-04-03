//
//package com.qnt.assigment.demo.config.security.auth.okta;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Data;
//import lombok.ToString;
//
//import java.util.List;
//
//@Data
//@ToString
//public class TokenClaims {
//    private boolean active;
//    private String scope;
//    private String username;
//    private long exp;
//    private long iat;
//    @JsonProperty("sub")
//    private String subject;
//    @JsonProperty("aud")
//    private String audiance;
//    @JsonProperty("client_id")
//    private String clientId;
//    private String uid;
//    @JsonProperty("Groups")
//    List<String> groups;
//}