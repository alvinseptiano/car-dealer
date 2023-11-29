package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

@WebServlet(name = "JasperReportServlet", urlPatterns = {"/generateJasperReport"})
public class JasperReportServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet JasperReportServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet JasperReportServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        String url = "jdbc:mysql://localhost/car_dealer";
        String username = "alvin";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    
            connection = DriverManager.getConnection(url, username, password);
            String path = "/home/pc/repos/java/AplikasiDealerMobil/src/main/webapp/car_dealer_report.jrxml";
            JasperCompileManager.compileReportToFile(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "/home/pc/repos/java/AplikasiDealerMobil/src/main/webapp/car_dealer_report.jasper", 
                    new HashMap<>(), connection);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            Class.forName("com.mysql.cj.jdbc.Driver");

//            String jrxmlPath = getServletContext().getRealPath(
//                       "car_dealer_report.jrxml");
//            JasperPrint jasperPrint = JasperFillManager.fillReport(
//                JasperCompileManager.compileReport(jrxmlPath),
//                new HashMap<>(),
//                connection
//            );
            // Close the database connection
            connection.close();
            // View the report using JasperViewer
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JasperReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
