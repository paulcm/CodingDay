package com.e0403.rtgame;

import java.util.TimerTask;

public class Task extends TimerTask {

	Linac lin;
	
	public Task(Linac lin) {
		// TODO Auto-generated constructor stub
		this.lin = lin;
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		lin.setBeamWidth(20);
				

	}

}
