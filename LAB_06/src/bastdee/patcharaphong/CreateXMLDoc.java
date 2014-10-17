/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bastdee.patcharaphong;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

/**
 *
 * @author Patcharaphong
 */
public class CreateXMLDoc {

    public static void main(String[] args) throws FileNotFoundException, TransformerConfigurationException, TransformerException {
        try {
            String xmlProfile = CreateXMLDoc.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "myProfile.xml";
            System.out.println(xmlProfile);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            //create various nodes
            Element root = doc.createElement("Profile");
            Element nameE = doc.createElement("name");
            Text nameT = doc.createTextNode("Patcharaphong Batdee");

            //Link relationships between created nodes
            nameE.appendChild(nameT);
            root.appendChild(nameE);
            doc.appendChild(root);

            OutputStream os = new FileOutputStream(xmlProfile);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(os));
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CreateXMLDoc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
