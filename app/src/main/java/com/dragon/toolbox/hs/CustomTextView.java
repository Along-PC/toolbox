package com.dragon.toolbox.hs;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class CustomTextView extends AppCompatTextView {

    private LifecyclerCallback lifecyclerCallback;
    private static final String TAG = "CustomTextView";

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLifecyclerCallback(LifecyclerCallback lifecyclerCallback) {
        this.lifecyclerCallback = lifecyclerCallback;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow() called");
        if(lifecyclerCallback!=null) lifecyclerCallback.attach();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow() called");
        if(lifecyclerCallback!=null) lifecyclerCallback.detach();
    }

    public interface LifecyclerCallback{

        public void attach();

        public void detach();

    }

}
