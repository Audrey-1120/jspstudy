package text01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class Login extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  response.setContentType("text/html; charset=UTF-8");
	  
	  PrintWriter out = response.getWriter();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  request.setCharacterEncoding("UTF-8");
	  
	  String id = request.getParameter("id");
	  String password = request.getParameter("password");
	  String passwordCheck = request.getParameter("passwordCheck");
	  String name = request.getParameter("name");
	  String gender = request.getParameter("gender");
	  String checkEmail = request.getParameter("checkEmail");
	  String secureNum = request.getParameter("secureNum");
	  
	  String[] birth = request.getParameterValues("birth");
	  String[] mobile = request.getParameterValues("mobile");
	  
	  System.out.println(id);
	  System.out.println(password);
	  System.out.println(passwordCheck);
	  System.out.println(name);
	  System.out.println(gender);
	  System.out.println(checkEmail);
	  System.out.println(secureNum);
	  System.out.println(Arrays.toString(birth));
	  System.out.println(Arrays.toString(mobile));
	  
	  
	  response.setContentType("text/html; charset=UTF-8");
	  
	  PrintWriter out = response.getWriter();
	  
    out.println("<!DOCTYPE html>");
    out.println("<html lang=\"ko\">");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>Insert title here</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<ul>");
    out.println("<li>아이디: " + id+ "</id>");
    out.println("<li>비밀번호: " + password+ "</id>");
    out.println("<li>이름: " + name+ "</id>");
    out.println("<li>생년월일: " + birth[0] + "년" + birth[1] + "월" + birth[2] + "일</li>");
    out.println("<li>성별: " + gender+ "</id>");
    out.println("<li>휴대전화: " + mobile[0] + " " + mobile[1] + "</id>");
    out.println("</ul>");
    out.println("</body>");
    out.println("</html>");
	  
    out.flush();
    out.close();
	  
	  
    
	      
	     
	  
	  
	  
	}

}
