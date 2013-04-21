/* 
 * Checks and guesses type of files and also
 * prepares properties of file.
 * */
package model.metafile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import model.identifiers.IIdentifier;

public class FileInfo {
	private String fileName;
	private Path filePath;
	private FileType type;
	private IIdentifier identifier;
	
	public FileInfo(String fileName, Path filePath, FileType type,
			IIdentifier identifier) {
		super();
		setFileName(fileName);
		setFilePath(filePath);
		setType(type);
		setIdentifier(identifier);
	}

	public FileInfo(Path filePath, FileType type) {
		this(null, filePath, type, 
				new FileTypeLookupTable().getFileDefiner(type).getIdentifier());		
	}
	
	public FileInfo(Path filePath) {
		this(null, filePath, null, null);
	}
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}

	public FileType getType() {
		return type;
	}

	public void setType(FileType type) {
		this.type = type;
	}

	public IIdentifier getIdentifier() {		
		return identifier;
	}

	public void setIdentifier(IIdentifier identifier) {
		this.identifier = identifier;
	}
	
	
	
	public boolean guessType() throws IOException {				
		FileTypeLookupTable fileTypeLookupTable = new FileTypeLookupTable();
		for( FileType t : FileType.values()){			
			setIdentifier(fileTypeLookupTable.getFileDefiner(t).getIdentifier());			
			if (getIdentifier().identify(new File(getFilePath().toString()))){
				setType(t);				
				return true;	
			}			
			
		}		
		return false;
	}
	
	public boolean checkType() throws IOException {
		
		if(getIdentifier().identify(new File(getFilePath().toString())))
			return true;		
		return false;
	}
	
	public void findFileName() throws IOException {
		
        setFileName(getFilePath().getName(getFilePath().getNameCount()-1).toString());        
	}
	
	public String FindAbsolutePath(){
		return Paths.get(new File(getFilePath().toString()).getAbsolutePath()).toString();
	}
	
	@Override
	public String toString() {
		return " Name : " + getFileName() + 
				"\n Path : " + FindAbsolutePath() + 
				"\n Type : " + getType();
	}
	
	
}
