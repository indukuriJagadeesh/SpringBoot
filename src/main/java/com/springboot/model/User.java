package com.springboot.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User implements Serializable {

    private int id;
    private String username;
    private List<String> skills;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
    
    public static void main(String[] args) throws JsonProcessingException, IOException {
        System.out.println("Starting...!");
    	String json = "{'id':1,'username':'John'}";
        ObjectMapper mapper = new ObjectMapper();
        
     
        User user = mapper.reader().forType(User.class).readValue(json);
        
        System.out.println("user.username>>"+user.username);

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

	}

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}