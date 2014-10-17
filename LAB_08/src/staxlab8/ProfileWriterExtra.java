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
public class ProfileWriterExtra {

    private static final String GEAR = "http://gear.kku.ac.th/Patcharaphong";
    private static final String XML = "http://www.w3.org/2001/XMLSchema-instance";
    private static final String HOME = "http://bandbcafe.ddns.net/Paatcharaphong";

    public static void main(String[] args) {
        String fileName = "myProfileExtra.xml";
        try {
            // output file name

// write an output factory
            XMLOutputFactory xof = XMLOutputFactory.newInstance();

// write an xml stream writer
            XMLStreamWriter xtw = xof.createXMLStreamWriter(new FileWriter(ProfileWriter.class.getProtectionDomain().getCodeSource().getLocation().getPath() + fileName));

            // xml declaration 
            xtw.writeStartDocument();
            xtw.setPrefix("kku", GEAR);
            xtw.setPrefix("bab", HOME);
            {

                xtw.writeStartElement(HOME, "profile");
                xtw.setDefaultNamespace(XML);
                xtw.writeNamespace("", XML);
                xtw.writeNamespace("kku", GEAR);
                xtw.writeNamespace("bab", HOME);
                {
                    xtw.writeCharacters("My Name is");
                    xtw.writeStartElement("name");
                    xtw.writeAttribute(GEAR, "id", "553040586-1");
                    {
                        xtw.writeCharacters("Patcharaphong Batdee");
                    }
                    xtw.writeEndElement(); // end name element
                    xtw.writeEmptyElement("br");
                    xtw.writeCData("<h1>Address<h1>");
                    xtw.writeStartElement(HOME,"Address");
                    xtw.writeAttribute("nation", "ไทย");
                    {
                        xtw.writeCharacters("34 หมู่ที่ 13 ต.สว่าง อ.พรรณานิคม จ.สกลนคร");
                    }
                    xtw.writeEndElement();
                }
                xtw.writeEndElement(); // end profile element
            }
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
