package com.example.myapplication.domain;

public class Doc {
    private String ClassName;
    private String Brifeintroduction;
    private MethodSummaryTable methodSummaryTable;
    private ConstructorSumaryTable constructorSumary;
    private FieldSumaryTable fieldSumaryTable;

    public Doc(String className, String brifeintroduction, MethodSummaryTable methodSummaryTable, ConstructorSumaryTable constructorSumary, FieldSumaryTable fieldSumaryTable) {
        ClassName = className;
        Brifeintroduction = brifeintroduction;
        this.methodSummaryTable = methodSummaryTable;
        this.constructorSumary = constructorSumary;
        this.fieldSumaryTable = fieldSumaryTable;
    }

    public String getClassName() {
        return ClassName;
    }

    public String getBrifeintroduction() {
        return Brifeintroduction;
    }

    public MethodSummaryTable getMethodSummaryTable() {
        return methodSummaryTable;
    }

    public ConstructorSumaryTable getConstructorSumary() {
        return constructorSumary;
    }

    public FieldSumaryTable getFieldSumaryTable() {
        return fieldSumaryTable;
    }
}
