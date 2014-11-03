package com.e0403.rtgame;

import com.e0403.rtgame.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class BeamPowerUpSound extends Service {

	private MediaPlayer mp;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		//replace power up sound later
		mp = MediaPlayer.create(this, R.raw.rtgametumorhit);
		mp.setVolume(1.0f, 1.0f);
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