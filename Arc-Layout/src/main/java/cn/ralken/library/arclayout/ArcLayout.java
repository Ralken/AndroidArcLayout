package cn.ralken.library.arclayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * A circle layout that contains at least 1 and 6 children maxniumn views,
 * layout them as cricle around.
 * 
 * @author liaoralken
 * @version V1.0
 * @date Oct 27, 2015
 */
public class ArcLayout extends ViewGroup {

	public ArcLayout(Context context) {
		super(context);
		init(context);
	}

	public ArcLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ArcLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int count = getChildCount();

		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() != GONE) {
				measureChild(child, widthMeasureSpec, heightMeasureSpec);
			}
		}
		int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
		setMeasuredDimension(width, width);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final int childCount = getChildCount();
		
		final int width = getWidth();
		
		/** Just assume that the padding offset should keep the same */
		final int childWidth = getChildAt(0).getMeasuredWidth();
		final int childdHeight = getChildAt(0).getMeasuredHeight();
		
		final int radius = (width - childWidth * 2) / 2 ;
		
		int xOffset = (int) (radius * Math.sin(Math.toRadians(30)));
		int[] xPositions = new int[]{0, xOffset, radius * 2 - xOffset, 
									radius * 2, radius * 2 - xOffset, xOffset};
		
		int yOffset = (int) (radius * Math.sin(Math.toRadians(60)));
		int[] yPostions = new int[] { radius, radius - yOffset, radius - yOffset, 
									radius, radius + yOffset, radius + yOffset };
		
		for (int i = 0; i < childCount; i++) {
			final int childIndex = i;
			final View child = getChildAt(childIndex);
			final int left = xPositions[i] + childWidth / 2;
			final int top = yPostions[i] + childdHeight / 2;
			child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
		}
	}

}
