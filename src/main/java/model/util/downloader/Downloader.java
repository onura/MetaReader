package model.util.downloader;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * Downloader class which can download multiple files in parallel 
 */

public class Downloader {

	private Path downDir;
	private ExecutorService executor; 
	
	
	public Downloader(Path downDir) {
		this.downDir = downDir;		
		executor = Executors.newCachedThreadPool();
	}
	
	public Path getDownDir() {
		return downDir;
	}
	
	public void setDownDir(Path downDir) {
		this.downDir = downDir;
	}
	
	/*
	 * Downloads files in parallel and add their locations in a blocking queue 
	 */
	public void download(ArrayList<String> fileLinks, BlockingQueue<String> fileQueue) {	
		
		for(String fileLink:fileLinks) {			
			executor.execute(new DownloaderWorker(downDir+getFileNameFromLink(fileLink), fileLink, fileQueue));
		}			

		//wait all threads to finish.
		executor.shutdown();		
		try {
			  executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			  fileQueue.add("\0");	//mark the end of the queue
		} catch(InterruptedException ei) {
			System.err.println(ei.getMessage());
		}
	}
	
	//returns the file name from a link 
	public String getFileNameFromLink(String fileLink) {
		return fileLink.substring(fileLink.lastIndexOf('/'));
	}
}
