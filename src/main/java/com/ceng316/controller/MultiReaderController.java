package com.ceng316.controller;

import java.io.IOException;
import java.nio.file.Path;

import com.ceng316.model.MetaReader.RETCODES;
import com.ceng316.model.multi.MultiReader;
import com.ceng316.view.FolderUI;



public class MultiReaderController extends MRController{
	
	private FolderUI folderUI;
	private RETCODES retCode;
	private MultiReader multiReader;
	
	public MultiReaderController(FolderUI folderUI){
		this.folderUI = folderUI;
	}
	
	public void analyseMultiFiles(Path folderPath){
	
		multiReader = new MultiReader(folderPath);			
		retCode = multiReader.analyseFolder();
		
		if (retCode == RETCODES.SUCCESS){
			try {
				multiReader.getFolder();
				multiReader.getFolder().getFiles();
				multiReader.analyseFolder();
				
				for (int i = 0; i < multiReader.getMetaFiles().size(); i++){
					folderUI.addFileNames(multiReader.getMetaFiles().get(i).getFileInfo().getFileName(),i);
					getMetaFiles().add(multiReader.getMetaFiles().get(i));
				}	
				
			} catch (IOException e) {
				folderUI.setMsgBox("There is an error.");
			}	
			
		}else{
			folderUI.setMsgBox(getErrorMsg(retCode));
		}	
		
	}
	
	public RETCODES getRetCode(){
		return retCode;
	}

	
	@Override
	public String getErrorMsg(RETCODES retCode){
		String errorMSG = "Some thing was wrong.";
		switch (retCode) {
			case UNKNOWNERROR:
				errorMSG = "The Folder directory couldn't found.";
				break;
			default:
				break;
		}
		return errorMSG;
	}

}
