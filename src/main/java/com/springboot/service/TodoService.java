package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.model.Todo;


/**
 * @author Petri Kainulainen
 */
@Service
public class TodoService {
	
	public List<Todo> findAll(){
		List<Todo> resultList = new ArrayList<Todo>();
		Todo todo = new Todo();
		todo.setTitle("Jagadeesh");
		resultList.add(todo);
		
		todo = new Todo();
		todo.setTitle("Raghavi");
		resultList.add(todo);
		
		return resultList;
	}
}