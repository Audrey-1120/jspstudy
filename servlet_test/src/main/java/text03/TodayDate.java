package text03;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TodayDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  
	  int number = Integer.parseInt(request.getParameter("choice"));
	  
    response.setContentType("text/html; charset=UTF-8");
	    
	  PrintWriter out = response.getWriter();
	  
	  StringBuilder builder = new StringBuilder();
	  
	  if(number == 0) {
	    
	    out.println("<script>");
	    out.println(" var dt = new Date()");
	    out.println(" alert('요청결과는' + dt + '입니다.')");
	    
	    out.println("</script>");
	    
	  } else {
	     out.println("<script>");
	     out.println("alert('현재시간');");
	     out.println("</script>");
	  }
	  
	  out.flush();
	  out.close();
	  
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
