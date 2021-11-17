import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Helloworld extends HttpServlet {
	
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	String loginIp = request.getRemoteAddr();
	out.println("<h1>hello world," + loginIp + "<h1>");	

   }
}
