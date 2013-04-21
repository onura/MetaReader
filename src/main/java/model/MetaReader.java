/*
 * Provides methods to read metadata of files
 * or file streams. 
 */

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import model.metafile.FileInfo;
import model.metafile.FileType;
import model.metafile.FileTypeLookupTable;
import model.metafile.MetaFile;

public class MetaReader {
	private MetaFile metaFile;
	private FileTypeLookupTable fileTypeLookupTable;
	
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
		this.fileTypeLookupTable = new FileTypeLookupTable();		
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
