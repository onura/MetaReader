package model.identifiers;

import java.io.File;
import java.io.IOException;

import org.apache.tika.Tika;

public class XSLXIdentifier implements IIdentifier {
	private final String xslxType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public boolean identify(File file) throws IOException {
		Tika tika = new Tika();
		
		try {
			String type = tika.detect(file);
			if (xslxType.equalsIgnoreCase(type))
				return true;			
			return false;
		} catch (IOException e) {
			return false;
		}
	}

}
