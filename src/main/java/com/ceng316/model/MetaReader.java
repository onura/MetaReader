/*
 * Provides methods to read metadata of files
 * or file streams. 
 */

package com.ceng316.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import com.ceng316.model.metafile.FileInfo;
import com.ceng316.model.metafile.FileType;
import com.ceng316.model.metafile.FileDefinerFactory;
import com.ceng316.model.metafile.MetaFile;


public class MetaReader {
	private MetaFile metaFile;
	private FileDefinerFactory fileTypeLookupTable;
	
	public static enum RETCODES {
		SUCCESS,
		UNKNOWNTYPE,
		FILENOTFOUND,
		WRONGFILETYPE,
		UNKNOWNERROR
	}
	
	public MetaReader() {
		super();		
		this.metaFile = new MetaFile();				
		this.fileTypeLookupTable = FileDefinerFactory.getInstance();		
	}

	public MetaFile getMetaFile() {
		return metaFile;
	}

	public void setMetaFile(MetaFile metaFile) {
		this.metaFile = metaFile;
	}
	
	
	public RETCODES analyseFile(Path filePath, FileType fileType) {		
		FileInfo fileInfo = new FileInfo(filePath, fileType);
		
		try {
			if(!fileInfo.checkType())
				return RETCODES.WRONGFILETYPE;
				
			fileInfo.findFileName();

			metaFile = new MetaFile(fileInfo, fileTypeLookupTable.getFileDefiner(fileInfo.getType()).getExtractor());
			metaFile.extractData(new File(filePath.toString()));			
			
		} catch(FileNotFoundException e) {
			return RETCODES.FILENOTFOUND; 
		} catch(IOException e) {
			return RETCODES.UNKNOWNERROR;
		}
			
		return RETCODES.SUCCESS;
		
	}
	
	public RETCODES analyseFile(Path filePath) {
		FileInfo fileInfo = new FileInfo(filePath);		
		
		try {			
			
			if(!fileInfo.guessType())
				return RETCODES.UNKNOWNTYPE;
			
			fileInfo.findFileName();

			metaFile = new MetaFile(fileInfo, fileTypeLookupTable.getFileDefiner(fileInfo.getType()).getExtractor());
			metaFile.extractData(new File(filePath.toString()));			
			
		} catch(FileNotFoundException e) {
			return RETCODES.FILENOTFOUND; 
		} catch(IOException e) {
			return RETCODES.UNKNOWNERROR;
		}
		
		return RETCODES.SUCCESS;
	}
	
}
