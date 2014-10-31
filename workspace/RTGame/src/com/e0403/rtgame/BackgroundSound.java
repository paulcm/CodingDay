package com.e0403.rtgame;

import com.example.bouncingball.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class BackgroundSound extends Service {

	private MediaPlayer mp;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		mp = MediaPlayer.create(this, R.raw.cairnomount);
		//mp.setVolume(1.0f, 1.0f);
		mp.setLooping(true);
	}
	@Override
	public void onDestroy() {
		mp.stop();
		mp.release();
	}
	@Override
	public void onStart(Intent intent, int startid) {
		mp.start();
	}

}
