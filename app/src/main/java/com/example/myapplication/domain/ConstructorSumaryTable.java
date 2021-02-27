package com.example.myapplication.domain;

import org.jetbrains.annotations.NotNull;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ConstructorSumaryTable {
    private String tableTittle;
    private ArrayList<ConstructorSummaryTableItem> tableBody;

    public ConstructorSumaryTable(String tableTittle,@NotNull Elements tables) {
        this.tableTittle = tableTittle;
        this.tableBody = new ArrayList(tables.size());
        for (int i=0;i<tables.size();i++){
            this.tableBody.add(new ConstructorSummaryTableItem(tables.get(i)));
        }
    }

    public String getTableTittle() {
        return tableTittle;
    }

    public ArrayList<ConstructorSummaryTableItem> getTableBody() {
        return tableBody;
    }
}
