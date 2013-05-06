/*
 * extracts metadatas from JPEG file
 * */
package model.extractors;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.jpeg.JpegParser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import model.metafile.Location;
import model.metafile.MetaData;

public class JPEGExtractor implements IExtractor {

	public MetaData extract(File file) throws IOException {
		MetaData metaData = new MetaData();
		
		JpegParser jpegParser = new JpegParser();
        InputStream stream = new FileInputStream(file);
		Metadata metadata = new Metadata();
		
        try {
			jpegParser.parse(stream, new DefaultHandler(), metadata, new ParseContext());

			metaData.setApplication(metadata.get(Metadata.SOFTWARE));
			metaData.setCreationDate(metadata.getDate(Metadata.ORIGINAL_DATE));
			metaData.setModificationDate(metadata.getDate(Metadata.LAST_MODIFIED));
			metaData.setExtraData(this.getExtra(metadata));
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
	
	private HashMap<String, String> getExtra(Metadata jpegInfo){
		HashMap<String, String> extra = new HashMap<String, String>();		
            extra.put("Image Height", jpegInfo.get(Metadata.IMAGE_LENGTH));
            extra.put("Image Width", jpegInfo.get(Metadata.IMAGE_WIDTH));
            extra.put("Camera Name", jpegInfo.get(Metadata.EQUIPMENT_MAKE));
            extra.put("Camera Model", jpegInfo.get(Metadata.EQUIPMENT_MODEL));
            extra.put("Flash Fired", jpegInfo.get(Metadata.FLASH_FIRED));
        
		return extra;
	}

}
