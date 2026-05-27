package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/EnrollServlet")

public class EnrollServlet extends HttpServlet{

protected void doGet(

HttpServletRequest request,

HttpServletResponse response

)

throws IOException{

response.setContentType(
"text/html"
);

PrintWriter out=
response.getWriter();

String course=

request.getParameter(
"course"
);


// Temporary student name

String studentName=
"Himanshu Kumar";


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

"insert into enrolled_courses(student_name,course_name) values(?,?)"

);

ps.setString(
1,
studentName
);

ps.setString(
2,
course
);

int i=
ps.executeUpdate();

if(i>0){

out.println(

"<html>"

+"<body style='background:#0f3460;color:white;text-align:center;padding-top:100px;font-family:Arial'>"

+"<h1>✅ Course Enrolled Successfully</h1>"

+"<br>"

+"<h2>Course: "

+course+

"</h2>"

+"<br>"

+"<a href='courses.html' style='background:#4da3ff;padding:15px 30px;border-radius:30px;color:white;text-decoration:none;'>Back</a>"

+"</body>"

+"</html>"

);

}

else{

out.println(

"<h2>Enrollment Failed</h2>"

);

}

con.close();

}

catch(Exception e){

out.println(e);

}

}

}