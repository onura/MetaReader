/*
 * Keeps a file's extractor and identifer objects.
 */
package model.metafile;

import model.extractors.IExtractor;
import model.identifiers.IIdentifier;

public class FileDefiner {
	private final IExtractor extractor;
	private final IIdentifier identifier;
	
	public FileDefiner(IExtractor extractor, IIdentifier identifier) {
		this.extractor = extractor;
		this.identifier = identifier;
	}
	
	public IExtractor getExtractor() {
		return extractor;
	}
	
	public IIdentifier getIdentifier() {
		return identifier;
	}
	
}
