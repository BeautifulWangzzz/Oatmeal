package com.plum.oatmeal.application.util;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {

    private ArrayList<File> XMLFiles = new ArrayList<>();
    private Map<String, Class> structureMap = new HashMap<>();

    public void addXML(File file) {
        XMLFiles.add(file);
    }

    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    private Document getDocument(File file) {
        Document document = null;
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public void parse() {
        //For every xmlFile
        for (File xmlFile : XMLFiles) {

            Document document = getDocument(xmlFile);
            NodeList nodeList = document.getElementsByTagName("structure");

            //For every structure
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                NodeList children = node.getChildNodes();

                String name = null, className = null;
                //For name and class-name in the structure node
                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    if (child.getNodeName().equals("name")) {
                        name = child.getTextContent();
                    } else if (child.getNodeName().equals("class-name")) {
                        className = child.getTextContent();
                    }
                }

                try {
                    structureMap.put(name, Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<String, Class> getStructureMap() {
        return structureMap;
    }
}
