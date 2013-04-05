package model.metafile;

import java.io.IOException;

import model.identifiers.IIdentifier;

public class FileInfo {
	private String fileName;
	private String filePath;
	private FileType type;
	private IIdentifier identifier;
	
	public FileInfo(String fileName, String filePath, FileType type,
			IIdentifier identifier) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.type = type;
		this.identifier = identifier;
	}

	public FileInfo(String filePath, FileType type) {
		this(null, filePath, type, null);		
	}
	
	public FileInfo(String filePath) {
		this(null, filePath, null, null);
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
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
		return true;
	}
	
	public boolean checkType() throws IOException {
		return true;
	}
	
	public void findFileName() throws IOException {
		//TODO: fill it so that it finds the fileName of the file
		;
	}
	
	
}
