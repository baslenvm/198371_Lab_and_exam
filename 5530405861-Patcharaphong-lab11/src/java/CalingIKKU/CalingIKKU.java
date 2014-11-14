/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalingIKKU;

import org.json.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 *
 * @author baslenvm
 */
@WebServlet(name = "CalingIKKU", urlPatterns = {"/CalingIKKU"})
public class CalingIKKU extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        URL url = new URL("http://www.kku.ac.th/ikku/api/activities/services/topActivity.php");
        NewJSONStrirng jsonstring = new NewJSONStrirng(url);
        JSONObject jsonObject = jsonstring.toJSONObject();
        JSONArray actArray = (JSONArray) jsonObject.get("activities");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.append("<!DOCTYPE html>");
            out.append("<html>");
            out.append("<head>");
            out.append("<title>Servlet CalingIKKU</title>");
            out.append("</head>");
            out.append("<body>");
            out.append("<h1>Servlet CalingIKKU at " + request.getContextPath() + "</h1>");
            out.append("<ul>");
            for (int i = 0; i < actArray.length(); i++) {
                JSONObject act = (JSONObject) actArray.get(i);
                out.append("<li>" + act.getString("dateSt") + " ");
                out.append("<a href=\"" + act.getString("url") + "\">" + act.getString("title") + "</a></li>");
            }
            out.append("</ul>");
            out.append("</body>");
            out.append("</html>");
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

    class NewJSONStrirng {

        private String json;
        private URL website;

        public NewJSONStrirng(String url) throws MalformedURLException {
            this(new URL(url));
        }

        public NewJSONStrirng(URL url) {
            try {
                StringBuilder response;
                this.website = url;
                URLConnection connection = this.website.openConnection();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));
                response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                this.json = response.toString();
                in.close();

            } catch (IOException ex) {
                Logger.getLogger(CalingIKKU.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public JSONObject toJSONObject() {
            return new JSONObject(this.json);
        }

        @Override
        public String toString() {
            return this.json; //To change body of generated methods, choose Tools | Templates.
        }
    }
}
