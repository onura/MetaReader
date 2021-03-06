/*
 * Identifies PDF files according to standard PDF file header information. 
 * */
package com.ceng316.model.identifiers;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFIdentifier implements IIdentifier{
	private final String pdfHeader = "%PDF-";

	public boolean identify(File file) throws IOException {		
		PDDocument pdf = null;
		try{			
			pdf = PDDocument.load(file);	
			COSDocument pdfCOS = pdf.getDocument();
			if(pdfCOS.getHeaderString().contains(pdfHeader)){
				pdf.close();
				return true;
			}		
			return false;
		}catch (IOException e){			
			if (pdf != null)
				pdf.close();
			return false;
		}
	}

}
