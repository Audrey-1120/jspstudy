package pkg03_response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyResponse extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  response.setContentType("text/html; charset=UTF-8");
	  
	  PrintWriter out = response.getWriter();
	  
	  out.println("<!DOCTYPE html>");
    out.println("<html lang=\"ko\">"); 
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>Insert title here</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>안녕하세요</h1>");
    out.println("</body>");
    out.println("</html>"); 
    out.flush();
    out.close();
	  
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
