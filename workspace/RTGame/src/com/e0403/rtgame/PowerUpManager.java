package com.e0403.rtgame;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PowerUpManager {
private Date myStartTime;
 private List<PowerUp> myPowerUps = new ArrayList<PowerUp>();
 private Scene myScene;
 
 public List<PowerUp> getPowerUps()
 {
	return myPowerUps; 
 }
 
 public void removePowerUp(PowerUp thePowerUpToBeRemoved)
 {
	 this.myPowerUps.remove(thePowerUpToBeRemoved);
	 this.myScene.removeEntity(thePowerUpToBeRemoved);
 }
 
 public PowerUpManager(Scene scene)
 {
	 this.myScene = scene;
     myStartTime = new Date();
     spawnPowerUp();
 }

 private void removePowerUps()
 {
   this.myPowerUps.clear();
 }
 
 public void update()
 {
	 Date currentTime = new Date();
	 Iterator<PowerUp> it = this.myPowerUps.iterator();
	 while(it.hasNext())
	 {
		 PowerUp p = it.next();
		 if(p.hasBeenHit())
		 {
			//this.myScene.removeEntity(p);
			it.remove();
		 }
	 }
		if (currentTime.getTime() - myStartTime.getTime() >= 10000)
		{
		removePowerUps();
		spawnPowerUp();
	  	myStartTime = new Date();
		}
	 // nach 5 sekunden muss das power up verschwinden
 }

 
 private void spawnPowerUp()
 {
	 Random rand = new Random();
	 int powerUpCount = rand.nextInt(5) + 1;
	 for(int i = 0; i < powerUpCount; ++i)
	 {
		 float left, right, top, bottom;
		 left = rand.nextInt(1000);
		 right = left + 35;
		 bottom = rand.nextInt(1000);
		 top = bottom - 35;
		 PowerUp pwrUp = new PowerUp(left, top, right, bottom);
		 this.myPowerUps.add(pwrUp);
		 this.myScene.addEntity(pwrUp);	 
	 }
 }
  
}

