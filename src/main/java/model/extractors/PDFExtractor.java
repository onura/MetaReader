/*
 * Extracts metadata from PDF files.
 */

package model.extractors;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import model.metafile.Location;
import model.metafile.MetaData;

public class PDFExtractor implements IExtractor{

	public MetaData extract(File file) throws IOException {
		MetaData metaData = new MetaData();
		PDDocument pdf = PDDocument.load(file);
		PDDocumentInformation pdfInfo = pdf.getDocumentInformation();
				
		metaData.setApplication(pdfInfo.getProducer() +" "+ pdfInfo.getCreator());
		if(pdfInfo.getModificationDate() != null)
			metaData.setModificationDate(pdfInfo.getModificationDate().getTime());
		if(pdfInfo.getCreationDate() != null)
			metaData.setCreationDate(pdfInfo.getCreationDate().getTime());
		metaData.setOwner(pdfInfo.getAuthor());
		metaData.setExtraData(this.getExtra(pdfInfo));
		metaData.setLocation(new Location(null, null));
		
		pdf.close();		
		return metaData;
	}
	
	private HashMap<String, String> getExtra(PDDocumentInformation pdfInfo){
		HashMap<String, String> extra = new HashMap<String, String>();
		extra.put("Keywords", pdfInfo.getKeywords());
		extra.put("Title", pdfInfo.getTitle());		
		extra.put("Subject", pdfInfo.getSubject());
		
		return extra;
	}

}
