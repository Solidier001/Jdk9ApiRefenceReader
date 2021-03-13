package com.example.myapplication.Component.DocParser;


import com.example.myapplication.Activities.ClassesIndexActivity;
import com.example.myapplication.Activities.ModulesActivity;
import com.example.myapplication.Activities.PackagesActivity;
import com.example.myapplication.Utils.HtmlParser;
import com.example.myapplication.domain.ChoiceItem;
import com.example.myapplication.domain.ClassesItem;
import com.example.myapplication.domain.ConstructorSumaryTable;
import com.example.myapplication.domain.Doc;
import com.example.myapplication.domain.FieldSumaryTable;
import com.example.myapplication.domain.MethodSummaryTable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class JDK9JavaDocParser {
    Gson gson;
    Properties cssQuery;
    Properties spiderConfig;
    HtmlParser parser;

    private static final String[] blockTags={
            "code","em"
    };

    public JDK9JavaDocParser(Gson gson, Properties cssQuery, Properties spiderConfig, HtmlParser parser) {
        this.gson = gson;
        this.cssQuery = cssQuery;
        this.parser = parser;
        this.spiderConfig = spiderConfig;
    }

    public JDK9JavaDocParser(InputStream cssQueryInputStream, InputStream spiderConfigInputStream) throws IOException {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.cssQuery = new Properties();
        this.spiderConfig = new Properties();
        cssQuery.load(cssQueryInputStream);
        spiderConfig.load(spiderConfigInputStream);
        this.parser = new HtmlParser(spiderConfig.getProperty("baseUri"),blockTags);
    }

    public JDK9JavaDocParser() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.cssQuery = new Properties();
        this.spiderConfig = new Properties();
    }

    public void load(InputStream cssQueryInputStream, InputStream spiderConfigInputStream) throws IOException {
        cssQuery.load(cssQueryInputStream);
        spiderConfig.load(spiderConfigInputStream);
        this.parser = new HtmlParser(spiderConfig.getProperty("baseUri"),blockTags);
    }

    public Doc getDoc(String subUri) throws IOException {
        Document document = parser.getHtml(subUri);
        MethodSummaryTable methodSummaryTable = getMethodSummaryTable(document);
        ArrayList<String> BriefIntroduction = getBrifeintroduction_alternative(document);
        String Classname = getClassName(document);
        ConstructorSumaryTable constructorSumary = getConstructorSumaryTable(document);
        FieldSumaryTable fieldSumaryTable = getFieldSumaryTable(document);
        return new Doc(
                Classname,
                BriefIntroduction,
                methodSummaryTable,
                constructorSumary,
                fieldSumaryTable
        );
    }

    private MethodSummaryTable getMethodSummaryTable(@NotNull Document document) {
        try {
            Element method_summary_section = document.getElementById("method.summary").parent();
            Elements table = method_summary_section.select("table>tbody>tr");
            table.remove(0);
            Element tableTittle = method_summary_section.select("h3").first();
            MethodSummaryTable methodSummaryTable = new MethodSummaryTable(tableTittle.text(), table);
            return methodSummaryTable;
        } catch (IndexOutOfBoundsException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    private String getBrifeintroduction(Document document) {
        String brief_introduction = parser.getAttributesFromElementByCssQuery(
                cssQuery.getProperty("brief_introduction"), document, "TEXT").get(0);
        return brief_introduction;
    }

    private ArrayList<String> getBrifeintroduction_alternative(Document document) {
        Node element = null;
        int i;
        List<Node> nodes = parser.textNodesWithoutBlock(document, cssQuery.getProperty("brief_introduction"));
        ArrayList<String> strings = new ArrayList<>(nodes.size());
        for (Node node : nodes) {
            if (node instanceof TextNode)strings.add(((TextNode)node).text());
            else if (node instanceof Element) {
                if (node.nodeName().equals("ol") || node.nodeName().equals("ul")) {
                    i = 1;
                    Iterator<Node> iterator = node.childNodes().iterator();
                    while (iterator.hasNext()) {
                        element = iterator.next();
                        if (element instanceof Element) {
                            strings.add("     " + i + '.' + ((Element) element).text());
                            i++;
                        }
                    }
                    iterator = null;
                } else strings.add(((Element) node).text());
            }
        }
        return strings;
    }


    private String getClassName(Document document) {
        String ClassName = parser.getAttributesFromElementByCssQuery(
                cssQuery.getProperty("head_classname"), document, "TEXT").get(0);
        return ClassName;
    }

    private ConstructorSumaryTable getConstructorSumaryTable(@NotNull Document document) {
        try {
            Element constructor_summary_section = document.getElementById("constructor.summary").parent();
            Elements table = constructor_summary_section.select("table>tbody>tr");
            table.remove(0);
            Element tableTittle = constructor_summary_section.select("h3").first();
            ConstructorSumaryTable constructorSumaryTable = new ConstructorSumaryTable(tableTittle.text(), table);
            return constructorSumaryTable;
        } catch (IndexOutOfBoundsException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    private FieldSumaryTable getFieldSumaryTable(@NotNull Document document) {
        try {
            Element field_summary_section = document.getElementById("field.summary").parent();
            Elements table = field_summary_section.select("table>tbody>tr");
            table.remove(0);
            Element tableTittle = field_summary_section.select("h3").first();
            FieldSumaryTable fieldSumaryTable = new FieldSumaryTable(tableTittle.text(), table);
            return fieldSumaryTable;
        } catch (IndexOutOfBoundsException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public ArrayList<ClassesItem> getAllClassesIdex(String subUri) throws IOException {
        Document document = parser.getHtml(subUri);
        return parser.getUrisFromElementAttribute(
                cssQuery.getProperty("item_index"),
                document,
                "href",
                "TEXT"
        );
    }

    public ArrayList<ChoiceItem> getIndex() throws IOException {
        ArrayList<ChoiceItem> index = new ArrayList<ChoiceItem>(3);
        index.add(new ChoiceItem("All classes and interfaces (except non-static nested types)", spiderConfig.getProperty("AllClass"), ClassesIndexActivity.class));
        index.add(new ChoiceItem("AllPackages", spiderConfig.getProperty("AllPackages"), PackagesActivity.class));
        index.add(new ChoiceItem("AllModules", spiderConfig.getProperty("AllModules"), ModulesActivity.class));
        return index;
    }

    public ArrayList<ClassesItem> getAllPackages(String subUri) throws IOException {
        Document document = parser.getHtml(subUri);
        return parser.getUrisFromElementAttribute(
                cssQuery.getProperty("package_index"),
                document,
                "href",
                "TEXT"
        );
    }


    /* ***************************************************************************************
     *                                                                                       *
     *                                                                                       *
     * **********************************TEST FUNCTIONS***************************************
     *                                                                                       *
     *                                                                                       *
     * ***************************************************************************************/

    public ArrayList<String> getBrifeintroductionTEST(String subUri) throws IOException {
        Document document = parser.getHtml(subUri);
        return getBrifeintroduction_alternative(document);
    }

}
