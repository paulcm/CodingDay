package com.example.bouncingball;
  

import java.util.Date;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
   
public class MainView extends View  {
   private int xMin = 0;          // This view's bounds
   private int xMax;
   private int yMin = 0;
   private int yMax;         
   private DrawableEntity leaf;
   private RotationState rotationState;

   
   private Tumor tumor;

   private Date startTime;
   private Vector<Float> myStats;

   // Constructor
   public MainView(Context context, RotationState rotationState) {
      super(context);
      myStats = new Vector<Float>();
      this.rotationState = rotationState;
      this.startTime = new Date();
      this.leaf = new Leaf(20, 40, 180, 200);
      this.tumor = new Tumor(context, 20);
   }

   // Called back to draw the view. Also called by invalidate().
   @Override
   protected void onDraw(Canvas canvas) {
      // Draw the ball
	   //virtuos coding mode enabled!
	  Date currentTime = new Date();
	  
	  if(currentTime.getTime() - startTime.getTime() >= 3000)
	  {
		  Paint textPaint = new Paint();
		  textPaint.setTextSize(50);
		  textPaint.setColor(Color.GREEN);
		  float result = .0f;
		  for(Float f : myStats)
		  {
			  result += f;
		  }
		  result /= myStats.size();
		  canvas.drawText("Average Coverage: " + result + "%", 100, 100, textPaint);
	  }
	  if(tumor != null && leaf != null)
		{
	      //canvas.drawRect(leaf.getBounds(), leaf.getPaint());
	      leaf.draw(canvas);
	      tumor.draw(canvas);
	      myStats.add(AbstractDrawableEntity.coverage(leaf.getBounds(), tumor.getBounds()));
		}
	  else
	  {
		 /* paint.setColor(Color.RED);
	
			
			
		  RectF ballBounds = new RectF();
		  ballBounds.set(100-80, 120-80, 100+80, 120+80);
		  canvas.drawOval(currentBall.ballBounds, paint);*/
	  }
	      // Update the position of the ball, including collision detection and reaction.
	      update();
	  
	      // Delay
	      try {  
	         Thread.sleep(20);  
	      } catch (InterruptedException e) { }
	  
      invalidate();  // Force a re-draw
   }
   
   // Detect collision and update the position of the ball.
   private void update() {
	   Float y = rotationState.getRotationY();
	   Float z = rotationState.getRotationZ();
	   this.leaf.collideAndCorrect(-y, -z, xMin, yMin, xMax, yMax);
	   if(tumor != null)
	   {
		   tumor.collideAndCorrect(y,z,xMin, yMin, xMax, yMax);
	   }
	   //mSensor.
	   
   }
   
   // Called back when the view is first created or its size changes.
   @Override
   public void onSizeChanged(int w, int h, int oldW, int oldH) {
      // Set the movement bounds for the ball
      xMax = w-1;
      yMax = h-1;
   }
}