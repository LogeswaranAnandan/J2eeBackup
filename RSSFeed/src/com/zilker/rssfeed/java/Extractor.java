package com.zilker.rssfeed.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {
	public String extractUrl(String description) {
		Pattern pattern = Pattern.compile("[<img src=]?(.*)?[>]?");
		Matcher matcher = pattern.matcher(description);
		if(matcher.find()) {
			return matcher.group();
		} else {
			return "not found";
		}
	}
}
