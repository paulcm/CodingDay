package com.e0403.rtgame;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.DisplayMetrics;

public class MainLoopThread extends Thread {

	public MainView view;
	//private StatsGenerator<Float> statsGen;
	private Date startTime;
	private Tumor enemy;
	private MainView mainView;
	private DrawableEntity linac;
	private Scene scene;
	private RotationState rotationState;
	private PowerUpManager myPowerUpManager;

	
	private boolean running = true;
	
	public static final int INTERVAL = 20;
	 
	 
	public MainLoopThread(Context context) {
		InputController inputController = new InputController();
		// create the scene
		this.enemy = new Tumor(20,4);
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int height = metrics.heightPixels;
		float yCenterPos = height / 2.0f;
		this.linac = new Linac(.0f, yCenterPos);
	    this.scene = new Scene();
			this.scene.addEntity(linac);
		this.scene.addEntity(enemy);
		inputController.registerLinac((Linac) linac);
		// game logic to gather stats
		//this.statsGen = new StatsGenerator<Float>();
		// the view the scene is drawn onto
		myPowerUpManager = new PowerUpManager(scene);
		this.mainView = new MainView(context, scene, inputController);
		// connect activity and view
		((Activity)context).setContentView(mainView);
		mainView.setBackgroundColor(Color.BLACK);
		
		// used as input to control the player entity
		this.rotationState = new RotationState(context);
		
	}


	@Override
	public void run() {
		this.startTime = new Date();	
	
		while(running)
		{
		Date currentTime = new Date();
		if (currentTime.getTime() - startTime.getTime() >= 10000) {
			//this.mainView.setResult(statsGen.generateStats());
			this.mainView.setResult(Double.MIN_VALUE);
			startTime = new Date();
			//statsGen.clearStats();
		}
		//statsGen.addStatPoint(AbstractDrawableEntity.coverage(player.getBounds(),
		//		enemy.getBounds()));
		// Update the position, including collision detection and reaction.
		myPowerUpManager.update();
		update(this.mainView.getxMin(),
				this.mainView.getyMin(),
				this.mainView.getxMax(),
				this.mainView.getyMax());
		// Delay
			try {
				Thread.sleep(INTERVAL);
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
		
		this.enemy.moveTumor(xMin, yMin, xMax, yMax);
		
		for(PowerUp p : this.myPowerUpManager.getPowerUps())
		{
			float hit = AbstractDrawableEntity.coverage(linac.getBounds(), p.getBounds());
			if(hit > 0.0f)		
			{
				p.markHit();
			}
		}
		float hit1 = AbstractDrawableEntity.coverage(linac.getBounds(), this.enemy.getBounds());
		if(hit1 > .0f)
		{
			System.out.println("TREFFER");
		}

		//this.player.collideAndCorrect(-y, -z, xMin, yMin, xMax, yMax);
	}

}
