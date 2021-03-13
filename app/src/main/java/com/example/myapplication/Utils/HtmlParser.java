package com.example.myapplication.Utils;

import com.example.myapplication.domain.ClassesItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HtmlParser {
    private HttpsURLConnection connection = null;

    private ArrayList<String> blockElements;
    private String baseUri;

    public HtmlParser(String baseUri,String[] names) {
        this.baseUri = baseUri;
        this.blockElements= new ArrayList<String>(Arrays.asList(names));
    }

    public Document getHtml() throws IOException {
        URL url = new URL(baseUri);
        InputStream inputStream = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
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
        URL url = new URL(baseUri + subUri);
        InputStream inputStream = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            inputStream = url.openStream();
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
        } else {
            for (Element item : elements) {
                Uris.add(new ClassesItem(item.attr(TittleAttr), item.attr(Attr)));
            }
        }
        return Uris;
    }

    public List<String> getAttributesFromElementByCssQuery(String cssQuery, Document document, String Attr) {
        if (Attr.equals("TEXT")) return document.select(cssQuery).eachText();
        return document.select(cssQuery).eachAttr(Attr);
    }

    public List<String> getAttributesFromElementByCssQuery(String cssQuery, Element element, String Attr) {
        if (Attr.equals("TEXT")) return element.select(cssQuery).eachText();
        return element.select(cssQuery).eachAttr(Attr);
    }

    public List<Node> textNodes(Document document, String cssQuery) {
        try {
            Element element = document.select(cssQuery).get(0);
            return element.childNodes();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public List<Node> textNodesWithoutBlock(Document document, String cssQuery) {
        try {
            StringBuilder content;
            Node node;
            int flag = 0;
            Element element = document.select(cssQuery).get(0);
            List<Node> children = element.childNodesCopy();
            for (int i = 0; i < children.size(); i++) {
                node = children.get(i);
                if (node instanceof Element) {
                    if (blockElements.contains(node.nodeName())) {
                        if (i > 0 && children.get(i - 1) instanceof TextNode) flag += 1;
                        if (i < children.size() - 1 && children.get(i + 1) instanceof TextNode)
                            flag += 2;
                        switch (flag) {
                            case 1: {
                                content = new StringBuilder(((TextNode) children.get(i - 1)).text());
                                content.append(((Element) node).text());
                                ((TextNode) children.get(i - 1)).text(content.toString());
                                children.remove(i);
                                break;
                            }
                            case 2: {
                                content = new StringBuilder(((Element) node).text());
                                content.append(((TextNode) children.get(i + 1)).text());
                                ((TextNode) children.get(i - 1)).text(content.toString());
                                children.remove(i);
                                break;
                            }
                            case 3: {
                                content = new StringBuilder(((TextNode) children.get(i - 1)).text());
                                content.append(((Element) node).text());
                                content.append(((TextNode) children.get(i + 1)).text());
                                ((TextNode) children.get(i - 1)).text(content.toString());
                                children.remove(i);
                                children.remove(i);
                                break;
                            }
                        }
                        i--;
                        flag = 0;
                    }
                }
            }
            return children;
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

}

