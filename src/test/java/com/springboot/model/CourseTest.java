package com.springboot.model;

import static org.mockito.Mockito.doAnswer;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import junit.framework.Assert;

public class CourseTest {

	@Test
	public void testSetName() {
		Course courseMock=Mockito.mock(Course.class);
		doAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation){
				Object[] args = invocation.getArguments();
				Mock mock = (Mock) invocation.getMock();
				System.out.println("Hello....>");
				return null;
			}
		}).when(courseMock).setName("jaggu");
	}

	@Test
	public void testSetName1() {
		Course courseMock=Mockito.spy(Course.class);
		try {
			courseMock.setName(null);
			Assert.fail();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
