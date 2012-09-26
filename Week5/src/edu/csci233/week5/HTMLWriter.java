package edu.csci233.week5;


public class HTMLWriter {
	
	public static String getHTMLHeader() {
		return getHTMLHeader("Missing title");
	}
	
	public static String getHTMLHeader(String title) {
		return "<html><head><title>" + title + "</title></body>";
	}
	
	public static String getHTMLFooter() {
		return "</body></html>";
	}

}
