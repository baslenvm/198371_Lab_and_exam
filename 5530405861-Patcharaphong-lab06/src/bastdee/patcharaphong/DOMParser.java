package bastdee.patcharaphong;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Patcharaphong
 */
public class DOMParser {

    public static void main(String[] arg) {
        String documentName = DOMParser.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "nation.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(documentName));
            Element rootElement
                    = doc.getDocumentElement();
            System.out.println("The root element name is " + rootElement.getTagName());
        } catch(IOException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(DOMParser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
