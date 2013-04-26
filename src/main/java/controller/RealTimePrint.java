package controller;

import model.metafile.MetaFile;
import model.realtime.IRealTimeResult;

public class RealTimePrint implements IRealTimeResult{

	public void use(MetaFile metafile) {
		System.out.println(metafile);		
	}

}
