package model.multi;

import java.nio.file.Path;
import java.util.ArrayList;

import model.MetaReader;
import model.MetaReader.RETCODES;
import model.metafile.MetaFile;
import model.realtime.IRealTimeResult;

/*
 * A Worker class that is responsible for analysing a file 
 * and adding the result into the meta files list.
 */

public class MultiReaderWorker implements Runnable {
	
	private ArrayList<MetaFile> metaFiles;
	private Path filePath;
	private IRealTimeResult realTimeResult;
	
	public MultiReaderWorker(ArrayList<MetaFile> metaFiles, Path filePath) {
		this.metaFiles = metaFiles;
		this.filePath = filePath;
	}
		
	public void setRealTimeResult(IRealTimeResult realTimeResult) {
		this.realTimeResult = realTimeResult;
	}
	
	public IRealTimeResult getRealTimeResult() {
		return realTimeResult;
	}
	
	//thread's main method
	public void run() {
		MetaReader reader = new MetaReader();
		RETCODES ret = reader.analyseFile(filePath) ;
		
		if(ret == RETCODES.SUCCESS) {
			//add result to the list in a thread safe way
			synchronized (metaFiles) {
				metaFiles.add(reader.getMetaFile());
				
				if(realTimeResult != null)
					realTimeResult.use(reader.getMetaFile());
			}			
			
			
			
		}
		
	}

	
}
