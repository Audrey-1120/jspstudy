package pkg06_upload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Download extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // download 쪽으로 전달되는 파라미터 이름  : fileName
	  request.setCharacterEncoding("UTF-8");
	  String filename = request.getParameter("filename");
	  
	  String uploadPath = request.getServletContext().getRealPath("upload");
	  
	  File file = new File(uploadPath, filename);
	  System.out.println(file.exists()); // 이게 true 가 나오면 해당 파일이 있음.
	  
	  // 원본 파일(서버) 입력 스트림 생성
	  // - 성능 향상을 위해 Buffer
	  BufferedInputStream in = new BufferedInputStream(new FileInputStream(file)); // 파일 객체는 위에서 이미 만듬...
	  
	  // 다운로드용 응답 헤더("Content-Disposition: attachment;")
	  // - attachment 값은 컨텐츠를 다운로드 할 수 있게 해줌.
	  response.setHeader("Content-Disposition", "attachment");   // 다운로드 대화상자 나옴.
	  response.setHeader("Content-Disposition", "attachment; filename=" + filename);   // 다운로드 대화상자 없이 지정한 filenam으로 곧바로 진행
	  // - filename을 명시해두면 이 이름으로 곧바로 다운로드 되어서 대화상자 안나옴.
	  // - timestamp가 섞여있는 이름으로 다운로드가 가능하도록 함.
	  
	  
	  // 복사 파일(클라이언트) 출력 스트림 - 복사 파일로 내보내야 하기 때문..
	  // - 클라이언트 측의 요청이 움직이는 통로는 response(응답)를 통해서 알아내야 함.
	  BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream()); 
	  
	  // 복사 (서버 -> 클라이언트)
	  byte[] b = new byte[1024];
	  int readByte = 0;
	  while((readByte = in.read(b)) != -1) {
	    out.write(b, 0, readByte);
	  }
	  
	  // 스트림 닫기
	  out.close();
	  in.close();
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
