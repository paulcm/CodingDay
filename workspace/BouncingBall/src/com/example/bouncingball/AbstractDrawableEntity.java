package com.example.bouncingball;

import android.graphics.Paint;
import android.graphics.RectF;

public abstract class AbstractDrawableEntity implements DrawableEntity{
	protected RectF bounds;
	protected Paint paint;
	

	public AbstractDrawableEntity(){
		this.bounds = new RectF();
	}
	
	public static float coverage(RectF beam, RectF target)
	{
		RectF result = new RectF();
		if(result.setIntersect(beam, target))
			{
			 return computeCoverage(result, target);
			}
		else
		{
			return .0f;
		}
	}

	private static float computeCoverage(RectF result, RectF target)
	{
		return (result.width() * result.height()) / ((target.width() * target.height())) * 100.0f;

	}
	
	@Override
	public void move(float dx, float dy) {
		this.bounds.set(this.bounds.left + dx, this.bounds.top + dy, this.bounds.right + dx, this.bounds.bottom + dy);
	}

	public RectF getBounds() {
		return this.bounds;
	}
	
	
	@Override
	 public void collideAndCorrect(float dx, float dy, float xMin, float yMin, float xMax, float yMax)
	   {
		   {
			      // Detect collision and react
			      if (dx + this.bounds.right > xMax) {
			    	  dx = xMax - this.bounds.right;
			      } else if (this.bounds.left + dx < xMin) {
			    	  dx = xMin + this.bounds.left;
			      }
			    if (dy + this.bounds.top < yMin) {
			    	  dy = yMin + this.bounds.top;
			      } else if (dy + this.bounds.bottom > yMax) {
			    	  dy = yMax - this.bounds.bottom;
			      }
			    
			    this.move(dx, dy);
	   }		   
		  
}
	
	   /*@Override
	   public boolean isNear(float x0, float y0, float nearCriteria)
	   {
		  // this.bounds.setIntersect(a, b)
		   float x = this.bounds.centerX();
		   float y = this.bounds.centerY();
		   float distX = Math.abs(x0 - x);
		   float distY = Math.abs(y0 - y);
		   if(distX <= nearCriteria && distY <= nearCriteria)
		   {
			   return true;
		   }
		   else
		   {
			   return false;
		   }
	   }*/
}
