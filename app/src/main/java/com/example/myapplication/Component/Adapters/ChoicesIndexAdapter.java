package com.example.myapplication.Component.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activities.ClassesIndexActivity;
import com.example.myapplication.Component.IndexItem;
import com.example.myapplication.Component.choiceItem;
import com.example.myapplication.R;
import com.example.myapplication.domain.ChoiceItem;
import com.example.myapplication.domain.ClassesItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class ChoicesIndexAdapter extends RecyclerView.Adapter {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ViewGroup parent;
    private ArrayList<ChoiceItem> Choices;
    public class ChoicesIndexViewHolder extends RecyclerView.ViewHolder{
        private final choiceItem textView;
        public ChoicesIndexViewHolder(@NonNull final View itemView) {
            super(itemView);
            View.OnClickListener listener=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(parent.getContext(), textView.getActivity());
                    intent.putExtra("herf",textView.getUri());
                    parent.getContext().startActivity(intent);
                }
            };
            textView = ((LinearLayout)itemView).findViewById(R.id.choiceItem_textView);
            itemView.setOnClickListener(listener);
            textView.setOnClickListener(listener);
        }
        public TextView getTextView(){
            return textView;
        }
    }

    public ChoicesIndexAdapter(ArrayList<ChoiceItem> choices,ViewGroup parent) {
        this.parent = parent;
        Choices = choices;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activiti_choice , parent, false);/*View.inflate(viewGroup.getContext(),, null);*/
        return new ChoicesIndexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        choiceItem textView= (choiceItem) ((ChoicesIndexViewHolder)viewHolder).getTextView();
        textView.setText(Choices.get(i).getTittle());
        textView.setUri(Choices.get(i).getHref());
        textView.setActivity(Choices.get(i).getActivity());
    }

    @Override
    public int getItemCount() {
        return Choices.size();
    }

}
