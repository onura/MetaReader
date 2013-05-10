package model.identifiers;

import java.io.File;
import java.io.IOException;

import org.apache.tika.Tika;

public class ODTIdentifier implements IIdentifier {
	private final String odtType = "application/vnd.oasis.opendocument.text";
	
	
	public boolean identify(File file) throws IOException {
		Tika tika = new Tika();
		
		try {
			String type = tika.detect(file);
			if (odtType.equalsIgnoreCase(type))
				return true;			
			return false;
		} catch (IOException e) {
			return false;
		}
	}

}
