package model.extractors;

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

import model.metafile.Location;
import model.metafile.MetaData;

public class DOCXExtraxtor implements IExtractor {

	public MetaData extract(File file) throws IOException {
		MetaData metaData = new MetaData();
		
		Parser docxParser = new AutoDetectParser();
        InputStream stream = new FileInputStream(file);
        Metadata metadata = new Metadata();
        
        try {
			docxParser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
			metaData.setApplication(metadata.get(OfficeOpenXMLExtended.APPLICATION));
			metaData.setCreationDate(metadata.getDate(TikaCoreProperties.CREATED));
			metaData.setModificationDate(metadata.getDate(TikaCoreProperties.PRINT_DATE));
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
	
	private HashMap<String, String> getExtra(Metadata docxInfo){
		HashMap<String, String> extra = new HashMap<String, String>();		
            extra.put("Publisher", docxInfo.get(TikaCoreProperties.PUBLISHER));
            extra.put("Modifier", docxInfo.get(TikaCoreProperties.MODIFIER)); 
            extra.put("Title", docxInfo.get(TikaCoreProperties.TITLE));
            extra.put("Subject", docxInfo.get(OfficeOpenXMLCore.SUBJECT));
            extra.put("Keyword", docxInfo.get(Office.KEYWORDS));
            extra.put("Template", docxInfo.get(OfficeOpenXMLExtended.TEMPLATE));
            extra.put("Paragraph Count", docxInfo.get(Office.PARAGRAPH_COUNT));
            extra.put("Page Count", docxInfo.get(Office.PAGE_COUNT));
            extra.put("Word Count", docxInfo.get(Office.WORD_COUNT));
            
		return extra;
	}

}
