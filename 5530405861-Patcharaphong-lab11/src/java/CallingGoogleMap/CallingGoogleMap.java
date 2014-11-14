/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CallingGoogleMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author baslenvm
 */
@WebServlet(name = "CallingGoogleMap", urlPatterns = {"/CallingGoogleMap"})
public class CallingGoogleMap extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address="+request.getParameterValues("q")[0]+"&sensor=false&language=en");
        NewJSONStrirng jsonstring = new NewJSONStrirng(url);
        JSONObject jsonObject = jsonstring.toJSONObject();
        JSONArray actArray = (JSONArray) jsonObject.get("activities");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CallingGoogleMap</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CallingGoogleMap at " + request.getContextPath() + "</h1>");
            out.append(jsonObject.toString());
            out.println("</body>");
            out.println("</html>");
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
                Logger.getLogger(NewJSONStrirng.class.getName()).log(Level.SEVERE, null, ex);
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
