package p1;

import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet{

protected void doPost(

HttpServletRequest request,
HttpServletResponse response)

throws ServletException,IOException{

response.setContentType("text/html");

PrintWriter out=response.getWriter();

String department=
request.getParameter("department");

String password=
request.getParameter("password");

try{

Class.forName(
"com.mysql.cj.jdbc.Driver"
);

Connection con=
DriverManager.getConnection(

"jdbc:mysql://localhost:3306/academicbot",

"root",

"Himanshu#9546"

);

PreparedStatement ps=
con.prepareStatement(

"select * from students where department=? and password=?"

);

ps.setString(1,department);

ps.setString(2,password);

ResultSet rs=
ps.executeQuery();

if(rs.next()){

HttpSession session=
request.getSession();

session.setAttribute(
"name",
rs.getString("name")
);

response.sendRedirect(
"dashboard.html"
);

}

else{

out.println(

"<h2 style='color:red;text-align:center'>Invalid Department or Password</h2>"

);

}

con.close();

}

catch(Exception e){

out.println(e);

}

}

}