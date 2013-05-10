package com.ceng316.controller;

import com.ceng316.model.metafile.MetaFile;
import com.ceng316.model.realtime.IRealTimeResult;

public class RealTimePrint implements IRealTimeResult{
	private RealTimeController realTime;
	
	public RealTimePrint(){
		
	}
	
	public RealTimePrint(RealTimeController realTime){
		this.realTime = realTime;
	}

	public void use(MetaFile metafile) {
		if (realTime != null){
			realTime.getMetaFiles().add(metafile);
			realTime.addFileNames(metafile.getFileInfo().getFileName());
			realTime.getRealTimeUI().getLblFileInfo().setText("Total number of files is " + 
									realTime.getRealTimeUI().getFileComboBox().getItemCount());
		}else
			System.out.println(metafile);		
	}

}
