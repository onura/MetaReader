package model.metafile;

import java.io.File;
import java.io.IOException;

import model.extractors.IExtractor;

public class MetaFile {
	private FileInfo fileInfo;
	private MetaData metaData;
	private IExtractor extractor;
	
	public MetaFile(FileInfo fileInfo, MetaData metaData, IExtractor extractor) {
		super();
		this.fileInfo = fileInfo;
		this.metaData = metaData;
		this.extractor = extractor;
	}
	
	public MetaFile(FileInfo fileInfo, IExtractor extractor) {
		this(fileInfo, null,extractor);
	}
	
	public MetaFile() {
		this(null, null, null);
	}
	
	public FileInfo getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	public MetaData getMetaData() {
		return metaData;
	}
	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}
	public IExtractor getExtractor() {
		return extractor;
	}
	public void setExtractor(IExtractor extractor) {
		this.extractor = extractor;
	}
	
	public void extractData(File file) throws IOException{
		metaData = extractor.extract(file);
	}
	
	public void gatherFileInfo() {
		;
	}
	
	@Override
	public String toString() {
		return "File Info:\n" + fileInfo + "\n\n" +
				"Meta Data:\n" + metaData; 
	}
	
}
