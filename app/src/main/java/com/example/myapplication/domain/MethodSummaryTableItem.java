package com.example.myapplication.domain;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MethodSummaryTableItem {
    private String Modifier_and_Type;
    private String Method;
    private String Description;

    public MethodSummaryTableItem(Element tableitem) {
        Elements tritems=tableitem.children();
        Modifier_and_Type = tritems.get(0).text();
        Method =tritems.get(1).text();
        Description = tritems.get(2).text();
    }

    public String getModifier_and_Type() {
        return Modifier_and_Type;
    }

    public String getMethod() {
        return Method;
    }

    public String getDescription() {
        return Description;
    }
}
