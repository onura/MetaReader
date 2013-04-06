package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import model.extractors.PDFExtractor;
import model.metafile.FileInfo;
import model.metafile.FileType;
import model.metafile.MetaData;
import model.metafile.MetaFile;

public class MetaReader {
	private MetaFile metaFile;
	
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
	}

	public MetaFile getMetaFile() {
		return metaFile;
	}

	public void setMetaFile(MetaFile metaFile) {
		this.metaFile = metaFile;
	}
	
	
	public RETCODES analyseFile(Path filePath, FileType fileType) {		
		//TODO: add selectors for identifiers and extractors
		
		FileInfo fileInfo = new FileInfo(filePath, fileType);
		
		try {
			if(!fileInfo.checkType())
				return RETCODES.WRONGFILETYPE;
				
			fileInfo.findFileName();

			metaFile = new MetaFile(fileInfo, new PDFExtractor());
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

			metaFile = new MetaFile(fileInfo, new PDFExtractor());
			metaFile.extractData(new File(filePath.toString()));			
			
		} catch(FileNotFoundException e) {
			return RETCODES.FILENOTFOUND; 
		} catch(IOException e) {
			return RETCODES.UNKNOWNERROR;
		}
		
		return RETCODES.SUCCESS;
	}

	
}
