package model.metafile;

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
	
	public void extractData(){
		;
	}
	
	public void gatherFileInfo() {
		;
	}
	
}
