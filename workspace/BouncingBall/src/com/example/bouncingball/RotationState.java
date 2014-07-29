package com.example.bouncingball;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class RotationState implements SensorEventListener {

	private final float[] mRotationMatrix = new float[16];
	private final float[] orientationVals =  new float[3];
	private SensorManager mSensorManager;
	private Sensor mRotationVectorSensor;
	
	public RotationState(Context context) {
		mSensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		mRotationVectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
		// initialize the rotation matrix to identity
		mRotationMatrix[0] = 1;
		mRotationMatrix[4] = 1;
		mRotationMatrix[8] = 1;
		mRotationMatrix[12] = 1;
		start();
	}

	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	public void start() {
		// enable our sensor when the activity is resumed, ask for
		// 10 ms updates.
		mSensorManager.registerListener(this, mRotationVectorSensor, 10000);
	}

	public void stop() {
		// make sure to turn our sensor off when the activity is paused
		mSensorManager.unregisterListener(this);
	}

	public void onSensorChanged(SensorEvent event) {
		// we received a sensor event. it is a good practice to check
		// that we received the proper event
		if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
			// convert the rotation-vector to a 4x4 matrix. the matrix
			// is interpreted by Open GL as the inverse of the
			// rotation-vector, which is what we want.
			SensorManager.getRotationMatrixFromVector(mRotationMatrix,
					event.values);
			
			   SensorManager.getOrientation(mRotationMatrix, orientationVals);

	            // Optionally convert the result from radians to degrees
	            orientationVals[0] = (float) Math.toDegrees(orientationVals[0]);
	            orientationVals[1] = (float) Math.toDegrees(orientationVals[1]);
	            orientationVals[2] = (float) Math.toDegrees(orientationVals[2]);
   
		}
	}
	
	public float getRotationX()
	{
		return this.orientationVals[0];
	}
	
	public float getRotationY()
	{
		return this.orientationVals[1];
	}
	
	public float getRotationZ()
	{
		return this.orientationVals[2];
	}

}
