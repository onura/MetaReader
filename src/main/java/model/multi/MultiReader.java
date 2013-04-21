package model.multi;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


import model.MetaReader.RETCODES;
import model.metafile.MetaFile;

/*
 *  To support analysing various files in a folder in parallel
 *  by using a FixedThreadPool.
 */

public class MultiReader {
	private ArrayList<MetaFile> metaFiles;
	private Folder folder;
	private ExecutorService executor;
	private final int threadCount;
	
	public MultiReader(ArrayList<MetaFile> metaFiles, Folder folder) {
		super();
		setMetaFiles(metaFiles);
		this.folder = folder;
		threadCount = 10;
		executor = Executors.newFixedThreadPool(threadCount);
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
			
			/*
			 * Uses threads from the pool for each file.
			 * Threads already created and if all the threads are in use, 
			 * new comers will wait in the queue 
			 */
			for(Path filePath:files) {
				executor.execute(new MultiReaderWorker(metaFiles, Paths.get(filePath.toString())));
			}
			
		} catch(IOException e) {
			//wait all thread to finish.
			executor.shutdown();		
			try {
				  executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch(InterruptedException ei) {
				  System.err.println(ei.getMessage());
			}
			retCode = RETCODES.UNKNOWNERROR;
		}
		
		//wait all thread to finish.
		executor.shutdown();		
		try {
			  executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch(InterruptedException ei) {
			System.err.println(ei.getMessage());
		}
		return retCode; 
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

