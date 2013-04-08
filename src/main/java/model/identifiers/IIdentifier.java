/*
 * Common identifier interface for all file formats. 
 */
package model.identifiers;

import java.io.File;
import java.io.IOException;


public interface IIdentifier {

	public boolean identify(File file) throws IOException;
}
