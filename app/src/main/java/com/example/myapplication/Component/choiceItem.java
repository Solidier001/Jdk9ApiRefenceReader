package com.example.myapplication.Component;

import android.content.Context;
import android.util.AttributeSet;

public class choiceItem extends android.support.v7.widget.AppCompatTextView {
    private String Uri = null;
    private Class activity=null;

    public choiceItem(Context context) {
        super(context);
    }

    public choiceItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public choiceItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public String getUri() {
        return Uri;
    }

    public Class getActivity() {
        return activity;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }
}
