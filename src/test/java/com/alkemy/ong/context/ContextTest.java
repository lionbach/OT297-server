package com.alkemy.ong.context;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import com.alkemy.ong.auth.service.AuthService;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.mapper.CategoryMapper;
import com.alkemy.ong.models.mapper.UserMapper;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.request.UserLoginRequest;
import com.alkemy.ong.models.request.UserRegisterRequest;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class ContextTest {

	protected static final String BEARER = "Bearer ";
	
	protected static final String REGISTER_URL = "/auth/register";
	
	protected static final String AUTH_LOGIN_URL = "/auth/login";

	protected UserLoginRequest userLoginRequest;

	protected UserRegisterRequest userRegisterRequest;
	
	protected NewsRequest newsRequest;
	
	CategoryRequest categoryRequest;
	
	Set<RoleEntity> roles;
	
	@Autowired
	public MockMvc mockMvc;
	
	@Autowired
	protected ObjectMapper objectMapper;
	
	@Autowired
	public UserMapper mapper;
	
	@Autowired
	public CategoryMapper categoryMapper;
	
	@Autowired
	public UserRepository userRepo;
	
	@Autowired
	public RoleRepository roleRepo;
	
	@Autowired
	public CategoryRepository categoryRepo;
	@Autowired
	public AuthService authService;


	protected String generateAdminToken() throws IOException, UnsupportedEncodingException, Exception, JsonProcessingException {
		buildAndRegisterAdmin();	
		
		String content = this.mockMvc
				.perform(post(AUTH_LOGIN_URL).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								UserLoginRequest.builder()
								.email("admin@test.com")
								.password("12345678")
								.build())))
				.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);		
	
		return JsonPath.read(content, "$.token");
	}
	

	protected String getToken() throws IOException, UnsupportedEncodingException, Exception, JsonProcessingException {
		UserRegisterRequest userRequest = UserRegisterRequest.builder()
				.firstName("admin")
				.lastName("admin")
				.email("admin@test.com")
				.password("12345678")
				.build();

		this.mockMvc
				.perform(MockMvcRequestBuilders.post(REGISTER_URL)
						.content(objectMapper.writeValueAsString(userRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
		
		String content = this.mockMvc
				.perform(post(AUTH_LOGIN_URL).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								UserLoginRequest.builder()
								.email("admin@test.com")
								.password("12345678")
								.build())))
				.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);		
	
		return JsonPath.read(content, "$.token");
	}	
	
	protected String generateUserToken() throws IOException, UnsupportedEncodingException, Exception, JsonProcessingException {
		buildAndRegisterUser();	
		
		String content = this.mockMvc
				.perform(post(AUTH_LOGIN_URL).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
								UserLoginRequest.builder()
								.email("user@test.com")
								.password("12345678")
								.build())))
				.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);		
	
		return JsonPath.read(content, "$.token");
	}
	
	protected void buildAndRegisterAdmin() throws IOException {
		userRegisterRequest = UserRegisterRequest.builder()
				.firstName("admin")
				.lastName("admin")
				.email("admin@test.com")
				.password("12345678")
				.build();
		authService.registerAdmin(userRegisterRequest);
	}
	
	protected void buildWithoutRegisterAdmin() throws IOException {
		userLoginRequest = UserLoginRequest.builder()
				.email("admin@test.com")
				.password("12345678")
				.build();
	}

	protected void buildAndRegisterUser() throws UsernameNotFoundException, IOException {
		userRegisterRequest = UserRegisterRequest.builder()
				.firstName("user")
				.lastName("user")
				.email("user@test.com")
				.password("12345678")
				.build();

		authService.register(userRegisterRequest);
	}
	
}
