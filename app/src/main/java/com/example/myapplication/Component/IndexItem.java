package com.example.myapplication.Component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class IndexItem extends android.support.v7.widget.AppCompatTextView {
    private String Uri = null;

    public IndexItem(Context context) {
        super(context);
    }

    public IndexItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IndexItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }
}