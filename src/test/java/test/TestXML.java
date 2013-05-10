package test;

import java.nio.file.Paths;

import com.ceng316.model.MetaReader;
import com.ceng316.model.MetaReader.RETCODES;
import com.ceng316.model.multi.MultiReader;
import com.ceng316.model.util.xml.XMLSaver;


public class TestXML {
	public static void main(String[] args) {		
		MetaReader reader = new MetaReader();
		RETCODES retCode;
				
		retCode = reader.analyseFile(Paths.get("/tmp/data/onur.docx"));
		
		if(retCode == RETCODES.SUCCESS) {			
			XMLSaver.save("/tmp/xml/test.xml", reader.getMetaFile());
		}
		
		MultiReader multiReader = new MultiReader(Paths.get("/tmp/data"));
		retCode = multiReader.analyseFolder();
		
		if(retCode == RETCODES.SUCCESS) {			
			XMLSaver.save("/tmp/xml/test2.xml", multiReader.getMetaFiles());
		}
		
	}
}
