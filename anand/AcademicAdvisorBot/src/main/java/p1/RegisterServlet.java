package p1;

import java.io.*;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String department = request.getParameter("department");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        try {

            // Password check
            if (!password.equals(confirmPassword)) {
                out.println("<h3 style='color:red;text-align:center'>Password not match</h3>");
                return;
            }

            // DB connection
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/academicbot",
                    "root",
                    "Himanshu#9546"
            );

            // INSERT
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO students(name,email,department,password,confirmPassword) VALUES (?,?,?,?,?)"
            );

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, department);
            ps.setString(4, password);
            ps.setString(5, confirmPassword);

            ps.executeUpdate();

            ps.close();

            // FETCH LAST INSERTED DATA (by email)
            PreparedStatement ps2 = con.prepareStatement(
                    "SELECT * FROM students WHERE email=?"
            );

            ps2.setString(1, email);

            ResultSet rs = ps2.executeQuery();

            // HTML START
            out.println("<html><head><style>");

            out.println("body{font-family:Poppins;background:linear-gradient(135deg,#0f172a,#020617);color:white;padding:20px;}");
            out.println("h2{text-align:center;color:#38bdf8;}");
            out.println("table{width:60%;margin:auto;border-collapse:collapse;background:rgba(255,255,255,0.08);border-radius:10px;overflow:hidden;}");
            out.println("th,td{padding:15px;border:1px solid rgba(255,255,255,0.2);text-align:center;}");
            out.println("th{background:#1e3a8a;}");
            out.println("tr:hover{background:rgba(255,255,255,0.1);transform:scale(1.01);} ");
            out.println("</style></head><body>");

            out.println("<h2>🎉 Registration Successful</h2>");
            out.println("<h3 style='text-align:center'>Inserted Data</h3>");

            out.println("<table>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Department</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("department") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            out.println("</body></html>");

            rs.close();
            ps2.close();
            con.close();

        } catch (Exception e) {
            out.println("<h3 style='color:red'>Error: " + e.getMessage() + "</h3>");
        }
    }
}