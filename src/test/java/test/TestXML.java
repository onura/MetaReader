package test;

import java.nio.file.Paths;

import model.MetaReader;
import model.MetaReader.RETCODES;
import model.multi.MultiReader;
import model.util.xml.XMLSaver;

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
