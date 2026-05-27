package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AppointmentServlet")

public class AppointmentServlet extends HttpServlet{

protected void doPost(

HttpServletRequest request,

HttpServletResponse response

)

throws IOException{

response.setContentType(
"text/html"
);

PrintWriter out=
response.getWriter();


String name=

request.getParameter(
"name"
);

String email=

request.getParameter(
"email"
);

String department=

request.getParameter(
"department"
);

String advisor=

request.getParameter(
"advisor"
);

String date=

request.getParameter(
"date"
);

String time=

request.getParameter(
"time"
);

String reason=

request.getParameter(
"reason"
);

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

"insert into appointments(student_name,email,department,advisor,appointment_date,appointment_time,reason) values(?,?,?,?,?,?,?)"

);

ps.setString(
1,
name
);

ps.setString(
2,
email
);

ps.setString(
3,
department
);

ps.setString(
4,
advisor
);

ps.setString(
5,
date
);

ps.setString(
6,
time
);

ps.setString(
7,
reason
);


int i=
ps.executeUpdate();

if(i>0){

out.println(

"<html>"

+"<body style='background:#0f3460;color:white;text-align:center;padding-top:100px;font-family:Poppins'>"

+"<h1>✅ Appointment Booked Successfully</h1>"

+"<br>"

+"<h2>Advisor : "+advisor+"</h2>"

+"<h3>Date : "+date+"</h3>"

+"<h3>Time : "+time+"</h3>"

+"<br>"

+"<a href='dashboard.html' style='padding:15px 30px;background:#4da3ff;color:white;text-decoration:none;border-radius:30px;'>Go Dashboard</a>"

+"</body>"

+"</html>"

);

}
else{

out.println(

"<h2>Booking Failed</h2>"

);

}

con.close();

}

catch(Exception e){

out.println(e);

}

}

}