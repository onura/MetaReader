package controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

import model.MetaReader.RETCODES;
import model.metafile.MetaFile;
import model.multi.MultiReader;

import view.FolderUI;

public class MultiReaderController {
	
	private FolderUI folderUI;
	private RETCODES retCode;
	private ArrayList<MetaFile> metaFiles;
	
	public MultiReaderController(FolderUI folderUI){
		this.folderUI = folderUI;
		this.metaFiles = new ArrayList<MetaFile>();
	}
	
	public void getFilesMetadata(Path folderPath){
	
		MultiReader multiReader = new MultiReader(folderPath);	
		
		retCode = multiReader.analyseFolder();
		
		if (retCode == RETCODES.SUCCESS){
			try {
				multiReader.getFolder();
				multiReader.getFolder().getFiles();
				multiReader.analyseFolder();
				
				for (int i = 0; i < multiReader.getMetaFiles().size(); i++){
					folderUI.addFileNames(multiReader.getMetaFiles().get(i).getFileInfo().getFileName(),i);
					metaFiles.add(multiReader.getMetaFiles().get(i));
				}	
				
			} catch (IOException e) {
				folderUI.setMsgBox("There is an error.");
			}	
			
		}else{
			getErrorMsg(retCode);
		}	
		
	}
	
	public RETCODES getRetCode(){
		return retCode;
	}
	
	
	public void setMetaFile(int index){
		String extra = "";
		
		if (index >= 0){			
			folderUI.getFileData().getTextName().setText(control(metaFiles.get(index).getFileInfo().getFileName()));
			folderUI.getFileData().getTextPath().setText(control(metaFiles.get(index).getFileInfo().getFilePath().toString()));
			folderUI.getFileData().getTextType().setText(control(metaFiles.get(index).getFileInfo().getType().toString()));
			
			folderUI.getFileData().getTextOwner().setText(control(metaFiles.get(index).getMetaData().getOwner()));
			folderUI.getFileData().getTextMdf().setText(dateControl(metaFiles.get(index).getMetaData().getModificationDate()));
			folderUI.getFileData().getTextCreation().setText(dateControl(metaFiles.get(index).getMetaData().getCreationDate()));
			folderUI.getFileData().getTextLoc().setText(control(metaFiles.get(index).getMetaData().getLocation().toString()));
			folderUI.getFileData().getTextPlatform().setText(control(metaFiles.get(index).getMetaData().getPlatform()));
			folderUI.getFileData().getTextApp().setText(control(metaFiles.get(index).getMetaData().getApplication()));
			
			for ( Entry<String, String> extraData :metaFiles.get(index).getMetaData().getExtraData().entrySet()){
				extra += extraData.getKey() + ":" + control(extraData.getValue()) + "\n";
			}
			folderUI.getFileData().getTextExtraData().setText(extra);
				
		}		
	}
	
	
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
	
	public void getErrorMsg(RETCODES retCode){
		String errorMSG = "Some thing was wrong.";
		switch (retCode) {
		case UNKNOWNERROR:
			errorMSG = "The Folder directory couldn't found.";
			break;
		default:
			break;
		}
		folderUI.setMsgBox(errorMSG);
	}

}
