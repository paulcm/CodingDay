package com.example.bouncingball;

import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public abstract class AbstractDrawableEntity implements DrawableEntity{
	protected RectF bounds;
	protected Paint paint;
	
	@Override
	public void move(float dx, float dy) {
		this.bounds.set(this.bounds.left + dx, this.bounds.top + dy, this.bounds.right + dx, this.bounds.bottom + dy);
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
}
