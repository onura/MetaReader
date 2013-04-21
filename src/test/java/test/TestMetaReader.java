package test;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import model.MetaReader;
import model.MetaReader.RETCODES;
import model.metafile.FileType;

public class TestMetaReader {

	@org.junit.Test
	public void test() {
		final Path filePath = Paths.get("sample.pdf");		
		MetaReader reader = new MetaReader();
		RETCODES retCode;
		
		retCode = reader.analyseFile(filePath);
		
		assertNotNull(reader.getMetaFile());
		assertNotNull(reader.getMetaFile().getMetaData());
		assertNotNull(reader.getMetaFile().getFileInfo());
		
		assertTrue(reader.getMetaFile().getFileInfo().getFileName().equals("sample.pdf"));
		assertTrue(reader.getMetaFile().getFileInfo().getType() == FileType.PDF);		
		
		assertTrue(retCode == RETCODES.SUCCESS);
		
		System.out.println(reader.getMetaFile());
	}
	

}
