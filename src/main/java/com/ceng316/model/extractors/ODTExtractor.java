package com.ceng316.model.extractors;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.Office;
import org.apache.tika.metadata.OfficeOpenXMLCore;
import org.apache.tika.metadata.OfficeOpenXMLExtended;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ceng316.model.metafile.Location;
import com.ceng316.model.metafile.MetaData;


public class ODTExtractor implements IExtractor {

	public MetaData extract(File file) throws IOException {
		MetaData metaData = new MetaData();
		
		Parser odtParser = new AutoDetectParser();
        InputStream stream = new FileInputStream(file);
        Metadata metadata = new Metadata();
        
        try {
			odtParser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
			metaData.setApplication(metadata.get(OfficeOpenXMLExtended.APPLICATION));
			metaData.setCreationDate(metadata.getDate(TikaCoreProperties.CREATED));
			metaData.setModificationDate(metadata.getDate(TikaCoreProperties.MODIFIED));
			metaData.setOwner(metadata.get(TikaCoreProperties.CREATOR));
			metaData.setPlatform(metadata.get(Metadata.SOFTWARE));
			metaData.setExtraData(getExtra(metadata));
			if (metadata.get(Metadata.LATITUDE) != null && metadata.get(Metadata.ALTITUDE) != null)
				metaData.setLocation(new Location(new Double(metadata.get(Metadata.ALTITUDE)), new Double(metadata.get(Metadata.LATITUDE))));
			else
				metaData.setLocation(new Location(null, null));
			
		} catch (SAXException e) {
			System.out.println(e.getClass());
		} catch (TikaException e) {
			System.out.println(e.getClass());
		}
        
		return metaData;
	}
	
	private HashMap<String, String> getExtra(Metadata odtInfo){
		HashMap<String, String> extra = new HashMap<String, String>();		
            extra.put("Modifier", odtInfo.get(TikaCoreProperties.MODIFIER)); 
            extra.put("Title", odtInfo.get(TikaCoreProperties.TITLE));
            extra.put("Subject", odtInfo.get(OfficeOpenXMLCore.SUBJECT));
            extra.put("Paragraph Count", odtInfo.get(Office.PARAGRAPH_COUNT));
            extra.put("Page Count", odtInfo.get(Office.PAGE_COUNT));
            extra.put("Word Count", odtInfo.get(Office.WORD_COUNT));
            
		return extra;
	}

}
