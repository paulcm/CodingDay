package com.e0403.rtgame;

import java.util.ArrayList;
import java.util.List;

public class InputController {

	private List<Linac> myLinacList = new ArrayList<Linac>();
	
	void notifyEvent(float xPos, float yPos)
	{
		for(Linac l : myLinacList)
		{
			l.receiveTouchNotification(xPos, yPos);
		}
	}
	
	void registerLinac(Linac theLinac)
	{
	 myLinacList.add(theLinac);
	}
	
	void dispatchLinac(Linac theLinac)
	{
	 myLinacList.remove(theLinac);
	}
}
