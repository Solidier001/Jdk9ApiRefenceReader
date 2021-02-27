package com.example.myapplication.domain;

import org.jetbrains.annotations.NotNull;
import org.jsoup.select.Elements;
import java.util.ArrayList;

public class MethodSummaryTable {
    private String tableTittle;
    private ArrayList<MethodSummaryTableItem> tableBody;

    public MethodSummaryTable(String tableTittle,@NotNull Elements tables) {
        this.tableTittle = tableTittle;
        this.tableBody = new ArrayList(tables.size());
        for (int i=0;i<tables.size();i++){
            this.tableBody.add(new MethodSummaryTableItem(tables.get(i)));
        }
    }

    public String getTableTittle() {
        return tableTittle;
    }

    public ArrayList<MethodSummaryTableItem> getTableBody() {
        return tableBody;
    }

    public void setTableTittle(String tableTittle) {
        this.tableTittle = tableTittle;
    }

    public void setTableBody(ArrayList<MethodSummaryTableItem> tableBody) {
        this.tableBody = tableBody;
    }
}
