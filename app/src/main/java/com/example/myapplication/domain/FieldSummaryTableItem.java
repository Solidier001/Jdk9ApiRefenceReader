package com.example.myapplication.domain;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FieldSummaryTableItem {
    private String ModifierandType;
    private String Field;
    private String Description;

    public FieldSummaryTableItem(Element tableitem) {
        Elements codes=tableitem.children();
        this.ModifierandType = codes.get(0).text();
        this.Field =codes.get(1).text();
        this.Description = codes.get(2).text();
    }

    public String getModifierandType() {
        return ModifierandType;
    }

    public String getField() {
        return Field;
    }

    public String getDescription() {
        return Description;
    }
}
