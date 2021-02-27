package com.example.myapplication.Activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.myapplication.Applications.SpiderApplication;
import com.example.myapplication.Component.Adapters.ChoicesIndexAdapter;
import com.example.myapplication.Component.DocParser.JDK9JavaDocParser;
import com.example.myapplication.R;
import com.example.myapplication.domain.ChoiceItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            InputStream inputStream1 = getApplication().getAssets().open("cssQuery.properties");
            InputStream inputStream2 = getApplication().getAssets().open("SpiderConfig.properties");
            ((SpiderApplication) MainActivity.this.getApplication()).getDocServer().load(inputStream1, inputStream2);
            init();
        } catch (IOException e) {
            Toast toast = Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    private void init() {
        new AsyncTask<Void, Void, ArrayList<ChoiceItem>>() {
            @Override
            protected ArrayList<ChoiceItem> doInBackground(Void... voids) {
                JDK9JavaDocParser parser = ((SpiderApplication) MainActivity.this.getApplication()).getDocServer();
                try {
                    return parser.getIndex();
                } catch (IOException e) {
                    Toast toast = Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(ArrayList<ChoiceItem> classesItems) {
                RecyclerView choices = MainActivity.this.findViewById(R.id.MainActivity_choices);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                ChoicesIndexAdapter adapter = new ChoicesIndexAdapter( classesItems,choices);
                choices.setLayoutManager(linearLayoutManager);
                choices.setAdapter(adapter);
            }
        }.execute();
    }
}
