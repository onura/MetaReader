/*
 * Common extractor interface for all file formats. 
 */
package model.extractors;

import java.io.File;
import java.io.IOException;

import model.metafile.MetaData;

public interface IExtractor {

	public MetaData extract(File file) throws IOException;	
}
