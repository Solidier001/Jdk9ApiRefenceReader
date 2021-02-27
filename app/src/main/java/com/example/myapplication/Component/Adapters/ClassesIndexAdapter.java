package com.example.myapplication.Component.Adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activities.ClassesIndexActivity;
import com.example.myapplication.Activities.DocContentActivity;
import com.example.myapplication.Component.IndexItem;
import com.example.myapplication.R;
import com.example.myapplication.domain.ClassesItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ClassesIndexAdapter extends RecyclerView.Adapter {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private ViewGroup parent;
    private ArrayList<ClassesItem> ClassesIndex;
    public class ClassesIndexViewHolder extends RecyclerView.ViewHolder{
        private final IndexItem textView;
        public ClassesIndexViewHolder(@NonNull final View itemView) {
            super(itemView);
            View.OnClickListener listener=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(parent.getContext(), DocContentActivity.class);
                    intent.putExtra("herf",textView.getUri());
                    parent.getContext().startActivity(intent);
                }
            };
            textView = ((LinearLayout)itemView).findViewById(R.id.IndexItem_textView);
            itemView.setOnClickListener(listener);
            textView.setOnClickListener(listener);
        }
        public TextView getTextView(){
            return textView;
        }
    }

    public ClassesIndexAdapter(ViewGroup parent, ArrayList<ClassesItem> classesIndex) {
        this.parent = parent;
        ClassesIndex = classesIndex;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item , parent, false);
        return new ClassesIndexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        IndexItem textView= (IndexItem) ((ClassesIndexViewHolder)viewHolder).getTextView();
        textView.setText(ClassesIndex.get(i).getTittle());
        textView.setUri(ClassesIndex.get(i).getHref());
    }

    @Override
    public int getItemCount() {
        return ClassesIndex.size();
    }
}
