package com.example.myapplication.Component.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.domain.MethodSummaryTableItem;

import java.util.ArrayList;

public class MethodsFiller extends RecyclerView.Adapter {
    private ArrayList<MethodSummaryTableItem> methods;
    private ViewGroup parent;

    public MethodsFiller(ArrayList<MethodSummaryTableItem> methods, ViewGroup parent) {
        this.methods = methods;
        this.parent = parent;
    }

    public class MethodViewHoder extends RecyclerView.ViewHolder {
        private final LinearLayout method;
        public MethodViewHoder(@NonNull View itemView) {
            super(itemView);
            method= (LinearLayout) itemView;
        }
        public LinearLayout getMethod() {
            return method;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tri_table_row, parent, false);
        return new MethodViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        LinearLayout method= (LinearLayout) ((MethodViewHoder)viewHolder).getMethod();
        TextView returnType=method.findViewById(R.id.triTableFirst);
        TextView methodName=method.findViewById(R.id.triTableSecond);
        TextView Description=method.findViewById(R.id.triTableThird);
        returnType.setText(methods.get(i).getModifier_and_Type());
        methodName.setText(methods.get(i).getMethod());
        Description.setText(methods.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return methods.size();
    }
}
