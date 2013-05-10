package com.ceng316.controller;

import com.ceng316.model.realtime.IRealTimeFinishEvent;

public class RealTimeFinish implements IRealTimeFinishEvent{
	private RealTimeController real;
	
	public RealTimeFinish(){
		
	}
	
	public RealTimeFinish(RealTimeController real){
		this.real = real;
	}

	public void finish() {
		if (real != null)
			real.getRealTimeUI().getLblLoading().setVisible(false);
		else
			System.out.println("Finished");
	}

}
