package com.girlesc.enguard.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.icu.util.Measure;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.girlesc.enguard.R;

public class CustomSeekBar extends View {

    private Context mContext;

    public CustomSeekBar(Context context) {
        super(context);
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public interface OnValueChanged {
        void ValueChanged(CustomSeekBar customSeekBar, Integer new_value);
    }

    public OnValueChanged onValueChanged = new OnValueChanged() {
        @Override
        public void ValueChanged(CustomSeekBar customSeekBar, Integer new_value) {

        }
    };

    boolean isDragging, isAnimating = false;
    int y = 0, curY = 0, newY = 0;
    int biggestHeight = 0;
    int currentValue = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    public void handleMove(MotionEvent e) {
        if (!isDragging) {
            return;
        }
        isAnimating = false;
        curY = Math.round(e.getY());
        invalidate();
    }

    public class BackgroundAnimator extends AsyncTask<Object, Object, Object> {

        @Override
        protected Object doInBackground(Object... objects) {
            while (curY != newY) {
                if (isAnimating) {
                    if (curY > newY)
                        curY--;
                    else
                        curY++;
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    return null;
                }
            }
            isAnimating = false;
            return null;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (y < 0 || (event.getY() > y && event.getY() < y + biggestHeight)) {
                    isAnimating = false;
                    getParent().requestDisallowInterceptTouchEvent(true);
                    isDragging = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                handleMove(event);
                break;
            case MotionEvent.ACTION_UP:
                handleMove(event);
            case MotionEvent.ACTION_CANCEL:
                if (isDragging == true) {
                    isAnimating = true;
                    if (curY == 0)
                        curY = valBasedY();
                    int bh = height() / 3;
                    for (int i = 0; i != 3; i++) {
                        if (bh * i < event.getY() && event.getY() < bh * (i + 1)) {
                            currentValue = i;
                        }
                    }
                    onValueChanged.ValueChanged(this, currentValue);
                    newY = valBasedY();
                    new BackgroundAnimator().execute();

                    getParent().requestDisallowInterceptTouchEvent(false);
                    isDragging = false;
                }
                break;
        }
        return true;
    }

    Rect size;

    int width() {
        return size.width();
    }

    int height() {
        return size.height();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        size = canvas.getClipBounds();

        if (isDragging || isAnimating) {
            y = curY;
        } else {
            y = valBasedY();
        }
        Paint box = new Paint();
        box.setColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawRoundRect(new RectF(0, y, width(), y + biggestHeight), 5, 5, box);
    }

    private int valBasedY() {
        int bh = (width() / 3);
        return bh * currentValue + bh / 2 - biggestHeight / 2;
    }
}
