package com.springBlog;

public class Utils {

	public static boolean isBlankOrEmpty(String string){
		if(string == null || "".equals(string) )
			return true;
		else 
			return false; 
	}
}
