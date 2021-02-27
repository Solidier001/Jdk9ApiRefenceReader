package com.example.myapplication.Applications;

import android.app.Application;

import com.example.myapplication.Component.DocParser.JDK9JavaDocParser;

import java.io.IOException;

public class SpiderApplication extends Application {
    private final JDK9JavaDocParser DocServer = new JDK9JavaDocParser();


    public SpiderApplication() throws IOException {
    }

    public JDK9JavaDocParser getDocServer() {
        return DocServer;
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }
}
