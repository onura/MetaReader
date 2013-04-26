package model.util.google;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import model.metafile.FileType;

/*
 * Responsible for searching files in the google and extracting file links
 */

public class GoogleFileSearch {
	private static final int BUFFER_SIZE = 10000;
	private static final String SEARCH_ADDRESS = "https://www.google.com.tr/search?output=search&q=";	
	
	// makes a file search in the google and returns file links. 
	public static ArrayList<String> search(String searchString, FileType fileType, int pageCount) {
		ArrayList<String> fileLinks = new ArrayList<String>();
		try {			
			String encodedString;
			for(int i = 0; i < pageCount; i++) {
				//prepare url
				encodedString = URLEncoder.encode("filetype:" + fileType.toString() + " " + searchString, "UTF-8");
				URL url = new URL( SEARCH_ADDRESS + encodedString +"&start="+(i*10));
				fileLinks.addAll(findFileLinks(getPage(url)));
			}
			
			return fileLinks;
			
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
			return fileLinks;
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return fileLinks;
		}		
	}
	
	// downloads a page
	private static String getPage(URL url) throws IOException {
		
		//create the https connection
		HttpURLConnection https= (HttpsURLConnection) url.openConnection();

		//set user agent of a browser to bypass google anti-bot
		https.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/2008102920 Firefox/3.0.4 (Splashtop-v1.0.5.0)");
		
		InputStream is = https.getInputStream();			
		StringBuilder result = new StringBuilder();
		byte[] buffer = new byte[BUFFER_SIZE];
		int size = 0;
		
		//get the response 
		do {
			size = is.read(buffer);
			if(size != -1)
				result.append(new String(buffer, 0, size));
		} while(size >= 0);
		
		is.close();
		https.disconnect();
		
		return result.toString();
	}
	
	// extracts links from a google result page
	private static ArrayList<String> findFileLinks(String pageContent) {
		ArrayList<String> fileLinks = new ArrayList<String>();
		
		//regex for google links (I wrote it for just the specific case, it might not be so generic)
		Pattern citeFinder = Pattern.compile("<h3 class=\"r\"><a href=\"/url\\?q=(.*?)&amp;",  Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		Matcher regexMatcher = citeFinder.matcher(pageContent);
		
		while(regexMatcher.find()) {
			try {
				fileLinks.add(URLDecoder.decode(regexMatcher.group(1), "UTF-8"));				
			} catch(UnsupportedEncodingException e) {
				System.err.println(e.getMessage());
			}
		}
			
		
		return fileLinks;
	}
	
	
	
}
