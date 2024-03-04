package pkg08_ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetData1 extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  String spec = "https://www.hankyung.com/feed/it.xml";
	  
	  URL url = new URL(spec);
	  
	  HttpURLConnection con = (HttpURLConnection)url.openConnection();
	  
	  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	  
	  StringBuilder builder = new StringBuilder();
	  
	  String line = null;
	  
	  while((line = in.readLine()) != null) {
	    builder.append(line);
	  }
	  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
