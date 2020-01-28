package com.springboot.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.springboot.exceptions.CourseNotFoundException;
import com.springboot.exceptions.ErrorDetails;
import com.springboot.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.model.User;
import com.springboot.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    RestTemplate restTemplate;

    // =========================================== Get All Users ==========================================

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        LOG.info("getting all users");
        List<User> users = userService.getAll();

        ObjectMapper jsonMapper = new ObjectMapper();
        try {
			String jsonString= jsonMapper.writeValueAsString(users);
			System.out.println("bytes array "+jsonMapper.writeValueAsBytes(users));
			System.out.println("request >> "+jsonString.getBytes("UTF-8"));
			System.out.println("jsonString>>"+jsonString);
			
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (users == null || users.isEmpty()){
            LOG.info("no users found");
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // =========================================== Get User By ID =========================================

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable("id") int id){
        LOG.info("getting user with id: {}", id);
        User user = userService.findById(id);

        if (user == null){
            LOG.info("user with id {} not found", id);
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getDuplicateByID(@PathVariable("id") int id){
        LOG.info("getting user with id: {}", id);
        List<User> users = userService.findByIds(id);

        if (users == null){
            LOG.info("user with id {} not found", id);
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    

    // =========================================== Create New User ========================================

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder ucBuilder){
        LOG.info("creating new user: {}", user);

        if (userService.exists(user)){
            LOG.info("a user with name " + user.getUsername() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.create(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // =========================================== Update Existing User ===================================

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user){
        LOG.info("updating user: {}", user);
        User currentUser = userService.findById(id);

        if (currentUser == null){
            LOG.info("User with id {} not found", id);
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());

        userService.update(user);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    // =========================================== Delete User ============================================

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        LOG.info("deleting user with id: {}", id);
        User user = userService.findById(id);

        if (user == null){
            LOG.info("Unable to delete. User with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @PostMapping(path = "/getUser")
    public ResponseEntity<List<User>> getUserInformationBYJSON(@RequestBody User user) {
        LOG.info("getting all users");
        List<User> users = userService.getAll();
        List<User> usersDummy = new ArrayList<User>();
        for(User user2 : users) {
        	
        	if(user.getId()==user2.getId()) {
        		usersDummy.add(user2);
        	}
        }
        if (users == null || users.isEmpty()){
            LOG.info("no users found");
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<User>>(usersDummy, HttpStatus.OK);
    }

    @PostMapping(path = "/getUserForList")
    public ResponseEntity<List<User>> getUserInformationBYJSONforListInput(@RequestBody User user) throws UserNotFoundException {
        LOG.info("getting all users"+user.getSkills());
        List<User> users = userService.getAll();
        List<User> usersDummy = new ArrayList<User>();
        if (users == null || users.isEmpty()){
            LOG.info("no users found");
            throw new UserNotFoundException("Invalid course requested...!");
            //return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        for(User user2 : users) {

            if(user.getId()==user2.getId()) {
                usersDummy.add(user2);
            }
        }
        if (usersDummy == null || usersDummy.isEmpty()){
            LOG.info("no users found");
            throw new UserNotFoundException("Invalid course requested...!");
            //return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }



        return new ResponseEntity<List<User>>(usersDummy, HttpStatus.OK);
    }

    @PostMapping(path = "/getUserForListTestLinkedService1")
    public String getUserForListTestLinkedService1(@RequestBody User user)  {
        LOG.info("getting all users"+user.getSkills());
        List<User> users = userService.getAll();
        String response = null;
        List<User> usersDummy = new ArrayList<User>();

        for(User user2 : users) {

            if(user.getId()==user2.getId()) {
                usersDummy.add(user2);
            }
        }
        if (usersDummy == null || usersDummy.isEmpty()){
            LOG.info("no users found");
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<String> entity = new HttpEntity<String>(headers);
                User usertest = getUser();
                HttpEntity<User> requestEntity = new HttpEntity<>(usertest, headers);

                String endpointUrl="http://localhost:8080/users/getUserForListTestLinkedService2";
                ResponseEntity<String> responseEntity= restTemplate.exchange(endpointUrl, HttpMethod.POST, requestEntity, String.class);


            response = responseEntity.getBody();
            //throw new UserNotFoundException("Invalid course requested...!");
            //return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }



        return response;
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

    @PostMapping(path = "/getUserForListTestLinkedService2")
    public ResponseEntity<List<User>> getUserForListTestLinkedService2(@RequestBody User user) throws UserNotFoundException {
        LOG.info("getting all users"+user.getSkills());

        List<User> usersDummy = new ArrayList<User>();

        if (usersDummy == null || usersDummy.isEmpty()){
            LOG.info("no users found");
            throw new UserNotFoundException("Invalid course requested...!");
            //return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(usersDummy, HttpStatus.OK);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(Exception ex) {
        ErrorDetails error = new ErrorDetails();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}/groups", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getGroupsByID(@PathVariable("id") int id){
        LOG.info("getting getGroupsByID : {}", id);
       /* List<User> users = userService.findByIds(id);

        if (users == null){
            LOG.info("user with id {} not found", id);
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }*/
        String response = "{\r\n" + 
        		"  \"data\": [\r\n" + 
        		"    {\r\n" + 
        		"      \"externalId\": \"string\",\r\n" + 
        		"      \"groups\": [\r\n" + 
        		"        {}\r\n" + 
        		"      ],\r\n" + 
        		"      \"id\": 0,\r\n" + 
        		"      \"name\": \"jagadeesh\",\r\n" + 
        		"      \"status\": \"string\"\r\n" + 
        		"    },{\r\n" + 
        		"      \"externalId\": \"string\",\r\n" + 
        		"      \"groups\": [\r\n" + 
        		"        {}\r\n" + 
        		"      ],\r\n" + 
        		"      \"id\": 0,\r\n" + 
        		"      \"name\": \"Sai\",\r\n" + 
        		"      \"status\": \"string\"\r\n" + 
        		"    }\r\n" + 
        		"  ],\r\n" + 
        		"  \"size\": 0,\r\n" + 
        		"  \"start\": 0,\r\n" + 
        		"  \"total\": 0\r\n" + 
        		"}";
        return response;
    }
    
}
