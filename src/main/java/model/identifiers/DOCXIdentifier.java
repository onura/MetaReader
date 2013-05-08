package model.identifiers;

import java.io.File;
import java.io.IOException;

import org.apache.tika.Tika;

public class DOCXIdentifier implements IIdentifier {
	private final String docxType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

	public boolean identify(File file) throws IOException {
		Tika tika = new Tika();
		
		try {
			String type = tika.detect(file);
			if (docxType.equalsIgnoreCase(type))
				return true;			
			return false;
		} catch (IOException e) {
			return false;
		}
	}

}
