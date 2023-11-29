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
import java.sql.PreparedStatement;

@WebServlet(name = "DB", urlPatterns = {"/DB"})
public class DB extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car_dealer";
    private static final String USERNAME = "alvin";
    private static final String PASSWORD = "";
    
    public static boolean insertData(String tableName, String[] columnNames, Object[] values) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                // Prepare the SQL statement
                StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
                for (int i = 0; i < columnNames.length; i++) {
                    sqlBuilder.append(columnNames[i]);
                    if (i < columnNames.length - 1) {
                        sqlBuilder.append(", ");
                    }
                }
                sqlBuilder.append(") VALUES (");
                for (int i = 0; i < values.length; i++) {
                    sqlBuilder.append("?");
                    if (i < values.length - 1) {
                        sqlBuilder.append(", ");
                    }
                }
                sqlBuilder.append(")");

                PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());

                // Set parameter values
                for (int i = 0; i < values.length; i++) {
                    preparedStatement.setObject(i + 1, values[i]);
                }

                // Execute the statement
                int rowsAffected = preparedStatement.executeUpdate();

                // Close resources
                preparedStatement.close();

                // Check if the insertion was successful
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DB</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DB at " + request.getContextPath() + "</h1>");
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

}
