package com.example.myapplication.Component.Adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.Activities.ClassesIndexActivity;
import com.example.myapplication.Activities.DocContentActivity;
import com.example.myapplication.Component.IndexItem;
import com.example.myapplication.R;
import com.example.myapplication.domain.ClassesItem;

import java.util.ArrayList;

public class PackagesIndexAdapter extends RecyclerView.Adapter {
    private ArrayList<ClassesItem> Packages;
    private ViewGroup parent;

    public class PackagesViewHolder extends RecyclerView.ViewHolder{
        private final IndexItem textView;
        public PackagesViewHolder(@NonNull final View itemView) {
            super(itemView);
            View.OnClickListener listener=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(parent.getContext(), ClassesIndexActivity.class);
                    intent.putExtra("herf",textView.getUri());
                    intent.putExtra("parentHerf",textView.getUri().replace("package-frame.html",""));
                    parent.getContext().startActivity(intent);
                }
            };
            textView = ((LinearLayout)itemView).findViewById(R.id.IndexItem_textView);
            itemView.setOnClickListener(listener);
            textView.setOnClickListener(listener);
        }

        public IndexItem getTextView() {
            return textView;
        }
    }

    public PackagesIndexAdapter(ArrayList<ClassesItem> packages, ViewGroup parent) {
        Packages = packages;
        this.parent = parent;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item , parent, false);
        return new PackagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        IndexItem item=((PackagesViewHolder) viewHolder).getTextView();
        item.setUri(Packages.get(i).getHref());
        item.setText(Packages.get(i).getTittle());
    }

    @Override
    public int getItemCount() {
        return Packages.size();
    }
}
