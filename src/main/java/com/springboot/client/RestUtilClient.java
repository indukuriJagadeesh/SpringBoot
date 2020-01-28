package com.springboot.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.springboot.exceptions.MyResponseErrorHandler;

@RestController
@RequestMapping("rest")
public class RestUtilClient {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/students/courses")
	public String retrieveCoursesForStudent() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> responseEntity= null;
		responseEntity = consumeRestCall(entity, responseEntity);
		return responseEntity.getBody();
	}

	public ResponseEntity<String> consumeRestCall(HttpEntity<String> entity, ResponseEntity<String> responseEntity) {
		try {			
			responseEntity= restTemplate.exchange("http://localhost:8080/vjr/students/1/courses", HttpMethod.GET, entity, String.class);
		}catch(HttpStatusCodeException httpStatusCodeException) {
			System.out.println("error Msg : "+httpStatusCodeException.getMessage());
		}
		return responseEntity;
	}
	public RestTemplate getRestTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new MyResponseErrorHandler());
		
		return restTemplate;
	}
	
	public ResponseEntity<String> consumeRestCallByMethod(HttpEntity<String> entity, ResponseEntity<String> responseEntity) {
		try {			
			responseEntity= getRestTemplate().exchange("http://localhost:8080/vjr/students/1/courses", HttpMethod.GET, entity, String.class);
		}catch(HttpStatusCodeException httpStatusCodeException) {
			System.out.println("error Msg : "+httpStatusCodeException.getMessage());
		}
		return responseEntity;
	}



	@GetMapping("/retrieveUser")
	public String retrieveUser() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		User usertest = getUser();
		HttpEntity<User> requestEntity = new HttpEntity<>(usertest, headers);

		String endpointUrl="http://localhost:8080/users/getUserForList";
		ResponseEntity<String> responseEntity= restTemplate.exchange(endpointUrl, HttpMethod.POST, requestEntity, String.class);


		return responseEntity.getBody();
	}

	@GetMapping("/getUserForListClientTestLinkedService")
	public String getUserForListClientTestLinkedService() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		User usertest = getUser();
		HttpEntity<User> requestEntity = new HttpEntity<>(usertest, headers);

		String endpointUrl="http://localhost:8080/users/getUserForListTestLinkedService1";
		ResponseEntity<String> responseEntity= restTemplate.exchange(endpointUrl, HttpMethod.POST, requestEntity, String.class);


		return responseEntity.getBody();
	}

	private User getUser() {
		User usertest = new User();
		usertest.setId(2);
		usertest.setUsername("raghavi");
		List<String> skills = new ArrayList<>();
		skills.add("C");
		skills.add("Java");
		skills.add("Cpp");
		usertest.setSkills(skills);
		return usertest;
	}


	@GetMapping("/employee/{empName}")
	public String retrieveEmployee(@PathVariable String empName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		String endpointUrl="http://localhost:8080/"+empName+"?empId=152";
		ResponseEntity<String> responseEntity= restTemplate.exchange(endpointUrl, HttpMethod.GET, entity, String.class);


		return responseEntity.getBody();
	}
	
	public String getName() {
		return "43";
	}



}
