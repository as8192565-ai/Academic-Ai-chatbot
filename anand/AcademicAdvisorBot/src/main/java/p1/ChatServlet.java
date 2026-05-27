package p1;

import java.io.*;
import java.net.URI;
import java.net.http.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ChatServlet")

public class ChatServlet
extends HttpServlet{

protected void doPost(

HttpServletRequest request,

HttpServletResponse response

)

throws IOException{

response.setContentType(
"text/plain"
);

PrintWriter out=
response.getWriter();

String message=
request.getParameter(
"message"
);

String apiKey=
System.getenv(
"OPENAI_API_KEY"
);

if(apiKey==null){

out.print(
"API key not configured"
);

return;

}

try{

String body=

"{"

+"\"model\":\"gpt-4.1-mini\","

+"\"messages\":["

+"{"

+"\"role\":\"system\","

+"\"content\":\"You are an Academic Advisor AI.\""

+"},"

+"{"

+"\"role\":\"user\","

+"\"content\":\""+message+"\""

+"}"

+"]"

+"}";


HttpRequest req=

HttpRequest.newBuilder()

.uri(

URI.create(

"https://api.openai.com/v1/chat/completions"

)

)

.header(
"Content-Type",
"application/json"
)

.header(
"Authorization",
"Bearer "+apiKey
)

.POST(

HttpRequest.BodyPublishers.ofString(body)

)

.build();


HttpClient client=
HttpClient.newHttpClient();

HttpResponse<String> res=

client.send(

req,

HttpResponse.BodyHandlers.ofString()

);

out.print(
res.body()
);

}

catch(Exception e){

out.print(
"Error: "
+e.getMessage()
);

}

}

}