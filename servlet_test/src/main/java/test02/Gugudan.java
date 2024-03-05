package test02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class Gugudan extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // String a = request.getParameter("a");
	  int a = Integer.parseInt(request.getParameter("a"));
	  int b = Integer.parseInt(request.getParameter("b"));
	  int answer = Integer.parseInt(request.getParameter("answer"));
	  
	  response.setContentType("text/html; charset=UTF-8");
	  
	  PrintWriter out = response.getWriter();
	  
	  if(answer == a*b) {
	    out.println("<script>");
	    out.println("alert('정답입니다.')");
	    out.println("</script>");
	  } else {
	     out.println("<script>");
	      out.println("alert('오답입니다.')");
	      out.println("</script>");
	  }
	  
	  out.flush();
	  out.close();
	  

	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
