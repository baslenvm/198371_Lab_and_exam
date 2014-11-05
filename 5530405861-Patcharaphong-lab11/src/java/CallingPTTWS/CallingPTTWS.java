/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CallingPTTWS;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.ws.WebServiceRef;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author baslenvm
 */
@WebServlet(name = "CallingPTTWS", urlPatterns = {"/CallingPTTWS"})
public class CallingPTTWS extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/www.pttplc.com/webservice/pttinfo.asmx.wsdl")
    private PTTInfo service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SAXException, ParserConfigurationException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(new StringReader(currentOilPrice("en")));

            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet CallingPTTWS</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet CallingPTTWS at " + request.getContextPath() + "</h1>");
                int i=0;
                //start
                for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                    switch (event) {
                        case XMLStreamConstants.START_ELEMENT:
                            if (parser.getName().toString() == "DataAccess") {
                                if (i++ == 3) {
                                    i = 1;
                                    out.println("<br>");
                                }
                            }
                            break;
                        case XMLStreamConstants.CHARACTERS:
                            out.println(" " + parser.getText());
                            break;
                        case XMLStreamConstants.CDATA:
                            out.println(" " + parser.getText());
                            break;
                    } // end switch
                } // end for
                //end

                out.println("</body>");
                out.println("</html>");
            }

            parser.close();
        } catch (XMLStreamException ex) {
            System.err.println(ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SAXException | ParserConfigurationException ex) {
            Logger.getLogger(CallingPTTWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SAXException | ParserConfigurationException ex) {
            Logger.getLogger(CallingPTTWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String currentOilPrice(java.lang.String language) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        PTTInfoSoap port = service.getPTTInfoSoap12();
        return port.currentOilPrice(language);
    }
}
