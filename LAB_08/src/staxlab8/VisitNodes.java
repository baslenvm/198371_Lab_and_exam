package staxlab8;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author baslenvm
 */
public class VisitNodes {
    public static void main(String[] args) {
        String input = MyTransformer.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "nation.xml";
        try {
            FileInputStream in = new FileInputStream(input);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);
            //start
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        System.out.println("Element name = " + parser.getName());
                        if(parser.getAttributeCount() !=0)
                            for(int i = 0; i < parser.getAttributeCount(); i++){
                                System.out.println("Attribute name: " + parser.getAttributeLocalName(i) + " = " + parser.getAttributeValue(i));
                            }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        break;
                    case XMLStreamConstants.CHARACTERS:
                            System.out.println("#Text = " + parser.getText());
                        break;
                    case XMLStreamConstants.CDATA:
                            System.out.println("#Text = " + parser.getText());
                        break;
                } // end switch
            } // end for
            //end
            parser.close();
        } catch (XMLStreamException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.out.println("IOException while parsing " + input);
            System.err.println(ex);
        } // end try-catch
    } // end main
}
