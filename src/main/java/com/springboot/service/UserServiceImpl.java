package com.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Student;
import com.springboot.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	StudentService studentService ;
	
	
    private static final AtomicInteger counter = new AtomicInteger();
    private static List<User> users = new ArrayList<User>(
            Arrays.asList(
                    new User(counter.incrementAndGet(), "Daenerys Targaryen"),
                    new User(counter.incrementAndGet(), "John Snow"),
                    new User(2, "Jagadeesh"),
                    new User(counter.incrementAndGet(), "Arya Stark"),
                    new User(counter.incrementAndGet(), "Cersei Baratheon")));

    public String getName() {
    	System.out.println("Calling getName in UserServiceImpl>>");
    	List<User> userList = getAll();
    	String result = null;
    	
    	List<Student> studentList = studentService.retrieveAllStudents();
    	
    	if(studentList!=null && !studentList.isEmpty()) {
    		result= "TotalStudents"+studentList.size();
    	}
    	
    	return result;
    }
    
    
    
    @Override
    public List<User> getAll() {
    	System.out.println("user service getAll called>>>");
        return users;
    }

    @Override
    public User findById(int id) {
        for (User user : users){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }
    
    
    public List<User> findByIds(int id) {
    	 List<User> users1 = new ArrayList<User>();
        for (User user : users){
            if (user.getId() == id){
            	users1.add(user);
            }
        }
        return users1;
    }

    @Override
    public User findByName(String name) {
        for (User user : users){
            if (user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void create(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
    }

    @Override
    public void update(User user) {
        int index = users.indexOf(findById(user.getId()));
        users.set(index, user);
    }

    @Override
    public void delete(int id) {
        User user = findById(id);
        users.remove(user);
    }

    @Override
    public boolean exists(User user) {
        return findByName(user.getUsername()) != null;
    }

}
