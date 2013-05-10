/*
 * Common identifier interface for all file formats. 
 */
package com.ceng316.model.identifiers;

import java.io.File;
import java.io.IOException;


public interface IIdentifier {

	public boolean identify(File file) throws IOException;
}
