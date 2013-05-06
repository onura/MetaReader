package controller;

import java.nio.file.Path;
import java.util.Date;
import java.util.Map.Entry;

import view.FileUI;

import model.MetaReader;
import model.MetaReader.RETCODES;
import model.metafile.FileType;

public class MetaReaderController {
	private FileUI fileUI;
	
	public MetaReaderController(FileUI fileUI){
		this.fileUI = fileUI;
	}
	
	public void getFileMetadata(Path filePath){
		
		MetaReader reader = new MetaReader();
		RETCODES retCode;
		if(fileUI.controlRdtBtn() != -1){
			retCode = reader.analyseFile(filePath,FileType.values()[fileUI.controlRdtBtn()]);
		}else{
			retCode = reader.analyseFile(filePath);
		}
		
		reader.getMetaFile();
		reader.getMetaFile().getMetaData();
		reader.getMetaFile().getFileInfo();
				
		if(retCode == RETCODES.SUCCESS){
			getMetadata(reader);
		}else{
			getErrorMsg(retCode);
		}
	}
	
	/* The file metadata is gathered and filled to suitable places.*/
	public void getMetadata(MetaReader reader){
		String extra = "";
		
		fileUI.getFileData().getTextName().setText(control(reader.getMetaFile().getFileInfo().getFileName()));
		fileUI.getFileData().getTextPath().setText(control(reader.getMetaFile().getFileInfo().getFilePath().toString()));
		fileUI.getFileData().getTextType().setText(control(reader.getMetaFile().getFileInfo().getType().toString()));
			
		fileUI.getFileData().getTextOwner().setText(control(reader.getMetaFile().getMetaData().getOwner()));
		fileUI.getFileData().getTextMdf().setText(dateControl(reader.getMetaFile().getMetaData().getModificationDate()));
		fileUI.getFileData().getTextCreation().setText(dateControl(reader.getMetaFile().getMetaData().getCreationDate()));
		fileUI.getFileData().getTextLoc().setText(control(reader.getMetaFile().getMetaData().getLocation().toString()));
		fileUI.getFileData().getTextPlatform().setText(control(reader.getMetaFile().getMetaData().getPlatform()));
		fileUI.getFileData().getTextApp().setText(control(reader.getMetaFile().getMetaData().getApplication()));
			
		for ( Entry<String, String> extraData :reader.getMetaFile().getMetaData().getExtraData().entrySet()){
			extra += extraData.getKey() + ":" + control(extraData.getValue()) + "\n";
		}
		fileUI.getFileData().getTextExtraData().setText(extra);
		
	}
	
	/* incomming value control */
	public String control(String text){
		if (text == null)
			return "Unknown";
		return text;
	}
	
	public String dateControl(Date date){		
		if (date != null)
			return date.toString();
		return "Unknown";
	}
	
	/* error message arrangement */
	public void getErrorMsg(RETCODES retCode){
		String errorMSG = "Some thing was wrong.";
		
		switch (retCode) {
		case FILENOTFOUND:
			errorMSG = "File couldn't found.";				
			break;
		case UNKNOWNTYPE:
			errorMSG = "File type doesn't supported yet.";
			break;
		case WRONGFILETYPE:
			errorMSG = "The selected file type doesn't match the real file type.";
			break;
		case UNKNOWNERROR:
			errorMSG = "An unknown error occured.";
			break;
		default:
			break;
		}
		fileUI.setMsgBox(errorMSG);
	}

}
