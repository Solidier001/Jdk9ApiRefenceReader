package com.example.myapplication.Component.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.domain.FieldSummaryTableItem;

import java.util.ArrayList;

public class FieldFiller extends RecyclerView.Adapter {
    private ArrayList<FieldSummaryTableItem>  fieldSummaryTableItems;
    private ViewGroup parent;

    private class FieldViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout Field;
        public FieldViewHolder(@NonNull View itemView) {
            super(itemView);
            Field= (LinearLayout) itemView;
        }

        public LinearLayout getField() {
            return Field;
        }
    }

    public FieldFiller(ArrayList<FieldSummaryTableItem> fieldSummaryTableItems, ViewGroup parent) {
        this.fieldSummaryTableItems = fieldSummaryTableItems;
        this.parent = parent;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tri_table_row,parent,false);
        return new FieldViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        LinearLayout field=((FieldViewHolder)viewHolder).getField();
        TextView fieldType=field.findViewById(R.id.triTableFirst);
        TextView fieldName=field.findViewById(R.id.triTableSecond);
        TextView Description=field.findViewById(R.id.triTableThird);
        fieldType.setText(fieldSummaryTableItems.get(i).getModifierandType());
        fieldName.setText(fieldSummaryTableItems.get(i).getField());
        Description.setText(fieldSummaryTableItems.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return fieldSummaryTableItems.size();
    }
}
