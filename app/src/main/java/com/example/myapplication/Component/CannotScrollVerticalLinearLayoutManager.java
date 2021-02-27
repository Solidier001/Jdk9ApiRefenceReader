package com.example.myapplication.Component;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class CannotScrollVerticalLinearLayoutManager extends LinearLayoutManager {

    public CannotScrollVerticalLinearLayoutManager(Context context) {
        super(context);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
