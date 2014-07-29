package com.example.bouncingball;
  
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
   
public class MainView extends View  {
   private int xMin = 0;          // This view's bounds
   private int xMax;
   private int yMin = 0;
   private int yMax;         
   private DrawableEntity leaf;
   private RotationState rotationState;
   
   private Tumor tumor;
   
   // Constructor
   public MainView(Context context, RotationState rotationState) {
      super(context);
      this.rotationState = rotationState;
      this.leaf = new Leaf(20, 40, 180, 200);
      this.tumor = new Tumor(context);
   }

   // Called back to draw the view. Also called by invalidate().
   @Override
   protected void onDraw(Canvas canvas) {
      // Draw the ball
	   //virtuos coding mode enabled!
	  
	  if(tumor != null && leaf != null)
		{
	      //canvas.drawRect(leaf.getBounds(), leaf.getPaint());
	      leaf.draw(canvas);
	      tumor.draw(canvas);
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
	   Float x = rotationState.getRotationX();
	   Float y = rotationState.getRotationY();
	   Float z = rotationState.getRotationZ();
	   Log.i("RotationState X", x.toString());
	   Log.i("RotationState Y", y.toString());
	   Log.i("RotationState Z", z.toString());
	   this.leaf.collideAndCorrect(-y, -z, xMin, yMin, xMax, yMax);
	   if(tumor != null)
		   {
		   //TODO: tumor.collideAndCorrect(xMin, yMin, xMax, yMax);
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