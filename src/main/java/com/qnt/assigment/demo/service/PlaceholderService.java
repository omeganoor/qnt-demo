package com.qnt.assigment.demo.service;

import com.qnt.assigment.demo.client.post.PlaceholderPostsClient;
import com.qnt.assigment.demo.client.post.PlaceholderPostsDetail;
import com.qnt.assigment.demo.client.user.PlaceholderUserClient;
import com.qnt.assigment.demo.client.user.PlaceholderUserDetail;
import com.qnt.assigment.demo.dao.ContactResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class PlaceholderService {

  private PlaceholderPostsClient placeholderPostsClient;
  private PlaceholderUserClient placeholderUserClient;

  public PlaceholderService (PlaceholderPostsClient placeholderPostsClient, PlaceholderUserClient placeholderUserClient) {
    this.placeholderPostsClient = placeholderPostsClient;
    this.placeholderUserClient = placeholderUserClient;
  }

  public List<PlaceholderUserDetail> getAllUser () {

    log.info("Execution started calling two api getAllPosts API 1 and getAllPosts API 2");
    long startTime = System.currentTimeMillis();

    CompletableFuture<PlaceholderUserDetail> responseCompletable1 = CompletableFuture.supplyAsync(() -> this.placeholderUserClient.getDetail(1l));
    CompletableFuture<PlaceholderUserDetail> responseCompletable2 = CompletableFuture.supplyAsync(() -> this.placeholderUserClient.getDetail(2l));
    CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(responseCompletable1, responseCompletable1);
    // Wait for both API calls to complete
    combinedFuture.join();

    // Get the results from the CompletableFutures
    PlaceholderUserDetail result1 = null;
    PlaceholderUserDetail result2 = null;
    try {
      result1 = responseCompletable1.get();
      result2 = responseCompletable2.get();

      long endTime = System.currentTimeMillis();
      long executionTime = endTime - startTime;
      log.info("Execution Complete for two api API 1 and API 2 Time -{}", executionTime);
      result1.setConcurrentApiExecutionTime(executionTime);
      result2.setConcurrentApiExecutionTime(executionTime);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    return Arrays.asList(result1, result2);
  }

  public List<PlaceholderPostsDetail> getAllPosts () {
    log.info("Execution started calling two api getAllPosts API 1 and getAllPosts API 2");
    long startTime = System.currentTimeMillis();

    CompletableFuture<PlaceholderPostsDetail> responseCompletable1 = CompletableFuture.supplyAsync(() -> this.placeholderPostsClient.getPostById(1l));
    CompletableFuture<PlaceholderPostsDetail> responseCompletable2 = CompletableFuture.supplyAsync(() -> this.placeholderPostsClient.getPostById(2l));
    CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(responseCompletable1, responseCompletable1);
    // Wait for both API calls to complete
    combinedFuture.join();

    // Get the results from the CompletableFutures
    PlaceholderPostsDetail result1 = null;
    PlaceholderPostsDetail result2 = null;
    try {
      result1 = responseCompletable1.get();
      result2 = responseCompletable2.get();

      long endTime = System.currentTimeMillis();
      long executionTime = endTime - startTime;
      log.info("Execution Complete for two api API 1 and API 2 Time -{}", executionTime);
      result1.setConcurrentApiExecutionTime(executionTime);
      result2.setConcurrentApiExecutionTime(executionTime);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    return Arrays.asList(result1, result2);
  }
}