package dev.mlqs.myblog.utils;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class XMLUtils {
    private static ArrayList<String[]> list;
    public static ArrayList<String[]> getNodeList(String uri) {
        if (list != null) return list;

        try {
            File f = new File(uri);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            NodeList nodeList = doc.getElementsByTagName("item");
            NamedNodeMap cNodeList;
            list = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); ++i) {
                cNodeList = nodeList.item(i).getAttributes();
                list.add(new String[]{cNodeList.getNamedItem("key").getTextContent(),
                        cNodeList.getNamedItem("value").getTextContent()});
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
