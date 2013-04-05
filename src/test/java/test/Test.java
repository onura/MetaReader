package test;

import model.MetaReader;
import model.MetaReader.RETCODES;
import model.metafile.FileType;

public class Test {
	public static void main(String[] args) {
		final String filePath = "sample.pdf";
		
		MetaReader reader = new MetaReader();
		RETCODES retCode;
		retCode = reader.analyseFile(filePath, FileType.PDF);
		//retCode = reader.analyseFile(filePath);
		
		if(retCode == RETCODES.SUCCESS) {			
			System.out.println(reader.getMetaFile());
		} else if(retCode == RETCODES.FILENOTFOUND) {
			System.out.println("File not found.");
		} else if(retCode == RETCODES.UNKNOWNTYPE) {
			System.out.println("Unknown file type");
		} else if(retCode == RETCODES.WRONGFILETYPE) {
			System.out.println("Wrong file type");
		} else {
			System.out.println("Unknown error.");
		}
		
	}
}
