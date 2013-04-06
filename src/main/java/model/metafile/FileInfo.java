package model.metafile;

import java.io.IOException;
import java.nio.file.Path;

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
		this(filePath.getName(filePath.getNameCount()-1).toString(), filePath, type, null);		
	}
	
	public FileInfo(Path filePath) {
		this(filePath.getName(filePath.getNameCount()-1).toString(), filePath, null, null);
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
		String extension = null;
		int i = getFileName().lastIndexOf('.');
		
		if (i > 0) {
		    extension = getFileName().substring(i+1);
		}
		
		try{
			setType( FileType.valueOf(extension) ); 
		}catch(IllegalArgumentException e){
			return false;
		}
						
		return true;
	}
	
	public boolean checkType() throws IOException {
		
		for( FileType t : FileType.values())
			if(t.name().equals(this.type.toString()))
				return true;		
		return false;
	}
	
	public void findFileName() throws IOException {
		
		if( getFilePath().isAbsolute() == true ) {
            setFileName(filePath.getName(filePath.getNameCount()-1).toString());
        }
	}
	
	@Override
	public String toString() {
		return "File Name : " + this.fileName + 
				"\nFile Path : " + this.filePath + 
				"\nFile Type : " + this.type;
	}
	
	
}
