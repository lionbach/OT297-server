package com.alkemy.ong.controller;

import java.io.IOException;
import org.junit.Test;

import org.springframework.http.HttpHeaders;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.alkemy.ong.context.ContextTest;


public class NewsControllerTest extends ContextTest {
	
	private static final String GET_NEWS_BY_ID_URL = "/news/{id}";
	
    
	@Test
	public void should_return_news_by_id() throws Exception, IOException {
		String token = getToken();
		this.mockMvc
				.perform(MockMvcRequestBuilders.get(GET_NEWS_BY_ID_URL, 1L)
						.header(HttpHeaders.AUTHORIZATION, BEARER + token))
				.andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty())
        		.andExpect(MockMvcResultMatchers.jsonPath("$.content").isNotEmpty())
    	        .andExpect(MockMvcResultMatchers.jsonPath("$.image").isNotEmpty());	
	}
	
}
