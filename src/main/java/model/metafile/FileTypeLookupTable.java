package model.metafile;

import model.extractors.PDFExtractor;
import model.identifiers.PDFIdentifier;

public class FileTypeLookupTable {
	private FileDefiner definers[];
	
	public FileTypeLookupTable() {
		definers = new FileDefiner[FileType.values().length];
		
		definers[FileType.PDF.ordinal()] = new FileDefiner(new PDFExtractor(), new PDFIdentifier());		
	}
	
	public FileDefiner getFileDefiner(FileType type) {
		return definers[type.ordinal()];
	}
}
