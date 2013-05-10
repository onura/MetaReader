/*
 * Keeps standard information and Metadata of a File.
 */

package com.ceng316.model.metafile;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ceng316.model.extractors.IExtractor;


@SuppressWarnings("restriction")
@XmlRootElement
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
	@XmlElement
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	public MetaData getMetaData() {
		return metaData;
	}
	@XmlElement
	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}
	public IExtractor getExtractor() {
		return extractor;
	}
	@XmlTransient
	public void setExtractor(IExtractor extractor) {
		this.extractor = extractor;
	}
	
	public void extractData(File file) throws IOException{
		metaData = extractor.extract(file);
	}
	
	@Override
	public String toString() {
		return "File Info:\n" + fileInfo + "\n\n" +
				"Meta Data:\n" + metaData; 
	}
	
}
