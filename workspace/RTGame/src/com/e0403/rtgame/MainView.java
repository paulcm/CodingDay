package com.e0403.rtgame;

import java.util.Date;
import java.util.Iterator;
import java.util.Timer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class MainView extends View {
	private int xMin = 0; // This view's bounds
	private int xMax;
	private int yMin = 0;
	private int yMax;
	// private RotationState rotationState;
	private Paint textPaint;
	private double result;
	private InputController myInputController;
	
	private Date startTime;
	private Tumor enemy;
	//private MainView mainView;
	private DrawableEntity linac;
	private Scene scene;
	private RotationState rotationState;
	private PowerUpManager myPowerUpManager;
	private OARManager myOARManager;
	public static final int INTERVAL = 20;
	public int hitcounter = 0;
	
	public Timer timer = new Timer();
	// Constructor
	public MainView(Context context) {
		super(context);
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
		//NEW
		myOARManager = new OARManager(scene);
		// connect activity and view
		this.setBackgroundColor(Color.BLACK);
		
		// used as input to control the player entity
		this.rotationState = new RotationState(context);
		// this.rotationState = rotationState;
		textPaint = new Paint();
		this.myInputController = inputController;
		textPaint.setTextSize(50);
		textPaint.setColor(Color.GREEN);
	}

	public void setResult(double result) {
		this.result = result;
	}

	// Called back to draw the view. Also called by invalidate().
	@Override
	protected void onDraw(Canvas canvas) {
		
		canvas.drawText("Average Coverage: " + result + "%", 100, 100,
				textPaint);
		Iterator<DrawableEntity> it = this.scene.getEntities().iterator();
		while(it.hasNext())
		{
			it.next().draw(canvas);
			//Log.i("REndering", entity.toString() + " " + entity.getClass());
		}
		invalidate();
		
		this.startTime = new Date();	
		
		Date currentTime = new Date();
		if (currentTime.getTime() - startTime.getTime() >= 10000) {
			//this.mainView.setResult(statsGen.generateStats());
			this.setResult(Double.MIN_VALUE);
			startTime = new Date();
			//statsGen.clearStats();
		}
		//statsGen.addStatPoint(AbstractDrawableEntity.coverage(player.getBounds(),
		//		enemy.getBounds()));
		// Update the position, including collision detection and reaction.
		myPowerUpManager.update();
		myOARManager.update();
		this.scene.updateScene();
		update(this.getxMin(),
				this.getyMin(),
				this.getxMax(),
				this.getyMax());
		// Delay
			try {
				Thread.sleep(INTERVAL);
			} catch (InterruptedException e) {
			}
		
		
		
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		switch(event.getActionMasked())
		{
		case MotionEvent.ACTION_DOWN:
		myInputController.notifyEvent(event.getX(), event.getY());
		break;
		default:
	    return false;
		}
		return true;
	}

	// Called back when the view is first created or its size changes.
	@Override
	public void onSizeChanged(int w, int h, int oldW, int oldH) {
		// Set the movement bounds for the ball
		xMax = w - 1;
		yMax = h - 1;
	}

	public int getxMin() {
		return xMin;
	}

	public int getxMax() {
		return xMax;
	}

	public int getyMin() {
		return yMin;
	}

	public int getyMax() {
		return yMax;
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
					timer.cancel();
					Intent objIntent = new Intent(this.getContext(), BeamPowerUpSound.class);
					this.getContext().startService(objIntent);
					p.markHit();
					Linac lin = (Linac) linac;
					lin.setBeamWidth(40);
					
					// start in 5 sec
					Timer timer = new Timer();
					timer.schedule(new Task(lin), 5000);
				}
			}
			
			for(Oar o : this.myOARManager.getOar())
			{
				float hit2 = AbstractDrawableEntity.coverage(linac.getBounds(), o.getBounds());
				if(hit2 > 0.0f)		
				{
					o.markHit();	
					
					hitcounter = hitcounter+1;
					if (hitcounter == 5)
					{
						// ende aufrufen
						
					}
					
					
				}
			}
			
			float hit1 = AbstractDrawableEntity.coverage(linac.getBounds(), this.enemy.getBounds());
			if(hit1 > .0f)
			{
				System.out.println("TREFFER");
				this.enemy.irradiate(linac.getBoundsPath());
			}

			//this.player.collideAndCorrect(-y, -z, xMin, yMin, xMax, yMax);
			if(gameOver())
			{
				Intent intent = new Intent(getContext(), EndActivity.class);
				getContext().startActivity(intent);
			}
		}
		
		boolean gameOver()
		{
			return this.enemy.isDead();
		}

}