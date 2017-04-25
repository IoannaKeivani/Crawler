## Web Crawler 
The web crawler is a very simple Breadth-First crawler.

# Installation
Download the project to your machine and import the jsoup jar into the project

# Explanation
The web crawler that is implemented, is a very simple Breadth-First crawler. It consists of four Java classes:
 - SpiderTest
 - Spider
 - SpiderLeg
 - Utilities
 
**Spider Test**: It is the test class, it takes the user defined URL and creates the spider which will crawl the web.

**Spider**: Is the class which keeps track of pages that have already been visited and the pages that are going to be visited. This lists are stored in two files so the crawler can resume its state.

**SpiderLeg**: Is the class which makes an HTTP request, parses the page for links, culls links that belong to forbidden domains and returns all the links on the page. 

**Utillities**: This class has some useful functions to read/write from/to a file and to delete files.
