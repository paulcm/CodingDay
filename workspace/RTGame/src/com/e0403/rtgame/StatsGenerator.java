package com.e0403.rtgame;

import java.util.Vector;

public class StatsGenerator<T extends Number> {
	   private Vector<T> myStats;
	
	   public StatsGenerator()
	   {
		  myStats = new Vector<T>();
	   }
	
	public double generateStats()
	{
		double result = 0;
		 for(T value : myStats)
		  {
			  result += value.doubleValue();
		  }
		  return (result /= myStats.size());	  
	}
	
	public void addStatPoint(T value)
	{
		myStats.add(value);
	}
	
	public void clearStats()
	{
		myStats.clear();
	}
	

}
