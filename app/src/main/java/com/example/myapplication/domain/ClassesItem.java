package com.example.myapplication.domain;

import java.io.Serializable;

public class ClassesItem implements Serializable {

    private String tittle;
    private String href;

    public ClassesItem(String tittle, String href) {
        this.tittle = tittle;
        this.href = href;
    }

    public String getTittle() {
        return tittle;
    }

    public String getHref() {
        return href;
    }

    @Override
    public String toString() {
        return "ClassesItem{" +
                "tittle='" + tittle + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
