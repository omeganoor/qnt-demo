package com.qnt.assigment.demo.controller;

import com.qnt.assigment.demo.client.post.PlaceholderPostsDetail;
import com.qnt.assigment.demo.client.user.PlaceholderUserDetail;
import com.qnt.assigment.demo.dao.ContactResponseDTO;
import com.qnt.assigment.demo.dao.constans.ApiPath;
import com.qnt.assigment.demo.service.PlaceholderService;
import com.qnt.assigment.demo.service.RateLimiterConfiguration;
import com.qnt.assigment.demo.utility.PageableHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiPath.PLACEHOLDER)
@Slf4j
public class JSONPlaceholderController {
    private RateLimiterConfiguration rateLimiterConfiguration;
    private PlaceholderService placeholderService;

    public JSONPlaceholderController (RateLimiterConfiguration rateLimiterConfiguration, PlaceholderService placeholderService) {
        this.rateLimiterConfiguration = rateLimiterConfiguration;
        this.placeholderService = placeholderService;
    }

    @GetMapping(ApiPath.USERS)
    public ResponseEntity<?> getPlaceholderUsersData(
    ) {
        UUID reqId = UUID.randomUUID();
        if (rateLimiterConfiguration.isLimitAvailable(reqId.toString())) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS.value()).build();
        }

        List<PlaceholderUserDetail> contactPage = this.placeholderService.getAllUser();
        return ResponseEntity.ok(contactPage);
    }

    @GetMapping(ApiPath.POSTS)
    public ResponseEntity<List<?>> getPlaceholderPostsData (
    ) {
        UUID reqId = UUID.randomUUID();
        if (rateLimiterConfiguration.isLimitAvailable(reqId.toString())) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS.value()).build();
        }

        List<PlaceholderPostsDetail> contactPage = this.placeholderService.getAllPosts();
        return ResponseEntity.ok(contactPage);
    }
}