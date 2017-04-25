package com.web.crawler;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg 
{
	// Forbidden domains
	private List<String> forbiddenDomains = Arrays.asList("google.gr", "facebook.com", "wikipedia.org"); 

	// We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private List<String> links = new LinkedList<String>();
	
	// This performs all the work. It makes an HTTP request, checks the response, and then gathers up all the links on the page.	
	public boolean crawl(String url) 
	{
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument = connection.get();
			
			if (connection.response().statusCode() == 200)
			{
				System.out.println("\n**Visiting** Received web page at " + url);
			}
	
			if (!connection.response().contentType().contains("text/html")) {
				System.out.println("**Failure** Retrieved something other than HTML");
				return false;
			}
			
			// Search for links
			Elements linksOnPage = htmlDocument.select("a[href]");

			// Cull forbidden domains
			boolean forbidden = false;
			for (Element link : linksOnPage) {
				for (String fbDomain : forbiddenDomains) {
					if (link.toString().contains(fbDomain)) {
						forbidden = true;
					}
				}

				if (forbidden == false) {
					this.links.add(link.absUrl("href"));
				}

			}
			System.out.println("Final (" + this.links.size() + ") links");
			return true;
		} catch (IOException ioe) {
			return false;
		}
	}

	public List<String> getLinks() {
		return this.links;
	}
}