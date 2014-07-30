package com.example.bouncingball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class MainView extends View {
	private int xMin = 0; // This view's bounds
	private int xMax;
	private int yMin = 0;
	private int yMax;
	// private RotationState rotationState;
	private Paint textPaint;
	private Scene scene;
	private double result;

	// Constructor
	public MainView(Context context, Scene scene) {
		super(context);
		// this.rotationState = rotationState;
		this.scene = scene;
		textPaint = new Paint();
		textPaint.setTextSize(50);
		textPaint.setColor(Color.GREEN);
	}

	public void setResult(double result) {
		this.result = result;
	}

	// Called back to draw the view. Also called by invalidate().
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawText("Average Coverage: " + result + "%", 100, 100,
				textPaint);
		for (DrawableEntity entity : this.scene.getEntities()) {
			entity.draw(canvas);
			Log.i("REndering", entity.toString() + " " + entity.getClass());
		}
		invalidate();
	}

	// Called back when the view is first created or its size changes.
	@Override
	public void onSizeChanged(int w, int h, int oldW, int oldH) {
		// Set the movement bounds for the ball
		xMax = w - 1;
		yMax = h - 1;
	}

	public int getxMin() {
		return xMin;
	}

	public int getxMax() {
		return xMax;
	}

	public int getyMin() {
		return yMin;
	}

	public int getyMax() {
		return yMax;
	}

}