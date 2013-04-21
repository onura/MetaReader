/*
 * Keeps the FileDefiner object for the supported file types. 
 */

package model.metafile;

import model.extractors.JPEGExtractor;
import model.extractors.PDFExtractor;
import model.identifiers.JPEGIdentifier;
import model.identifiers.PDFIdentifier;

public class FileTypeLookupTable {
	private FileDefiner definers[];
	
	public FileTypeLookupTable() {
		definers = new FileDefiner[FileType.values().length];
		
		definers[FileType.PDF.ordinal()] = new FileDefiner(new PDFExtractor(), new PDFIdentifier());
		definers[FileType.JPEG.ordinal()] = new FileDefiner(new JPEGExtractor(), new JPEGIdentifier());
	}
	
	public FileDefiner getFileDefiner(FileType type) {
		return definers[type.ordinal()];
	}
}
