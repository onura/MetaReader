package model.identifiers;

import java.io.File;
import java.io.IOException;

import org.apache.tika.Tika;

public class JPEGIdentifier implements IIdentifier {
	private final String jpegType = "image/jpeg";
	
	public boolean identify(File file) throws IOException {
		Tika tika = new Tika();
		
		try {
			String type = tika.detect(file);
			if (jpegType.equalsIgnoreCase(type))
				return true;			
			return false;
		} catch (IOException e) {
			return false;
		}
		
		
	}

}
