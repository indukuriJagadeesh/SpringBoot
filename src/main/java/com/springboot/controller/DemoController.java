package com.springboot.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.springboot.dto.BookTestDtoForm;
import com.springboot.model.User;
 
@Controller
public class DemoController {
 
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/")
	public String welcome(Model model) {
		BookTestDtoForm bookTestDtoForm = new BookTestDtoForm();
		model.addAttribute("form",bookTestDtoForm);
		return "welcome";
	}
	
	@RequestMapping("/testSave")
	public String testSave(@ModelAttribute BookTestDtoForm form,Model model) {
		System.out.println("Form elements >>"+form.getBookName());
		model.addAttribute("bookName",form.getBookName());
		
		model.addAttribute("form",form);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		ResponseEntity<List<User>> responseEntity= null;
		try {			
			responseEntity= restTemplate.exchange("http://localhost:8080//users/", HttpMethod.GET, entity, 
					new ParameterizedTypeReference<List<User>>(){});
		}catch(HttpStatusCodeException httpStatusCodeException) {
			System.out.println("error Msg : "+httpStatusCodeException.getMessage());
		}
		//return responseEntity.getBody();
		System.out.println("responseEntity.getBody()>>"+responseEntity.getBody());
		model.addAttribute("response",responseEntity.getBody());
		return "welcome";
	}
	
	@RequestMapping("/testSave1")
	public String testSave1(@ModelAttribute BookTestDtoForm form,Model model) {
		System.out.println("Form elements >>"+form.getBookName());
		model.addAttribute("bookName",form.getBookName());
		

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> responseEntity= null;
		try {			
			responseEntity= restTemplate.exchange("http://localhost:8080//users/", HttpMethod.GET, entity, String.class);
		}catch(HttpStatusCodeException httpStatusCodeException) {
			System.out.println("error Msg : "+httpStatusCodeException.getMessage());
		}
		//return responseEntity.getBody();
		System.out.println("responseEntity.getBody()>>"+responseEntity.getBody());
		model.addAttribute("response",responseEntity.getBody());
		return "result";
	}
}