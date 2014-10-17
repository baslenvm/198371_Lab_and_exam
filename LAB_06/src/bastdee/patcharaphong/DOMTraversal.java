/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bastdee.patcharaphong;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Patcharaphong
 */
public class DOMTraversal {

    public static void main(String[] arg) {
        String documentName = DOMParser.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "nation.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(documentName));
            Element rootElement = doc.getDocumentElement();
            followNode(rootElement);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void followNode(Node node) {
        writeNode(node);
        if (node.hasChildNodes()) {
            String name = node.getNodeName();
            int numChildren = node.getChildNodes().getLength();
            System.out.println("node " + name + " has " + numChildren + " children");
            Node firstChild = node.getFirstChild();
            followNode(firstChild);
        }
        Node nextNode = node.getNextSibling();
        if (nextNode != null) {
            followNode(nextNode);
        }
    }

    private static void writeNode(Node node) {
        System.out.println("Node:type = " + node.getNodeType() + " name = " + node.getNodeName() + " value = " + node.getNodeValue());
    }
}
