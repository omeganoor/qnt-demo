package com.qnt.assigment.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class RateLimiterConfiguration {
  private  final int maxCountLimit;
  private static final Duration TIME_WINDOW = Duration.ofMinutes(5);
  private static final Map<String, Queue<Long>> requestMap = new ConcurrentHashMap<>();

  public RateLimiterConfiguration (@Value("${max.limit.request}") int maxCountLimit) {
    this.maxCountLimit = maxCountLimit;
  }

  public boolean isLimitAvailable (String ip) {
    Queue<Long> requestQueue = requestMap.computeIfAbsent(ip, k -> new ConcurrentLinkedQueue<>());
    long now = System.currentTimeMillis();
    requestQueue.add(now);

    while (!requestQueue.isEmpty() && now - requestQueue.peek() > TIME_WINDOW.toMillis()) {
      requestQueue.poll();
    }

    if (requestQueue.size() > maxCountLimit) {
      return false;
    }

    return true;
  }
}