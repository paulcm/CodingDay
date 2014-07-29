package com.example.bouncingball;
  
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
  
public class MainActivity extends Activity {

	private RotationState rotationState;
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
       rotationState = new RotationState(this);
      View bouncingBallView = new MainView(this, rotationState);

      requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                              WindowManager.LayoutParams.FLAG_FULLSCREEN);
      setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
      setContentView(bouncingBallView);
      bouncingBallView.setBackgroundColor(Color.BLACK);
   }
   
   
   /* @Override
   protected void onResume() {
	   if(rotationState != null)
	   this.rotationState.start();
   }
   
   @Override
   protected void onPause() {
	   if(rotationState != null)
		   this.rotationState.stop();
   }*/
   

   
  

}
