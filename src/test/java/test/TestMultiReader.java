package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;


import org.junit.Test;

import com.ceng316.model.multi.MultiReader;

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
