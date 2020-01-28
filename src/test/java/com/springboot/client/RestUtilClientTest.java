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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.model.Course;
import com.springboot.model.CourseList;
@RunWith(SpringRunner.class)
@WebMvcTest(RestUtilClient.class)
public class RestUtilClientTest {

	@Mock RestTemplate restTemplate;
	
	
	//MockRestServiceServer mockServer=null;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	 
	@Spy
	@InjectMocks
	RestUtilClient restUtilClient;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void retrieveCoursesForStudentAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/students/courses")
	      .accept(MediaType.APPLICATION_JSON))
	  	//.andDo(print())
	    //.andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());
	}
	
	@Test
	public void retrieveCoursesForStudentRestAPI() throws Exception
	{
		HttpHeaders headers = new HttpHeaders();
		//headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> responseEntity= null;
		try {			
			responseEntity= restTemplate.exchange("http://localhost:8080/vjr/students/1/courses", HttpMethod.GET, entity, String.class);
			System.out.println("responseEntity>>"+responseEntity);
		}catch(HttpStatusCodeException httpStatusCodeException) {
			System.out.println("error Msg : "+httpStatusCodeException.getMessage());
		}
	}
	
	private ResultHandler print() {
		return null;
	}
	
	
	@Test
	public void consumeRestCallTest() throws JsonParseException, JsonMappingException, IOException {
		
		ResponseEntity<String> myEntity = new ResponseEntity<String>("[{\"id\":\"1\",\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]},{\"id\":\"2\",\"name\":\"Spring MVC\",\"description\":\"10 Examples\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]},{\"id\":\"3\",\"name\":\"Spring Boot\",\"description\":\"6K Students\",\"steps\":[\"Learn Maven\",\"Learn Spring\",\"Learn Spring MVC\",\"First Example\",\"Second Example\"]},{\"id\":\"4\",\"name\":\"Maven\",\"description\":\"Most popular maven course on internet!\",\"steps\":[\"Pom\",\"Build Life Cycle\",\"Parent POM\",\"Importing into Eclipse\"]}]", HttpStatus.ACCEPTED);
        Mockito.when(restTemplate.exchange(
            Matchers.eq("http://localhost:8080/vjr/students/1/courses"),
            Matchers.eq(HttpMethod.GET),
            Matchers.<HttpEntity<String>>any(),
            Matchers.<Class<String>> any()
        )).thenReturn(myEntity);
        
        HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
        //restUtilClient.retrieveCoursesForStudent();
		ResponseEntity<String> myEntityResponse = restUtilClient.consumeRestCall(entity, myEntity);
		System.out.println("myEntityResponse>>"+myEntityResponse);
		ObjectMapper objectMapper = new ObjectMapper();
		//TODO: need to very how can we deserialize to java object; CourseList courseList= objectMapper.readValue(myEntityResponse.getBody(), CourseList.class);
		//System.out.println("courseList>>>"+courseList);
		
        /*
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("sampleBodyString", HttpStatus.ACCEPTED);
        
        Mockito.when(restTemplate.exchange(
                Matchers.anyString(), 
                Matchers.eq(HttpMethod.GET),
                Matchers.<HttpEntity<?>> any(), 
                Matchers.<Class<String>> any()
               )
              ).thenReturn(responseEntity);
        */
        /*
         * when(restTemplate.exchange(
                           Matchers.anyString(), 
                           Matchers.any(HttpMethod.class),
                           Matchers.<HttpEntity<?>> any(), 
                           Matchers.<Class<String>> any()
                          )
                         ).thenReturn(responseEntity);

         * 
         */
		
	}
	
	@Test
	public void consumeRestCallTestByMethod() throws JsonParseException, JsonMappingException, IOException {
		
		ResponseEntity<String> myEntity = new ResponseEntity<String>("[{\"id\":\"101\",\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]},{\"id\":\"2\",\"name\":\"Spring MVC\",\"description\":\"10 Examples\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]},{\"id\":\"3\",\"name\":\"Spring Boot\",\"description\":\"6K Students\",\"steps\":[\"Learn Maven\",\"Learn Spring\",\"Learn Spring MVC\",\"First Example\",\"Second Example\"]},{\"id\":\"4\",\"name\":\"Maven\",\"description\":\"Most popular maven course on internet!\",\"steps\":[\"Pom\",\"Build Life Cycle\",\"Parent POM\",\"Importing into Eclipse\"]}]", HttpStatus.ACCEPTED);
        
		Mockito.when(restUtilClient.getRestTemplate()).thenReturn(restTemplate);
		
		Mockito.when(restTemplate.exchange(
            Matchers.eq("http://localhost:8080/vjr/students/1/courses"),
            Matchers.eq(HttpMethod.GET),
            Matchers.<HttpEntity<String>>any(),
            Matchers.<Class<String>> any()
        )).thenReturn(myEntity);
        
        HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> myEntityResponse = restUtilClient.consumeRestCallByMethod(entity, myEntity);
		System.out.println("myEntityResponse>>"+myEntityResponse);
		ObjectMapper objectMapper = new ObjectMapper();
		//TODO: need to very how can we deserialize to java object; CourseList courseList= objectMapper.readValue(myEntityResponse.getBody(), CourseList.class);
		//System.out.println("courseList>>>"+courseList);
		
	
	}

}
