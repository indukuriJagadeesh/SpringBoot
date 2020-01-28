package com.springboot.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.springboot.exceptions.MyResponseErrorHandler;

@RestController
@RequestMapping("rest")
public class RestUtilClient2 {

	RestUtilClient restUtilClient;

	public RestTemplate getRestTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new MyResponseErrorHandler());

		return restTemplate;
	}

	public ResponseEntity<String> consumeRestCallByMethod(HttpEntity<String> entity, ResponseEntity<String> responseEntity) {
		try {			
			responseEntity= getRestTemplate().exchange("http://localhost:8080/vjr/students/1/courses", HttpMethod.GET, entity, String.class);
			String result = responseEntity.getBody();
			System.out.println("result >>"+result);
		}catch(HttpStatusCodeException httpStatusCodeException) {
			System.out.println("error Msg : "+httpStatusCodeException.getMessage());
		}
		return responseEntity;
	}
	
	public String getNameBySpyVsMock() {
		
		String result ="10";
		result = getName();
		//result = restUtilClient.getName();
		
		return result;
		
	}
	
	public String getName() {
		return "100";
	}





}
