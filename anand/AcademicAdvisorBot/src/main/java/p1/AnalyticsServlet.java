package p1;

import java.io.*;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AnalyticsServlet")
public class AnalyticsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/academicbot",
                    "root",
                    "Himanshu#9546"
            );

            // 🔥 COUNT DATA
            Statement st = con.createStatement();

            ResultSet rs1 = st.executeQuery("SELECT COUNT(*) FROM students");
            rs1.next();
            int totalStudents = rs1.getInt(1);

            ResultSet rs2 = st.executeQuery("SELECT COUNT(DISTINCT department) FROM students");
            rs2.next();
            int totalDept = rs2.getInt(1);

            ResultSet rs3 = st.executeQuery("SELECT COUNT(*) FROM students");
            rs3.next();
            int totalReg = rs3.getInt(1);

            // 🔥 HTML OUTPUT
            out.println("<html><head><style>");

            out.println("body{font-family:Poppins;background:linear-gradient(135deg,#071739,#0f3460,#1b3c73);color:white;text-align:center;}");
            out.println(".box{margin:20px auto;width:60%;padding:20px;background:rgba(255,255,255,0.1);border-radius:20px;}");
            out.println("h1{color:#38bdf8;}");

            out.println("</style></head><body>");

            out.println("<h1>📊 Live Analytics Data</h1>");

            out.println("<div class='box'>");
            out.println("<h2>Total Students: " + totalStudents + "</h2>");
            out.println("<h2>Total Departments: " + totalDept + "</h2>");
            out.println("<h2>Total Registrations: " + totalReg + "</h2>");
            out.println("</div>");

            out.println("</body></html>");

            con.close();

        } catch (Exception e) {
            out.println("<h3 style='color:red'>Error: " + e.getMessage() + "</h3>");
        }
    }
}