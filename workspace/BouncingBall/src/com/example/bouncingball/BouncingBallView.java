package com.example.bouncingball;
  
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
   
public class BouncingBallView extends View {
   private int xMin = 0;          // This view's bounds
   private int xMax;
   private int yMin = 0;
   private int yMax;
   public List<Ball> ballList;
   private Paint paint;           // The paint (e.g. style, color) used for drawing
   private DrawableEntity leaf;
   
   // Constructor
   public BouncingBallView(Context context) {
      super(context);
      this.ballList = new ArrayList<Ball>();
     this.ballList.add(new Ball());
      paint = new Paint();
      this.leaf = new Leaf(20, 40, 180, 200);
    
    //  this.set
   }

   // Called back to draw the view. Also called by invalidate().
   @Override
   protected void onDraw(Canvas canvas) {
      // Draw the ball
	    
	  Ball currentBall = ballList.get(0);
	  if(currentBall != null)
		{
		  currentBall.setBounds();
	      paint.setColor(currentBall.color);
	      canvas.drawOval(currentBall.ballBounds, paint);
	      //canvas.drawRect(leaf.getBounds(), leaf.getPaint());
	      leaf.draw(canvas);
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
	         Thread.sleep(1);  
	      } catch (InterruptedException e) { }
	  
      invalidate();  // Force a re-draw
   }
   
   // Detect collision and update the position of the ball.
   private void update() {
	   Ball currentBall = ballList.get(0);
	   if(currentBall != null)
		   {
		   currentBall.collideAndCorrect(xMin, yMin, xMax, yMax);
	   }
   }
   
   // Called back when the view is first created or its size changes.
   @Override
   public void onSizeChanged(int w, int h, int oldW, int oldH) {
      // Set the movement bounds for the ball
      xMax = w-1;
      yMax = h-1;
   }
}