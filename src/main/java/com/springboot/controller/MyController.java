package com.springboot.controller;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("message")
public class MyController {

    @RequestMapping(
                        method = RequestMethod.GET)
    @ResponseBody
    public String handleRequest () {

        //System.out.println("body in bytes: " + bytes);
        //System.out.println("body in string: " + str);

        return "<html><body><h1>Hi there</h1></body></html>";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType
                        .APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void handleFormRequest (@RequestBody MultiValueMap<String, String> formParams) {
        System.out.println("form params received " + formParams);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType
            .APPLICATION_FORM_URLENCODED_VALUE
                        )
    @ResponseStatus(HttpStatus.OK)
    //@ResponseStatus(HttpStatus.CREATED)
    public void handleFormPutRequest (@RequestBody MultiValueMap<String, String> formParams) {
        System.out.println("form params received " + formParams);
    }
}