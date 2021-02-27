package com.example.myapplication.Applications;

import android.app.Application;

import com.example.myapplication.Caches.Cache;
import com.example.myapplication.Caches.DefaultCache;

public class ApplicationWithCache extends Application {
    Cache cache;

    public ApplicationWithCache() {
        this.cache = new DefaultCache();
    }
}
