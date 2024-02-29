package pkg07_Cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class SaveCookie extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	  // 요청 및 요청 파라미터
	  request.setCharacterEncoding("UTF-8");
	  // - 아무 입력도 안하고 보내면? 빈 문자열 ""
	  String name = request.getParameter("name");
	  String email = request.getParameter("email");
	  
	  // 쿠키 만들기(쿠키 이름, 유지 시간, 저장 경로)
	  // - 쿠키는 클라이언트의 각 브라우저에 저장되어 있음.
	  // - 쿠키를 저장하는 건? 응답이다. 서버가 클라이언트에게 보내야 함.
	  
	  // 쿠키 이름과 값
	  Cookie cookie1 = new Cookie("name", URLEncoder.encode(name, "UTF-8"));     // 쿠키 값은 String 타입이다. 공백 등 쿠키 값으로 사용할 수 없는 문자가 있어서 인코딩 후 저장한다.
	  Cookie cookie2 = new Cookie("email", URLEncoder.encode(email, "UTF-8"));
    
	  // 쿠키 유지 시간
	  // - setMaxAge : 단위는 초임. 60 * 60은 1분, 60 * 60 * 60은 한시간....
	  cookie1.setMaxAge(60 * 60 * 24 * 15);  // 15일 
	  // cookie2.setMaxAge(0);               // 유지 시간을 생략하면 세션 쿠키가 된다. (브라우저를 닫으면 지워지는 쿠키.)
	  
	  // 쿠키 저장 경로 (생략하면 contextPath 가 경로로 사용된다.)
	  // - 이 쿠키값을 어디서 확인할 수 있나? 주소에 따라서 달라짐.
	  cookie1.setPath("/servlet");             // contextPath, cookie1.setPath(request.getContextPath())
	  cookie2.setPath("/servlet/saveCookie");  // URLMapping,    cookie2.setPath(request.getRequestURI())
	  
	  // 쿠키 저장(응답)
	  response.addCookie(cookie1);
	  response.addCookie(cookie2);
	  
	  // ReadCookie 서블릿으로 이동하기.
	  
	  // contextPath를 변수 처리하는 것은 정말 중요하다.
	  response.sendRedirect(request.getContextPath() + "/readCookie");
    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
