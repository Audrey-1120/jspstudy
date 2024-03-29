package pkg08_ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class GetData2 extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath");
	  // 위의 주소는 ?뒤에 쿼리데이터를 작성해주어야 함. -> StringBuilder를 이용하자.
	  urlBuilder.append("?serviceKey=").append(URLEncoder.encode("8qXryYQzWCQYOX/XCfybXEZ5pFThPA3PmXZFQ9iPt1tTdL2EzbP7y32w46vBQntgd4WAEgl8U9dHeGSKa/Nvfw==", "UTF-8"));
	  urlBuilder.append("&searchYear=").append(URLEncoder.encode("2022", "UTF-8"));
	  urlBuilder.append("&siDo=").append(URLEncoder.encode("1100", "UTF-8"));
	  urlBuilder.append("&guGun=").append(URLEncoder.encode("1116", "UTF-8"));
	  urlBuilder.append("&type=").append(URLEncoder.encode("json", "UTF-8"));
	  urlBuilder.append("&numOfRows=").append(URLEncoder.encode("10", "UTF-8"));
	  urlBuilder.append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"));
	  
	  // 요청 URL
	  String spec = urlBuilder.toString();
	  
    // 응답 코드
    int responseCode = 0;
    
    try {
      
      // URL 객체
      URL url = new URL(spec);
      
      // HttpURLConnection 객체
      HttpURLConnection con = (HttpURLConnection)url.openConnection();
      
      // 응답 코드 확인
      responseCode = con.getResponseCode();
      if(responseCode != HttpURLConnection.HTTP_OK) {
        throw new RuntimeException("API 응답 실패");
      }
      
      // 문자 입력 스트림 (전국 사망사고 정보 읽어오기)
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      
      // StringBuilder 객체 (전체 JSON)
      StringBuilder builder = new StringBuilder();
      
      // String 객체 (한 줄)
      String line = null;
      
      // 문자 입력 스트림을 통해 전국 사망사고 정보 읽어 오기
      while((line = in.readLine()) != null) {
        builder.append(line);
      }
      
      // 응답 데이터 타입 & 인코딩
      response.setContentType("application/json; charset=UTF-8");
      
      // 응답
      PrintWriter out = response.getWriter();
      out.print(builder.toString());
      out.flush();
      out.close();
      
    } catch (Exception e) {
      // $.ajax().fail() 메소드로 전달되는 응답 만들기
      // 1) 응답 코드 만들기
      response.setStatus(responseCode);
      // 2) 응답 메시지 만들기
      response.setContentType("text/plain; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.print(e.getMessage());
      out.flush();
      out.close();
    }
  
  }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
