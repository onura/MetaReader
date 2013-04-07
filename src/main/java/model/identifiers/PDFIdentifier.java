package model.identifiers;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFIdentifier implements IIdentifier{
	private static String pdfHeader = "%PDF-";

	public boolean identify(File file) throws IOException {
		try{
			PDDocument pdf = PDDocument.load(file);
			COSDocument pdfCOS = pdf.getDocument();
			if(pdfCOS.getHeaderString().contains(pdfHeader)){
				pdf.close();
				return true;
			}
		}catch(IOException e){
			return false;
		}
		return false;		
	}

}
