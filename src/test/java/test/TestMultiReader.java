package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;

import model.multi.MultiReader;

import org.junit.Test;

public class TestMultiReader {

	@Test
	public void test() throws IOException {
		MultiReader multiReader = new MultiReader(Paths.get("/tmp/sample files"));
		assertNotNull(multiReader.getFolder());
		assertNotNull(multiReader.getFolder().getFiles());
		
		multiReader.analyseFolder();
		assertNotNull(multiReader.getMetaFiles());		
		assertTrue(multiReader.getMetaFiles().size() > 0);		
		//assertTrue(multiReader.getMetaFiles().size() == 64);
		
		
		System.out.println(multiReader);
	}

}
