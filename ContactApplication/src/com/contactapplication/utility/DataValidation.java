package com.contactapplication.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
	
	public boolean validateEmailId(String email) {
		Pattern pattern = Pattern.compile("^[A-Z][A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		if(matcher.find()) {
			return true;
		}
		return false;
	}
}
