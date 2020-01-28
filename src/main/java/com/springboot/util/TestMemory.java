package com.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.model.User;

import java.util.ArrayList;
import java.util.List;

public class TestMemory {

	static int i = 0;
// VM arguments : -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:C:\jagadeesh\log\gc.log
	public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User usertest = new User();
        usertest.setId(15);
        usertest.setUsername("raghavi");
        List<String> skills = new ArrayList<>();
        skills.add("C");
        skills.add("Java");
        skills.add("Cpp");
        usertest.setSkills(skills);
        String jsonString = mapper.writeValueAsString(usertest);
        System.out.println(">>>>>>"+jsonString);

		/*if (i < 250) {
			i++;
			System.out.println(i);
			main(new String[] { (args[0] + args[0]) });
		}*/

	}

}