package controller;

import model.realtime.IRealTimeFinishEvent;

public class RealTimeFinish implements IRealTimeFinishEvent{

	public void finish() {
		System.out.println("Finished. ");		
	}

}
