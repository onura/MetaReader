package controller;

import model.metafile.MetaFile;
import model.realtime.IRealTimeResult;

public class RealTimePrint implements IRealTimeResult{
	private RealTimeController realTime;
	
	public RealTimePrint(){
		
	}
	
	public RealTimePrint(RealTimeController realTime){
		this.realTime = realTime;
	}

	public void use(MetaFile metafile) {
		realTime.getRealMeta().add(metafile);
		realTime.addFileNames();
	}

}
