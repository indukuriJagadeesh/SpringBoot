package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.springboot.model.Student;
import com.springboot.model.User;

import junit.framework.Assert;

public class UserServiceImplTest {

	@Spy
	@InjectMocks
	UserServiceImpl userServiceImpl ;
	
	@InjectMocks
	UserServiceImpl userServiceImplInjectMocks ;
	
	@Mock
	UserServiceImpl userServiceImplMock ;
	
	@Spy
	StudentService studentService ;
	
	@Mock
	StudentService studentServiceMock ;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getNameByMockAnnotationTest(){
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setUsername("jag");
		user.setId(123);
		users.add(user);
		
		List<Student> students = new ArrayList<>();
		Student student = new Student("123", "ram", "sebi ram", null);
		students.add(student);
		
		Mockito.when(userServiceImplMock.getAll()).thenReturn(users);
		//spy and mock both objects are working fine while using inject object 
		//Mockito.when(studentService.retrieveAllStudents()).thenReturn(students);
		Mockito.when(studentServiceMock.retrieveAllStudents()).thenReturn(students);
		
		
		String result = userServiceImplInjectMocks.getName();
		Assert.assertNotNull(result);
	}
	
	@Test
	public void getNameTest() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setUsername("jag");
		user.setId(123);
		users.add(user);
		
		List<Student> students = new ArrayList<>();
		Student student = new Student("123", "ram", "sebi ram", null);
		students.add(student);
		
		Mockito.doReturn(users).when(userServiceImpl).getAll();
		
		Mockito.doReturn(students).when(studentService).retrieveAllStudents();
		
		String result = userServiceImpl.getName();
		Assert.assertNotNull(result);
	}
}
