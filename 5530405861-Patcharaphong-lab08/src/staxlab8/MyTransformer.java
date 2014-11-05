/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staxlab8;

import java.io.*;
import java.util.logging.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/**
 *
 * @author baslenvm
 */
public class MyTransformer {
    public static void main(String[] args){
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File(MyTransformer.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "catalog.xsl"));
            Transformer transformer = factory.newTransformer(xslt);
            
            Source text = new StreamSource(new File(MyTransformer.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "catalog.xml"));
            transformer.transform(text, new StreamResult(new File(MyTransformer.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "catalog.html")));
        } catch (TransformerException ex) {
            Logger.getLogger(MyTransformer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
