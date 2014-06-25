package org.libsdl.app;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

/**
 * 手势控件
 * @author android
 *
 */
public class GestureView extends View implements OnGestureListener{
	private static final String TAG = GestureView.class.getSimpleName();
	private static final int DISTANCE = 10;
	public static final int DIRECTION_LEFT = 0;
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_UP = 2;
	public static final int DIRECTION_DOWN = 3;
	
	private GestureDetector mGDetector;
	private FloatPoint mDownPoint;
	private onDirectionChangedListener mListener;
	private boolean mIsInit;
	
	public GestureView(Context context) {
		super(context);
	}

	public GestureView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public GestureView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	private void init(){
		mGDetector = new GestureDetector(getContext(), this);
		mDownPoint = new FloatPoint(0, 0);
	}
	
	public void setOnDirectionChangedListener(onDirectionChangedListener listener){
		mListener = listener;
		if(!mIsInit){
			mIsInit = true;
			init();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_UP){
			mDownPoint.setValue(0, 0);
			return true;
		}
		if(mGDetector.onTouchEvent(event)){
			return true;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		Log.d(TAG, "onDown ...");
		mDownPoint.setValue(arg0.getX(), arg0.getY());
		return true;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		Log.d(TAG, "onFling ..."+arg2+","+arg3);
		return true;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		Log.d(TAG, "onLongPress ...");
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		Log.d(TAG, "onScroll ..." + arg2 + "," + arg3);
		float ax = Math.abs(arg2);
		float ay = Math.abs(arg3);
		if (ax >= DISTANCE && ay >= DISTANCE) {
			if (ax > ay) {
				if (arg2 > 0) {
					onDirectionChanged(DIRECTION_LEFT);
				} else {
					onDirectionChanged(DIRECTION_RIGHT);
				}
			} else {
				if (arg3 > 0) {
					onDirectionChanged(DIRECTION_UP);
				} else {
					onDirectionChanged(DIRECTION_DOWN);
				}
			}
		} else if (ax >= DISTANCE) {
			if (arg2 > 0) {
				onDirectionChanged(DIRECTION_LEFT);
			} else {
				onDirectionChanged(DIRECTION_RIGHT);
			}
		} else if (ay >= DISTANCE) {
			if (arg3 > 0) {
				onDirectionChanged(DIRECTION_UP);
			} else {
				onDirectionChanged(DIRECTION_DOWN);
			}
		} else {
			mDownPoint.setValue(arg1.getX(), arg1.getY());
		}
		return true;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		Log.d(TAG, "onShowPress ...");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		Log.d(TAG, "onSingleTapUp ...");
		return true;
	}
	
	private void onDirectionChanged(int direction){
		if(mListener != null){
			mListener.onDirectionChanged(direction);
		}
	}
	
	private class FloatPoint{
		private float x;
		private float y;
		
		public FloatPoint(float _x, float _y){
			x = _x;
			y = _y;
		}
		
		public void setValue(float _x, float _y){
			x = _x;
			y = _y;
		}
	}

	public interface onDirectionChangedListener {
		/**
		 * 方向改变
		 * @param direction
		 */
		public void onDirectionChanged(int direction);
	}
}
