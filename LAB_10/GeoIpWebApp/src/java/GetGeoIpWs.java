/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/**
 *
 * @author baslenvm
 */
public class GetGeoIpWs extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {
    	response.setContentType("text/xml;charset=UTF-8");
    	String ipAddr = request.getParameter("ip");
    	PrintWriter out = response.getWriter();
    	try {
            // สร้าง MessageFactory เพื่อเตรียมสร้าง SOAPMessage
        	MessageFactory mFac = MessageFactory.newInstance();
            // สร้าง SOAPMessage จาก MessageFactory
        	SOAPMessage message = mFac.createMessage();
            // ดึงส่วนของ SOAPBody จาก SOAPMessage
        	SOAPBody body = message.getSOAPBody();
            // สร้าง SOAPFactory เพื่อเตรียมสร้างรายละเอียดของ SOAP
        	SOAPFactory soapFactory = SOAPFactory.newInstance();
            // สร้าง object Name (javax.xml.soap.Name)
            // ที่มี local name คือ GetGeoIP มี prefix คือ ns และ
            // มี namespace คือ http://www.webservicex.net/
      	  String prefix = "ns";
        	String namespace = "http://www.webservicex.net/";
        	Name opName = soapFactory.createName("GetGeoIP",
                      	prefix, namespace);
            // ใส่ opName เข้าไปใน SOAPBody
        	SOAPBodyElement opElem = body.addBodyElement(opName);
            // สร้าง SOAPElement ของ IpAddress
        	SOAPElement ip = opElem.addChildElement(
                    soapFactory.createName("IPAddress", prefix, namespace));
  	ip.addTextNode(ipAddr); // เพิ่ม ip ที่ต้องการค้นหา
            // เพิ่มส่วนการกำหนด SOAPAction
        	MimeHeaders header = message.getMimeHeaders();
        	header.addHeader("SOAPAction", namespace + "GetGeoIP");
            // แสดงข้อความ SOAP Request
//        	displayMessage(message, out);
            // สร้าง SOAPConnection เพื่อเรียกใช้เว็บเซอร์วิส และรับค่าผลลัพธ์ด้วย SOAPMessage
        	SOAPConnection soapConn =
                  	SOAPConnectionFactory.newInstance().createConnection();
        	String endpoint = "http://www.webservicex.net/geoipservice.asmx";
        	SOAPMessage resp = soapConn.call(message, endpoint);
            // แสดงข้อความ SOAP Response
        	displayMessage(resp, out);
    	} catch (Exception ex) {
        	ex.printStackTrace();
    	} finally {
        	out.close();
    	}
}// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
private void displayMessage(SOAPMessage message, PrintWriter out) {
    	try {
        	TransformerFactory tFac = TransformerFactory.newInstance();
        	Transformer transformer = tFac.newTransformer();
        	Source src = message.getSOAPPart().getContent();
        	StreamResult result = new StreamResult(out);
 	       transformer.transform(src, result);
    	} catch (Exception ex) {
        	ex.printStackTrace();
    	}
	}
}
