package com.example.bouncingball;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private MainLoopThread mainThread;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
     	this.mainThread = new MainLoopThread(this);
		this.mainThread.start();
		
		Intent objIntent = new Intent(this, BackgroundSound.class);
		startService(objIntent);
	}

	/*
	 * @Override protected void onResume() { if(rotationState != null)
	 * this.rotationState.start(); }
	 * 
	 * @Override protected void onPause() { if(rotationState != null)
	 * this.rotationState.stop(); }
	 */
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Intent objIntent = new Intent(this, BackgroundSound.class);
		stopService(objIntent);
	}


}
