package com.alkemy.ong.models.response;

import lombok.Data;

import java.util.List;

@Data
public class NewsPaginatedResponse {
    List<NewsResponse> newsPageContent;
    String nextPage;
    String previousPage;
}
