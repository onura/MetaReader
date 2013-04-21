package model.multi;

import java.nio.file.Path;
import java.util.ArrayList;

import model.MetaReader;
import model.MetaReader.RETCODES;
import model.metafile.MetaFile;

/*
 * A Worker class that is responsible for analysing a file 
 * and adding the result into the meta files list.
 */

public class MultiReaderWorker implements Runnable {
	
	private ArrayList<MetaFile> metaFiles;
	private Path filePath;
	
	public MultiReaderWorker(ArrayList<MetaFile> metaFiles, Path filePath) {
		this.metaFiles = metaFiles;
		this.filePath = filePath;
	}
	
	//add result to the list in a thread safe way
	private synchronized void addMetaFile(MetaFile metaFile) {
		metaFiles.add(metaFile);
	}
	
	//thread's main method
	public void run() {
		MetaReader reader = new MetaReader();
		RETCODES ret = reader.analyseFile(filePath) ;
		
		if(ret == RETCODES.SUCCESS)			
			addMetaFile(reader.getMetaFile());
		
	}

	
}
