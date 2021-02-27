package com.example.myapplication.domain;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ConstructorSummaryTableItem {
    private String Constructor;
    private String Description;

    public ConstructorSummaryTableItem(@NotNull Element tableitem) {
        Elements codes=tableitem.children();
        Constructor = codes.get(0).text();
        Description =codes.get(1).text();
    }

    public String getConstructor() {
        return Constructor;
    }

    public String getDescription() {
        return Description;
    }
}
