package model.multi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
 * Folder class to get paths from a folder.
 */

public class Folder {
	private Path dir;
	
	public Folder(Path dir) {
		this.dir = dir;
	}
	
	public Path getDir() {
		return dir;
	}
	
	public void setDir(Path dir) {
		this.dir = dir;
	}
	
	public ArrayList<Path> getFiles() throws IOException {
		ArrayList<Path> filePaths = new ArrayList<Path>();
		File directory = getDir().toFile();
		File[] files = directory.listFiles();
				
		
		for(File file:files) {
			filePaths.add(Paths.get(file.getAbsolutePath()));
		}
		
		return filePaths;
	}
	
	public int getFileCount() {
		return getDir().toFile().listFiles().length;
	}
}
