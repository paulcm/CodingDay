package com.example.bouncingball;

import android.graphics.Color;
import android.graphics.RectF;

public class Ball {
	public float ballRadius = 80; // Ball's radius
	public float ballX = ballRadius + 20;  // Ball's center (x,y)
	public float ballY = ballRadius + 40;
	public float ballSpeedX = 5;  // Ball's speed (x,y)
	public float ballSpeedY = 3;
	public RectF ballBounds;      // Needed for Canvas.drawOval
	public int color = Color.GREEN;
	   
	   public Ball()
	   {
		      ballBounds = new RectF();
	   }
	   
	   public void setBounds()
	   {
		   ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
	   }
	   
	   public boolean isNear(float x0, float y0, float nearCriteria)
	   {
		   float distX = Math.abs(x0 - ballX);
		   float distY = Math.abs(y0 - ballY);
		   if(distX <= nearCriteria && distY <= nearCriteria)
		   {
			   return true;
		   }
		   else
		   {
			   return false;
		   }
	   }
	   
	   public void collideAndCorrect(float xMin, float yMin, float xMax, float yMax)
	   {
		   {
			      // Get new (x,y) position
				   ballX += ballSpeedX;
				   ballY += ballSpeedY;
			      // Detect collision and react
			      if (ballX + ballRadius > xMax) {
			    	  ballSpeedX = -ballSpeedX;
			    	  ballX = xMax-ballRadius;
			      } else if (ballX - ballRadius < xMin) {
			    	  ballSpeedX = -ballSpeedX;
			    	  ballX = xMin+ballRadius;
			      }
			      if (ballY + ballRadius > yMax) {
			    	  ballSpeedY = -ballSpeedY;
			    	  ballY = yMax - ballRadius;
			      } else if (ballY - ballRadius < yMin) {
			    	  ballSpeedY = -ballSpeedY;
			    	  ballY = yMin + ballRadius;
			      }
	   }
	   }
}
