package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@WebServlet(urlPatterns = {"/CarDealerReport"})
public class CarDealerReportServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CarDealerReportServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CarDealerReportServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
//        try {
//            // Establish database connection
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_dealer", "alvin", "");
//
//            // Load compiled Jasper report
//            InputStream reportStream = getServletContext().getResourceAsStream(
//                        "/home/pc/JaspersoftWorkspaceV2/MyReports/car_dealer_report.jasper");
//            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
//
//            // Create parameters map (if needed)
//            Map<String, Object> parameters = new HashMap<>();
//
//            // Fill the report with data
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
//
//            // Set the response type
//            response.setContentType("application/pdf");
//
//            // Export the report to PDF and send it to the response output stream
//            net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//
//            // Close the database connection
//            connection.close();
//
//        } catch (JRException | SQLException e) {
//            e.printStackTrace();
//            // Handle exceptions as needed
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        try {
            Class.forName("com.jdbc.driver.class"); // Replace with your JDBC driver
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_dealer", "alvin", "");

            // Compile the JRXML file to JasperReport object
            String jrxmlPath = getServletContext().getRealPath("/home/pc/JaspersoftWorkspaceV2/MyReports/car_dealer_report.jrxml"); // Replace with your JRXML path
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(jrxmlPath),
                new HashMap<>(),
                connection
            );

            // Export the report to PDF and send it to the response
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=report.pdf");

            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            pdfExporter.exportReport();

            connection.close();
        } catch (ClassNotFoundException | SQLException | JRException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., show an error page)
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
