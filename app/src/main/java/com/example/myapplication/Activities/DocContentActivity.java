package com.example.myapplication.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Applications.SpiderApplication;
import com.example.myapplication.Component.Adapters.MethodsFiller;
import com.example.myapplication.Component.CannotScrollVerticalLinearLayoutManager;
import com.example.myapplication.Component.DocParser.JDK9JavaDocParser;
import com.example.myapplication.R;
import com.example.myapplication.domain.Doc;

import java.io.IOException;

public class DocContentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String subUri = getIntent().getStringExtra("herf");
        init(subUri);
    }

    private void init(String subUri) {
        new AsyncTask<String, Void, Doc>() {
            @Override
            protected Doc doInBackground(String... strings) {
                JDK9JavaDocParser parser = ((SpiderApplication) DocContentActivity.this.getApplication()).getDocServer();
                try {
                    return parser.getDoc(strings[0]);
                } catch (IOException e) {
                    Toast toast = Toast.makeText(DocContentActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Doc doc) {
                DocContentActivity.this.setContentView(R.layout.activity_doccontent);
                TextView className = DocContentActivity.this.findViewById(R.id.DocContent_Classname);
                TextView brifeintroduction = DocContentActivity.this.findViewById(R.id.DocContent_BriefIntroduction);
                className.setText(doc.getClassName());
                brifeintroduction.setText(doc.getBrifeintroduction());
                RecyclerView MethodSummaryTable = DocContentActivity.this.findViewById(R.id.doccontent_MethodTable);
                CannotScrollVerticalLinearLayoutManager linearLayoutManager = new CannotScrollVerticalLinearLayoutManager(DocContentActivity.this);
                MethodsFiller adapter;
                if (doc.getMethodSummaryTable()!=null)
                    adapter = new MethodsFiller(doc.getMethodSummaryTable().getTableBody(),MethodSummaryTable);
                else adapter=null;
                MethodSummaryTable.setLayoutManager(linearLayoutManager);
                MethodSummaryTable.setAdapter(adapter);
            }
        }.execute(subUri);
    }
}
