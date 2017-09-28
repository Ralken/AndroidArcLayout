package cn.ralken.library.arclayout;

import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ArcImageView extends ImageView {
	private int rotation_angle = 0;
	// coordinates when motion down event
	private float onMotionDown_X, onMotionDown_Y;
	private boolean isPressed = false;
	private int index;
	private CircleLayout parent;

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	// set the rotation angle of the object
	public void setRotationParameters(int angle) {
		rotation_angle = angle;
		// this.invalidate();

		rotateByAnimation(rotation_angle);
	}

	public ArcImageView(Context context, CircleLayout cl) {
		super(context);
		parent = cl;
		init();
	}

	public ArcImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ArcImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	// to pass the touch event to the parent
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			onMotionDown_X = ev.getRawX();
			onMotionDown_Y = ev.getRawY();
		}
		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			// if move event occurs
			long downTime = SystemClock.uptimeMillis();
			long eventTime = SystemClock.uptimeMillis() + 100;
			float x = ev.getRawX();
			float y = ev.getRawY();
			if ((Math.abs(onMotionDown_X - x) > 10) || (Math.abs(onMotionDown_Y - y) > 10)) {
				int metaState = 0;
				// create new motion event
				MotionEvent motionEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, x, y, metaState);
				// send event to listview
				((CircleLayout) this.getParent()).dispatchTouchEvent(motionEvent);
				return true;
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	// the function get activated whenever click event occurs
	public void onClick() {
		CircleLayout cl = (CircleLayout) this.getParent();
		CircleLayout.OnChildClickListener onChildClickListener = cl.getOnChildClickListener();
		if(null != onChildClickListener){
			onChildClickListener.onChildClicked(this, index);
			//Toast.makeText(getContext(), index + "", Toast.LENGTH_SHORT).show();
		}
	}

	// Initialize the object
	public void init() {
		setLayerType(View.LAYER_TYPE_HARDWARE, null);
		// setAdjustViewBounds(true);
		setScaleType(ScaleType.CENTER_INSIDE);
		this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
				if (action == MotionEvent.ACTION_DOWN)
					isPressed = true;
				else if (action == MotionEvent.ACTION_UP) {
					onClick();
					if (isPressed) {
						isPressed = false;
						return false;
					}
				}
				return true;
			}
		});
	}

	@Override
	public void draw(Canvas canvas) {
	   /*canvas.save();
       CircleLayout cl=(CircleLayout)this.getParent();
       if(!parent.get_is_pinned_childs())
	        canvas.rotate(rotation_angle%360,this.getHeight()/2,this.getWidth()/2);
       else
            canvas.rotate(parent.get_pinnded_childs_rotation_angle(), this.getHeight() / 2, this.getWidth() / 2);

	    super.draw(canvas);
	    canvas.restore();*/
		
		super.draw(canvas);
	}
	
	private void rotateByAnimation(float rotation){
		setRotation(rotation_angle%360);
	}
	
}
