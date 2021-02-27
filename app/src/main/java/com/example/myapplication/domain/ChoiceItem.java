package com.example.myapplication.domain;

public class ChoiceItem {
    private String tittle;
    private String href;
    private Class<?> activity;

    public ChoiceItem(String tittle, String href, Class<?> activity) {
        this.tittle = tittle;
        this.href = href;
        this.activity = activity;
    }

    public String getTittle() {
        return tittle;
    }

    public String getHref() {
        return href;
    }

    public Class<?> getActivity() {
        return activity;
    }
}
