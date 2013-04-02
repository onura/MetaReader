package test;

import model.MetaReader;

public class Test {
	public static void main(String[] args) {
		final String filePath = "test.pdf";
		
		MetaReader reader = new MetaReader();
		reader.analyseFile(filePath);
		reader.getMetaFile().toString();
		
	}
}
