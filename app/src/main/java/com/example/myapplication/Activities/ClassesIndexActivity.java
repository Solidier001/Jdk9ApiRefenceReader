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
import com.example.myapplication.Component.DocParser.JDK9JavaDocParser;
import com.example.myapplication.R;
import com.example.myapplication.domain.ClassesItem;

import java.io.IOException;
import java.util.ArrayList;

public class ClassesIndexActivity extends AppCompatActivity {
    String subUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_index);
        Intent intent = getIntent();
        subUri = intent.getStringExtra("herf");
        init();
    }
    private void init() {
        new AsyncTask<Void, Void, ArrayList<ClassesItem>>() {
            @Override
            protected ArrayList<ClassesItem> doInBackground(Void... voids) {
                JDK9JavaDocParser parser = ((SpiderApplication) ClassesIndexActivity.this.getApplication()).getDocServer();
                try {
                    return parser.getAllClassesIdex(subUri);
                } catch (IOException e) {
                    Toast toast = Toast.makeText(ClassesIndexActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(ArrayList<ClassesItem> classesItems) {
                RecyclerView Index = ClassesIndexActivity.this.findViewById(R.id.ClassesIndexActivity_Index);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ClassesIndexActivity.this);
                ClassesIndexAdapter adapter = new ClassesIndexAdapter(Index, classesItems);
                Index.setLayoutManager(linearLayoutManager);
                Index.setAdapter(adapter);
            }
        }.execute();
    }
}
