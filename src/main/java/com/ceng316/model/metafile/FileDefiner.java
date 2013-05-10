/*
 * Keeps a file's extractor and identifer objects.
 */
package com.ceng316.model.metafile;

import com.ceng316.model.extractors.IExtractor;
import com.ceng316.model.identifiers.IIdentifier;

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
