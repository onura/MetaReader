package com.ceng316.model.util.xml;


import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.ceng316.model.metafile.MetaFile;


@SuppressWarnings("restriction")
public class XMLSaver {
	//save a metafile ojbect into a xml file 
	public static void save(String filePath, MetaFile metaFile) {
		
		try {
			File xmlFile = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(MetaFile.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(metaFile, xmlFile);
			
		} catch(JAXBException e) {
			e.printStackTrace();
		}	
	}
	
	//save a list of metafile objects into a xml file
	public static void save(String filePath, ArrayList<MetaFile> metaFiles) {
		
		try {
			File xmlFile = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(XmlMetaFiles.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(new XmlMetaFiles(metaFiles), xmlFile);
			
		} catch(JAXBException e) {
			e.printStackTrace();
		}
		
	}
}
