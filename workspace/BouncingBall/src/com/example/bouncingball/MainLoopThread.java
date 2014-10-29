package com.example.bouncingball;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class MainLoopThread extends Thread {

	public MainView view;
	private StatsGenerator<Float> statsGen;
	private Date startTime;
	private DrawableEntity enemy;
	private MainView mainView;
	private DrawableEntity player;
	private Scene scene;
	private RotationState rotationState;

	
	private boolean running = true;
	 
	 
	public MainLoopThread(Context context) {
		// create the scene
		this.enemy = new Tumor(20);
		this.player = new Leaf();
		this.scene = new Scene();
		this.scene.addEntity(player);
		this.scene.addEntity(enemy);
		// game logic to gather stats
		this.statsGen = new StatsGenerator<Float>();
		// the view the scene is drawn onto
		this.mainView = new MainView(context, scene);
		// connect activity and view
		((Activity)context).setContentView(mainView);
		mainView.setBackgroundColor(Color.BLACK);
		
		// used as input to control the player entity
		this.rotationState = new RotationState(context);
		
		this.startTime = new Date();
		
	
	}

	@Override
	public void run() {
	
		
		while(running)
		{
		Date currentTime = new Date();
		if (currentTime.getTime() - startTime.getTime() >= 10000) {
			this.mainView.setResult(statsGen.generateStats());

			startTime = new Date();
			statsGen.clearStats();
		}
		statsGen.addStatPoint(AbstractDrawableEntity.coverage(player.getBounds(),
				enemy.getBounds()));
		// Update the position, including collision detection and reaction.
		update(this.mainView.getxMin(),
				this.mainView.getyMin(),
				this.mainView.getxMax(),
				this.mainView.getyMax());
		// Delay
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}
	}

	// Detect collision and update the position.
	private void update(float xMin, float yMin, float xMax, float yMax) {
		Float y = rotationState.getRotationY();
		Float z = rotationState.getRotationZ();
		//Log.i("Update: ", "xMin: " + xMin + "yMin: " + yMin + "xMax: " + xMax + "yMax: " + yMax);
		
		/*if(leaf != null) {
		this.leaf.collideAndCorrect(-y, -z, xMin, yMin, xMax, yMax);
		}
		if (tumor != null) {
			tumor.collideAndCorrect(y, z, xMin, yMin, xMax, yMax);
		}*/
		/*for(DrawableEntity e : this.scene.getEntities())
		{
			e.collideAndCorrect(-y, -z, xMin, yMin, xMax, yMax);
		}*/
		this.enemy.collideAndCorrect(y, z, xMin, yMin, xMax, yMax);
		this.player.collideAndCorrect(-y, -z, xMin, yMin, xMax, yMax);
	}

}
