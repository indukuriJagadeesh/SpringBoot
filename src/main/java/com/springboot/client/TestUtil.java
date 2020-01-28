package com.springboot.client;

import java.util.Arrays;
import java.util.List;

public class TestUtil {

	
	public static void main(String[] args) {
		
		
		List applications = Arrays.asList("A", "B");
		  List<String> user = Arrays.asList("A");
		  Boolean[] arr = {true}; 
		  int result =0;
		  int[] resultArry = {0};
		  applications.forEach( a -> {
		   for (String str : user) {
		    if(a.equals(str)) {
		     //error resolved: Local variable flag defined in an enclosing scope must be final or effectively final
		     arr[0] = false; 
		     //result=10;
		     resultArry[0]= 10;
		     break;
		    }else{
		     arr[0] = true;
		     resultArry[0]= 100;
		    }
		   }
		   System.out.println("resultArry>>>>"+resultArry[0]);
		 
		   if(!arr[0]) {
		    System.out.println("Here with false");
		   }else{
		    System.out.println("Here with true");
		   }
		  });
	}
	
	
}
