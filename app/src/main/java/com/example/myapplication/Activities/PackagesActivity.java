package com.example.myapplication.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.myapplication.Applications.SpiderApplication;
import com.example.myapplication.Component.Adapters.ClassesIndexAdapter;
import com.example.myapplication.Component.Adapters.PackagesIndexAdapter;
import com.example.myapplication.Component.DocParser.JDK9JavaDocParser;
import com.example.myapplication.R;
import com.example.myapplication.domain.ClassesItem;

import java.io.IOException;
import java.util.ArrayList;

public class PackagesActivity extends AppCompatActivity {

    String subUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        Intent intent = getIntent();
        subUri = intent.getStringExtra("herf");
        init();
    }
    private void init() {
        new AsyncTask<Void, Void, ArrayList<ClassesItem>>() {
            @Override
            protected ArrayList<ClassesItem> doInBackground(Void... voids) {
                JDK9JavaDocParser parser = ((SpiderApplication) PackagesActivity.this.getApplication()).getDocServer();
                try {
                    return parser.getAllPackages(subUri);
                } catch (IOException e) {
                    Toast toast = Toast.makeText(PackagesActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(ArrayList<ClassesItem> classesItems) {
                RecyclerView Index = PackagesActivity.this.findViewById(R.id.Package_Index);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PackagesActivity.this);
                PackagesIndexAdapter adapter = new PackagesIndexAdapter(classesItems,Index);
                Index.setLayoutManager(linearLayoutManager);
                Index.setAdapter(adapter);
            }
        }.execute();
    }
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
    }*/
}
