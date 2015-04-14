package com.e0403.rtgame;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class OARManager {
private Date myStartTime;
 private List<Oar> myOar = new ArrayList<Oar>();
 private Scene myScene;
 
 public List<Oar> getOar()
 {
	return myOar; 
 }
 
 public void removeOar(Oar theOarToBeRemoved)
 {
	 this.myOar.remove(theOarToBeRemoved);
	 this.myScene.removeEntity(theOarToBeRemoved);
 }
 
 public OARManager(Scene scene)
 {
	 this.myScene = scene;
     myStartTime = new Date();
     spawnOar();
 }

 private void removeOar()
 {
   this.myOar.clear();
 }
 
 public void update()
 {
	 Date currentTime = new Date();
	 Iterator<Oar> it = this.myOar.iterator();
	 while(it.hasNext())
	 {
		 Oar o = it.next();
		 if(o.hasBeenHit())// && p.getHitTime().getTime() - currentTime.getTime() >= 500)
		 {
			this.myScene.removeEntity(o);
			it.remove();
		 }
	 }
		
	
 }
 
 private void spawnOar()
 {
	//10 OARS at the beginning
	 Random rand = new Random();
	//5x klein
	 for(int i = 0; i < 1; ++i)
	 {
		 float left, right, top, bottom;
		 left = rand.nextInt(1000);
		 right = left + 35;
		 bottom = rand.nextInt(1000);
		 top = bottom - 35;
		 Oar organ = new Oar(left, top, right, bottom);
		 this.myOar.add(organ);
		 this.myScene.addEntity(organ);	 
	 }
	 //5x groß
	 for(int i = 0; i < 1; ++i)
	 {
		 float left, right, top, bottom;
		 left = rand.nextInt(1000);
		 right = left + 35;
		 bottom = rand.nextInt(1000);
		 top = bottom - 60;
		 Oar organ = new Oar(left, top, right, bottom);
		 this.myOar.add(organ);
		 this.myScene.addEntity(organ);	 
	 }
 }

 

  
}

