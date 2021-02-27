package com.example.myapplication.Utils;

import com.example.myapplication.domain.ClassesItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HtmlParser {
    private HttpsURLConnection connection = null;;

    private String baseUri;

    public HtmlParser(String baseUri) {
        this.baseUri = baseUri;
    }

    public Document getHtml() throws IOException {
        URL url=new URL(baseUri);
        InputStream inputStream=null;
        try {
            connection= (HttpsURLConnection) url.openConnection();
            inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String tempt = null;
            while ((tempt = bufferedReader.readLine()) != null) stringBuffer.append(tempt);
            Document document = Jsoup.parse(stringBuffer.toString());
            return document;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public Document getHtml(String subUri) throws IOException {
        URL url=new URL(baseUri + subUri);
        InputStream inputStream=null;
        try {
            connection= (HttpsURLConnection) url.openConnection();
            inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String tempt = null;
            while ((tempt = bufferedReader.readLine()) != null) stringBuffer.append(tempt);
            Document document = Jsoup.parse(stringBuffer.toString());
            return document;
        }finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public ArrayList getUrisFromElementAttribute(String cssQuery, Element element, String Attr, String TittleAttr) {
        Elements elements = element.select(cssQuery);
        ArrayList<ClassesItem> Uris = new ArrayList<>(elements.size());
        for (Element item : elements) {
            Uris.add(new ClassesItem(item.attr(TittleAttr), item.attr(Attr)));
        }
        return Uris;
    }

    public ArrayList getUrisFromElementAttribute(String cssQuery, Document document, String Attr, String TittleAttr) {
        Elements elements = document.select(cssQuery);
        ArrayList<ClassesItem> Uris = new ArrayList<>(elements.size());
        if (TittleAttr.equals("TEXT")) {
            for (Element item : elements) {
                Uris.add(new ClassesItem(item.text(), item.attr(Attr)));
            }
        }
        else {
            for (Element item : elements) {
                Uris.add(new ClassesItem(item.attr(TittleAttr), item.attr(Attr)));
            }
        }
        return Uris;
    }
    public List<String> getAttributesFromElementByCssQuery(String cssQuery, Document document, String Attr){
        if (Attr.equals("TEXT"))return document.select(cssQuery).eachText();
        return document.select(cssQuery).eachAttr(Attr);
    }
 public List<String> getAttributesFromElementByCssQuery(String cssQuery, Element element, String Attr){
        if (Attr.equals("TEXT"))return element.select(cssQuery).eachText();
        return element.select(cssQuery).eachAttr(Attr);
    }

}

