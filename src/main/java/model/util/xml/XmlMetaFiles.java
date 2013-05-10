package model.util.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

import model.metafile.MetaFile;

/*
 * A wrapper class to make an ArrayList<MetaFile>
 * JAXB marshallable
 */

@SuppressWarnings("restriction")
@XmlRootElement
class XmlMetaFiles extends ArrayList<MetaFile> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8640359439316748680L;

	//Default constructor is needed  by JAXB
	public XmlMetaFiles() {
		;
	}
	
	//Get original list in the constructor
	public XmlMetaFiles(ArrayList<MetaFile> metaFiles) {
		for(MetaFile mf:metaFiles) {
			this.add(mf);
		}
	}
	
	//put a self getter trick 
	@XmlElement
	public List<MetaFile> getMetaFiles() {
		return this;
	}
}