import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author baslenvm
 */
public class CallRDWS {

    public void msgEnvelope() throws Exception {

        SOAPConnectionFactory soapConnectionFactory
                = SOAPConnectionFactory.newInstance();
        SOAPConnection connection
                = soapConnectionFactory.createConnection();
        MessageFactory messageFactory
                = MessageFactory.newInstance();

        // Create a message
        SOAPMessage message
                = messageFactory.createMessage();

        // Get the SOAP header and body from the message
        // and remove the header
        SOAPHeader header = message.getSOAPHeader();
        SOAPBody body = message.getSOAPBody();
        header.detachNode();

        // Create a SOAP factory
        // Create a UDDI v2 checkPin body element
        SOAPFactory soapFactory
                = SOAPFactory.newInstance();
        SOAPBodyElement checkPin
                = body.addBodyElement(soapFactory.createName("ServicePIN",
                                "ns", "https://rdws.rd.go.th/ServiceRD/CheckTINPINService"));

        SOAPElement username
                = checkPin.addChildElement(
                        soapFactory.createName("username", "ns",
                                "https://rdws.rd.go.th/ServiceRD/CheckTINPINService"));
        username.addTextNode("anonymous");

        SOAPElement password
                = checkPin.addChildElement(
                        soapFactory.createName("password", "ns",
                                "https://rdws.rd.go.th/ServiceRD/CheckTINPINService"));
        password.addTextNode("anonymous");

        SOAPElement pin
                = checkPin.addChildElement(
                        soapFactory.createName("PIN", "ns",
                                "https://rdws.rd.go.th/ServiceRD/CheckTINPINService"));
        pin.addTextNode("1479900218374");

        MimeHeaders hd = message.getMimeHeaders();
        hd.addHeader("SOAPAction",
                "https://rdws.rd.go.th/ServiceRD/CheckTINPINService/ServicePIN");

        message.saveChanges();
        System.out.println("REQUEST:");
        //Display Request Message
        displayMessage(message);

        System.out.println("\n\n");
        //add code below for trust x.509 ceritficate
        XTrustProvider.install();
        SOAPConnection conn
                = SOAPConnectionFactory.newInstance().createConnection();
        SOAPMessage response = conn.call(message,
                "https://rdws.rd.go.th/ServiceRD/CheckTINPINService.asmx");

        System.out.println("RESPONSE:");
        //Display Response Message
        displayMessage(response);
    }

    public void displayMessage(SOAPMessage message) throws Exception {
        TransformerFactory tFact = TransformerFactory.newInstance();
        Transformer transformer = tFact.newTransformer();
        Source src = message.getSOAPPart().getContent();
        StreamResult result = new StreamResult(System.out);
        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        transformer.transform(src, result);
    }

    public static void main(String[] args) throws Exception {
        CallRDWS clientApp = new CallRDWS();
        clientApp.msgEnvelope();
    }
}
