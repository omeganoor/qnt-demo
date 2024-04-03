package com.qnt.assigment.demo.client.post;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlaceholderPostsDetail {
    public int id;
    public String slug;
    public String url;
    public String title;
    public String content;
    public String image;
    public String thumbnail;
    public String status;
    public String category;
    public String publishedAt;
    public String updatedAt;
    public int userId;
    public long concurrentApiExecutionTime;
}