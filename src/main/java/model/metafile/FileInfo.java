package model.metafile;

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
	
	public void guessType() {
		;
	}
	
	public boolean checkType() {
		return false;
	}
	
	
}
