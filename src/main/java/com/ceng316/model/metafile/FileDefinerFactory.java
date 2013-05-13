/*
 * Keeps the FileDefiner object for the supported file types. 
 */

package com.ceng316.model.metafile;

import com.ceng316.model.extractors.*;
import com.ceng316.model.identifiers.*;

public class FileDefinerFactory {
	private FileDefiner definers[];
	
	private static FileDefinerFactory instance;
	
	protected FileDefinerFactory() {		
		definers = new FileDefiner[FileType.values().length];
		
		definers[FileType.PDF.ordinal()] = new FileDefiner(new PDFExtractor(), new PDFIdentifier());		
		definers[FileType.JPG.ordinal()] = new FileDefiner(new JPEGExtractor(), new JPEGIdentifier());
		definers[FileType.DOCX.ordinal()] = new FileDefiner(new DOCXExtraxtor(), new DOCXIdentifier());
		definers[FileType.XLSX.ordinal()] = new FileDefiner(new XLSXExtraxtor(), new XLSXIdentifier());
		definers[FileType.ODT.ordinal()] = new FileDefiner(new ODTExtractor(), new ODTIdentifier());
		
	}
	
	public static FileDefinerFactory getInstance() {
		if(instance == null)
			return new FileDefinerFactory();
		return instance;
	}
	
	public FileDefiner getFileDefiner(FileType type) {
		return definers[type.ordinal()];
	}
}
