package com.web.crawler;

public class SpiderTest {

	private static final String StartURL = "https://www.finn.no/"; 
	
	// This is our test. It creates a spider (which creates spider legs) and crawls the web.	
	public static void main(String[] args) {
		spider spider = new spider();
		spider.Initialize();
		spider.search(StartURL);
	}

}
