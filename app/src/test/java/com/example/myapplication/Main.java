package com.example.myapplication;


import com.example.myapplication.Component.DocParser.JDK9JavaDocParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;

public class Main {
    public void test() throws Exception {
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        File cssQuery=new File("F:\\MyApplication\\app\\src\\main\\assets\\cssQuery.properties");
        File SpiderConfig=new File("F:\\MyApplication\\app\\src\\main\\assets\\SpiderConfig.properties");
        FileInputStream cssQueryInputStream=new FileInputStream(cssQuery);
        FileInputStream SpiderConfigInputStream=new FileInputStream(SpiderConfig);
        JDK9JavaDocParser jdk9JavaDocParser=new JDK9JavaDocParser(cssQueryInputStream,SpiderConfigInputStream);
        jdk9JavaDocParser.
        System.out.println(gson.toJson(jdk9JavaDocParser.getIndex()));
    }
}
