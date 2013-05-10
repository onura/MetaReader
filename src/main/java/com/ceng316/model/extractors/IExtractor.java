/*
 * Common extractor interface for all file formats. 
 */
package com.ceng316.model.extractors;

import java.io.File;
import java.io.IOException;

import com.ceng316.model.metafile.MetaData;


public interface IExtractor {

	public MetaData extract(File file) throws IOException;	
}
