package com.example.hh.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hh on 16/12/1.
 */

public class MyView extends View {

    private String mText;
    private Paint mTextPaint;


    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
        super(context, attrs);
        init();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);

        String string = typedArray.getString(R.styleable.MyView_text);
        if (null != string) {
            setText(string);
        }

        setTextColor(typedArray.getColor(R.styleable.MyView_textColor, Color.RED));

        int textSize = typedArray.getDimensionPixelOffset(R.styleable.MyView_textSize, 0);
//        typedArray.getDimension()
//        typedArray.getDimensionPixelSize()
        if (textSize > 0) {
//            Log.i("MyView", "textSize:" + textSize);
            setTextSize(textSize);
        }

        typedArray.recycle();


    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("MyView", "3===");
    }


    private void setText(String string) {
        mText = string;
        requestLayout();
        invalidate();

    }

    private void setTextSize(int textSize) {
        mTextPaint.setTextSize(textSize);
        requestLayout();
        invalidate();
    }

    private void setTextColor(int textColor) {
        mTextPaint.setColor(textColor);
        invalidate();
    }

    private void init() {

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);

//        mTextPaint.setTextSize(16 * getResources().getDisplayMetrics().density);
//        mTextPaint.setColor(Color.BLUE);
//        setPadding(5, 5, 5, 5);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("MyView", "onMeasure");
        setMeasuredDimension(setMeaseureWidth(widthMeasureSpec), setMeasureHeight(heightMeasureSpec));
    }

    private int setMeaseureWidth(int widthMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        if (MeasureSpec.EXACTLY == mode) {
//            Log.i("MyView", "---" + getPaddingLeft() + "---" + getPaddingRight() + "---" + mTextPaint.measureText(mText) + "---" + size);
            result = size;
        } else {
            result = getPaddingLeft() + getPaddingRight() + (int) mTextPaint.measureText(mText);
//            result = (int) mTextPaint.measureText(mText);
            if (MeasureSpec.AT_MOST == mode) {
                result = Math.min(result, size);
            }

        }

        return result;
    }

    private int setMeasureHeight(int heightMeasureSpec) {

        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        if (MeasureSpec.EXACTLY == mode) {
            result = size;
        } else {
//            Log.i("MyView", "===" + mTextPaint.descent() + "===" + mTextPaint.ascent() + "===" + getPaddingBottom() + "===" + getPaddingTop() + "===" + size);
            result = (int) (mTextPaint.descent() - mTextPaint.ascent()) + getPaddingBottom() + getPaddingTop();
//            result = (int) (mTextPaint.descent() - mTextPaint.ascent());
            if (MeasureSpec.AT_MOST == mode) {
                result = Math.min(result, size);
            }
        }

        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mText, getPaddingLeft(), getPaddingTop() - mTextPaint.ascent(), mTextPaint);
    }
}
