package com.example.myapplication.Component.Adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.domain.ConstructorSummaryTableItem;

import java.util.ArrayList;

public class ConstructorFiller extends RecyclerView.Adapter {
    private ArrayList<ConstructorSummaryTableItem> constructorSummaryTableItems;
    private ViewGroup parent;

    public ConstructorFiller(ArrayList<ConstructorSummaryTableItem> constructorSummaryTableItems, ViewGroup parent) {
        this.constructorSummaryTableItems = constructorSummaryTableItems;
        this.parent = parent;
    }
    private class ConstructorViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout Constructor;
        public ConstructorViewHolder(@NonNull View itemView) {
            super(itemView);
            Constructor= (LinearLayout) itemView;
        }

        public LinearLayout getConstructor() {
            return Constructor;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doube_table_row,parent,false);
        return new ConstructorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        LinearLayout constructor=((ConstructorViewHolder)viewHolder).getConstructor();
        TextView methodName=constructor.findViewById(R.id.doubleTableFirst);
        TextView Description=constructor.findViewById(R.id.doubleTableSecond);
        methodName.setText(constructorSummaryTableItems.get(i).getConstructor());
        methodName.setTextColor(Description.getTextColors());
        Description.setText(constructorSummaryTableItems.get(i).getDescription());
        Description.setTextColor(Color.rgb(100,100,100));
    }

    @Override
    public int getItemCount() {
        return constructorSummaryTableItems.size();
    }
}
