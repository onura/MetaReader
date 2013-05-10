package com.ceng316.controller;

import com.ceng316.model.realtime.IRealTimeFinishEvent;

public class RealTimeFinish implements IRealTimeFinishEvent{

	public void finish() {
		System.out.println("Finished. ");		
	}

}
