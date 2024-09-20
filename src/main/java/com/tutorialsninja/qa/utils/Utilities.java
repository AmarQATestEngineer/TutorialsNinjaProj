package com.tutorialsninja.qa.utils;

import java.util.Date;

public class Utilities {

	

	public static String generateEmailWithTimeStamp() {
		// TODO Auto-generated method stub
		Date date=new Date();
		String timeStamp=date.toString().replace(" ", "_").replace(":", "_");
		return "rgdfgedgtd"+timeStamp+"@gmail.com";
	}
}
