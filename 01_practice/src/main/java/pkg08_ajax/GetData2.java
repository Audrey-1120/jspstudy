package pkg08_ajax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetData2 extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath");
    urlBuilder.append("?serviceKey=").append(URLEncoder.encode("8qXryYQzWCQYOX/XCfybXEZ5pFThPA3PmXZFQ9iPt1tTdL2EzbP7y32w46vBQntgd4WAEgl8U9dHeGSKa/Nvfw==", "UTF-8"));
    urlBuilder.append("&searchYear=").append(URLEncoder.encode("2024", "UTF-8"));
    urlBuilder.append("&siDo=").append(URLEncoder.encode("1100", "UTF-8"));
    urlBuilder.append("&guGun=").append(URLEncoder.encode("1116", "UTF-8"));
    urlBuilder.append("&type=").append(URLEncoder.encode("json", "UTF-8"));
    urlBuilder.append("&numOfRows=").append(URLEncoder.encode("10", "UTF-8"));
    urlBuilder.append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"));
    
    String spec = urlBuilder.toString();
    
    try {
      
      URL url = new URL(spec);
      
      HttpURLConnection con = (HttpURLConnection)url.openConnection();
      
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      
      StringBuilder builder = new StringBuilder();
      
      String line = null;
      
      while((line = in.readLine()) != null) {
        builder.append(line);
      }
      
      con.disconnect();
      
      String responseBody = builder.toString();
      
      JSONObject obj = new JSONObject(responseBody);
      
      String resultCode = obj.getString("resultCode");
      String resultMsg = obj.getString("resultMsg");
      
      if(!resultCode.equals("00")) {
        throw new RuntimeException(resultMsg + "(" + resultCode + ")");
      }
      
      response.setContentType("application/json; charset=UTF-8");
      
      PrintWriter out = response.getWriter();
      out.println(builder.toString());
      out.flush();
      out.close();
      
      
    } catch (Exception e) {
      
      response.setContentType("text/plain; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.print(e.getMessage());
      out.flush();
      out.close();
    }
  
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}