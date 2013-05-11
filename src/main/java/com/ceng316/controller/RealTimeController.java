package com.ceng316.controller;

import java.nio.file.Path;

import com.ceng316.model.MetaReader.RETCODES;
import com.ceng316.model.metafile.FileType;
import com.ceng316.model.realtime.RealTimeReader;
import com.ceng316.model.util.google.GoogleFileSearch;
import com.ceng316.view.RealTimeUI;


public class RealTimeController extends MRController {
	private RealTimeUI realTimeUI;
	
	public RealTimeController(RealTimeUI realTimeUI){
		this.realTimeUI = realTimeUI;
	}
	
	public void analyseRealTimeFiles(Path tempDir,String search, String rank){
		try{
			if(addFileType() != null){
				RealTimeReader rtr = new RealTimeReader(tempDir, new RealTimePrint(this), new RealTimeFinish(this));	
				GoogleFileSearch gfs = new GoogleFileSearch();
				RETCODES retCode = rtr.process(gfs.search(search, addFileType(), new Integer(rank)));
				if(retCode == RETCODES.FILENOTFOUND) {
					realTimeUI.setMsgBox("Directory is not found");
					//finish code
				}
			}else{
				realTimeUI.setMsgBox("Check file type");
			}
		}catch(NumberFormatException nfe){
			realTimeUI.setMsgBox("Please, enter correct number for page number");
		}			
	}
	
	public RealTimeUI getRealTimeUI(){
		return realTimeUI;
	}
	
	public void addFileNames(String metaFile){
		realTimeUI.getFileComboBox().addItem(metaFile);
	}
	
	public FileType addFileType(){
		for( FileType t : FileType.values()){
			if (t.name().equals(realTimeUI.getFileType().getSelectedItem().toString())){
				return t;
			}
		}
		return null;

	}

}
