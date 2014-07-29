package com.example.bouncingball;
  
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
  
public class MainActivity extends Activity implements OnTouchListener {

   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      View bouncingBallView = new BouncingBallView(this);
      setContentView(bouncingBallView);
      bouncingBallView.setBackgroundColor(Color.BLACK);
      bouncingBallView.setOnTouchListener(this);
   }
   
   @Override
   public boolean onTouch(View v, MotionEvent event)
   {
	   if(v instanceof BouncingBallView)
	   {
		BouncingBallView bv = (BouncingBallView) v;
		Random rand = new Random();
		int r, g, b;
		r = rand.nextInt(255);
		g = rand.nextInt(255);
		b = rand.nextInt(255);
		float ex =	event.getX();
		float ey = 	event.getY();
		Ball ball = bv.ballList.get(0);
		if(ball != null)
		{
			if(ball.isNear(ex, ey, 50.0f))
			{
			ball.color = Color.rgb(r, g,b );
			//bv.ballList.clear();
			}
		}
	   }
	   return true;
   }

   
  

}
