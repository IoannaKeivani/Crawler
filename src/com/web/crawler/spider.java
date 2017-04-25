package com.web.crawler;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class spider 
{
	private static final int MAX_PAGES = 100;
	
	private static final String VisitedPagesFilename = "PagesVisited.txt";
	private static final String PagesToVisitFilename = "PagesToVisit.txt";

	private Set<String> pagesVisited = new HashSet<String>();
	private LinkedList<String> pagesToVisit = new LinkedList<String>();

	public void Initialize() 
	{
		Utilities util = new Utilities();
		
		File s = new File(VisitedPagesFilename);
		File l = new File(PagesToVisitFilename);

		if (s.exists()) {
			pagesVisited.addAll(util.readFromFile_set(VisitedPagesFilename));
		}

		if (l.exists()) {
			pagesToVisit.addAll(util.readFromFile_list(PagesToVisitFilename));
		}
	}
	

	// Search starts with a url
	public void search(String url) 
	{
		Utilities util = new Utilities();
		
		System.out.println("VisitedList (" + pagesVisited.toString() + ")");
		System.out.println("PagestoVisit (" + pagesToVisit.toString() + ")");
		
		// Ends when there are no more pages to be visited
		do{
			String currentUrl;
			SpiderLeg leg = new SpiderLeg();
			if (this.pagesToVisit.isEmpty()) {
				currentUrl = url;
				this.pagesVisited.add(url);
				util.writeToFile("PagesVisited.txt", this.pagesVisited);
			} else {
				currentUrl = this.nextUrl();
			}
			leg.crawl(currentUrl);

			this.pagesToVisit.addAll(leg.getLinks());
			
			// Update the file
			util.writeToFile("PagesToVisit.txt", this.pagesToVisit);
		}while (this.pagesVisited.size() < MAX_PAGES); 
			
	
		System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
		
		// Crawling is over. Delete the files.
		try {
			util.deleteFiles("PagesToVisit.txt");
			util.deleteFiles("PagesVisited.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Returns the next Url
	private String nextUrl() 
	{
		String nextUrl;
		Utilities util = new Utilities();
		do {
			nextUrl = this.pagesToVisit.remove(0);
			util.writeToFile("PagesToVisit.txt", this.pagesToVisit);
		} while (this.pagesVisited.contains(nextUrl));
		this.pagesVisited.add(nextUrl);
		util.writeToFile("PagesVisited.txt", this.pagesVisited);

		return nextUrl;
	}
}
