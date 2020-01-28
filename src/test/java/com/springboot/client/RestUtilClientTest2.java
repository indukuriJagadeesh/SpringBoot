package com.springboot.client;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringJUnit4ClassRunner.class)
public class RestUtilClientTest2 {

	//@Mock RestTemplate restTemplate;
	
	
	//MockRestServiceServer mockServer=null;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(new RestUtilClientTest())
                .build();
	}
	 
	@Spy
	@InjectMocks
	RestUtilClient2 restUtilClient2;
	
	@Mock
	RestUtilClient restUtilClient;
	
	/*@Autowired*/
	private MockMvc mvc;
	
	private ResultHandler print() {
		return null;
	}
	
	@Test
	public void consumeRestCallTestByMethod() throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
		ResponseEntity<String> myEntity = new ResponseEntity<String>("[{\"id\":\"101\",\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]},{\"id\":\"2\",\"name\":\"Spring MVC\",\"description\":\"10 Examples\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]},{\"id\":\"3\",\"name\":\"Spring Boot\",\"description\":\"6K Students\",\"steps\":[\"Learn Maven\",\"Learn Spring\",\"Learn Spring MVC\",\"First Example\",\"Second Example\"]},{\"id\":\"4\",\"name\":\"Maven\",\"description\":\"Most popular maven course on internet!\",\"steps\":[\"Pom\",\"Build Life Cycle\",\"Parent POM\",\"Importing into Eclipse\"]}]", HttpStatus.ACCEPTED);
        
		Mockito.when(restUtilClient2.getRestTemplate()).thenReturn(restTemplate);
		
		Mockito.when(restTemplate.exchange(
            Matchers.eq("http://localhost:8080/vjr/students/1/courses"),
            Matchers.eq(HttpMethod.GET),
            Matchers.<HttpEntity<String>>any(),
            Matchers.<Class<String>> any()
        )).thenReturn(myEntity);
        
        HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> myEntityResponse = restUtilClient2.consumeRestCallByMethod(entity, myEntity);
		System.out.println("myEntityResponse>>"+myEntityResponse);
		ObjectMapper objectMapper = new ObjectMapper();
		//TODO: need to very how can we deserialize to java object; CourseList courseList= objectMapper.readValue(myEntityResponse.getBody(), CourseList.class);
		//System.out.println("courseList>>>"+courseList);
		
	
	}
	
	@Test
	public void testGetName() {
		Mockito.when(restUtilClient2.getName()).thenReturn("88");
		Mockito.doReturn("550").when(restUtilClient2).getName();
		String result = restUtilClient2.getNameBySpyVsMock();
		System.out.println("result99 >>>"+result);
		
	}

}
