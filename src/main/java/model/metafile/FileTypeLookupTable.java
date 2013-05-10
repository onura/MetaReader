/*
 * Keeps the FileDefiner object for the supported file types. 
 */

package model.metafile;

import model.extractors.*;
import model.identifiers.*;

public class FileTypeLookupTable {
	private FileDefiner definers[];
	
	public FileTypeLookupTable() {		
		definers = new FileDefiner[FileType.values().length];
		
		definers[FileType.PDF.ordinal()] = new FileDefiner(new PDFExtractor(), new PDFIdentifier());		
		definers[FileType.JPG.ordinal()] = new FileDefiner(new JPEGExtractor(), new JPEGIdentifier());
		definers[FileType.DOCX.ordinal()] = new FileDefiner(new DOCXExtraxtor(), new DOCXIdentifier());
		definers[FileType.XLSX.ordinal()] = new FileDefiner(new XLSXExtraxtor(), new XLSXIdentifier());
		definers[FileType.ODT.ordinal()] = new FileDefiner(new ODTExtractor(), new ODTIdentifier());
		
	}
	
	public FileDefiner getFileDefiner(FileType type) {
		return definers[type.ordinal()];
	}
}
