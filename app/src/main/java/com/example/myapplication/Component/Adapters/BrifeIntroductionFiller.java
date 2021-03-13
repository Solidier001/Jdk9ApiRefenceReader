package com.example.myapplication.Component.Adapters;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class BrifeIntroductionFiller extends RecyclerView.Adapter {
    private ArrayList<String> ItemStrings;
    private ViewGroup parent;

    private class ItemHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.NormalText);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public BrifeIntroductionFiller(ArrayList<String> itemStrings, ViewGroup parent) {
        ItemStrings = itemStrings;
        this.parent = parent;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.normal_text_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 0);
        TextView textView = ((ItemHolder) viewHolder).getTextView();
        textView.setPadding(4,0,0,0);
        textView.setText(ItemStrings.get(i));
        textView.setTypeface(Typeface.MONOSPACE);
        textView.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return ItemStrings.size();
    }
}
