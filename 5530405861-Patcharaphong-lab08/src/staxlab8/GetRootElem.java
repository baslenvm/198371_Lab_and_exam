/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staxlab8;

import java.io.*;
import javax.xml.stream.*;

/**
 *
 * @author baslenvm
 */
public class GetRootElem {

    public static void main(String[] args) {
        String input = MyTransformer.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "nation.xml";
        try {
            FileInputStream in = new FileInputStream(input);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);
            parser.next();
            System.out.println("The root element is " + parser.getName());
            parser.close();
        } catch (XMLStreamException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.out.println("IOException while parsing " + input);
            System.err.println(ex);
        } // end try-catch
    } // end main
}
