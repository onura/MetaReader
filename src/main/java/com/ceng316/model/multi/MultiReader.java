package com.ceng316.model.multi;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.ceng316.model.MetaReader.RETCODES;
import com.ceng316.model.metafile.MetaFile;
import com.ceng316.model.realtime.IRealTimeResult;



/*
 *  To support analysing various files in a folder in parallel
 *  by using a FixedThreadPool.
 */

public class MultiReader {
	private ArrayList<MetaFile> metaFiles;
	private Folder folder;
	private ExecutorService executor;
	
	public MultiReader(ArrayList<MetaFile> metaFiles, Folder folder) {
		super();
		setMetaFiles(metaFiles);
		this.folder = folder;
		executor = Executors.newCachedThreadPool();
	} 
	
	public MultiReader(Path dir) {
		this(null, new Folder(dir));
	}
	

	public ArrayList<MetaFile> getMetaFiles() {
		return metaFiles;
	}

	public void setMetaFiles(ArrayList<MetaFile> metaFiles) {
		if(metaFiles == null)
			this.metaFiles = new ArrayList<MetaFile>();
		else
			this.metaFiles = metaFiles;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}
		
	
	public RETCODES analyseFolder() {
		RETCODES retCode = RETCODES.SUCCESS;
		
		try {
			ArrayList<Path> files = folder.getFiles();
			retCode = analyseFiles(files);
			
		} catch(IOException e) {
			retCode = RETCODES.UNKNOWNERROR;
			//e.printStackTrace();
		} catch(Exception e) {
			//System.err.println(e.getMessage());
			retCode = RETCODES.UNKNOWNERROR;
		}
		
		return retCode; 
	}
	
	public RETCODES analyseFiles(ArrayList<Path> files) {							
		/*
		 * Uses threads from the pool for each file.
		 * Threads already created and if all the threads are in use, 
		 * new comers will wait in the queue 
		 */
		for(Path filePath:files) {
			executor.execute(new MultiReaderWorker(metaFiles, Paths.get(filePath.toString())));
		}
			
		//wait all thread to finish.
		executor.shutdown();		
		try {
			  executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch(InterruptedException ei) {
			System.err.println(ei.getMessage());
		}
		return RETCODES.SUCCESS; 
	}
	
	public RETCODES analyseFiles(BlockingQueue<String> fileQueue, IRealTimeResult realTimeResult) {
		String file;		
		MultiReaderWorker worker;
		
		while(true) {
			try {
				file = fileQueue.take();			
				
				if(file == "\0")
					break;
				
				worker = new MultiReaderWorker(metaFiles, Paths.get(file));
				worker.setRealTimeResult(realTimeResult);				
				executor.execute(worker);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		
		//wait all thread to finish.
		executor.shutdown();		
		try {
			  executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch(InterruptedException ei) {
			System.err.println(ei.getMessage());
		}
		
		return RETCODES.SUCCESS;		
	}
	
	
	@Override
	public String toString() {
		String retStr = "";
		
		for(MetaFile mf: metaFiles) {
			retStr += mf + "\n";
		}
		retStr += metaFiles.size() + " of " + folder.getFileCount() + " files have been recognized.\n";
		
		
		return retStr;
	}
	
}

