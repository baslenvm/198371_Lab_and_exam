/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staxlab8;

import java.io.FileWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author baslenvm
 */
public class ProfileWriter {

    public static void main(String[] args) {
        String fileName = "myProfile.xml";
        try {
            // output file name

// write an output factory
            XMLOutputFactory xof = XMLOutputFactory.newInstance();

// write an xml stream writer
            XMLStreamWriter xtw = xof.createXMLStreamWriter(new FileWriter(ProfileWriter.class.getProtectionDomain().getCodeSource().getLocation().getPath() + fileName));

            // xml declaration 
            xtw.writeStartDocument();
            xtw.writeStartElement("profile");
            xtw.writeStartElement("name");
            xtw.writeCharacters("Patcharaphong Batdee");
            xtw.writeEndElement(); // end profile element
            xtw.writeEndElement(); // end name element
            xtw.writeEndDocument();
            // write any cached data to the underlying 
// output stream
            xtw.flush();
            xtw.close();
        } catch (Exception ex) {
            System.err.println("Exception occurred while running writer1");
            ex.printStackTrace();
        }
        System.out.println("Finish writing " + fileName);
    }
}