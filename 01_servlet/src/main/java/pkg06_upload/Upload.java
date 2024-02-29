package pkg06_upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
                 maxFileSize = 1024 * 1024 * 5,
                 maxRequestSize = 1024 * 1024 * 50)

public class Upload extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 업로드 경로 (톰캣 내부 경로)
    String uploadPath = request.getServletContext().getRealPath("upload");
    File uploadDir = new File(uploadPath);
    
    // 디렉토리가 없으면 새로 만든다!
    if(!uploadDir.exists()) {
      uploadDir.mkdirs();
    }
    
    // 원본 파일 이름
    String originalFilename = null;
    
    // 저장된 파일 이름
    String filesystemName = null;


    // 첨부된 파일 정보
    Collection<Part> parts = request.getParts();
    for(Part part : parts) {
      // System.out.println(part.getName() + "," + part.getContentType() + "," + part.getSize() + "," + part.getSubmittedFileName());
      // System.out.println(part.getHeader("Content-Disposition"));
      if(part.getHeader("Content-Disposition").contains("filename")) {
        // 각 파트의 헤더 중 Content-Disposition 헤더에서 filename을 포함하는 경우 처리
        // Content-Disposition : 컨텐츠의 전송 형식을 나타내는 헤더.
        // 파일 업로드 파트인지 확인하는 부분. -> 만약 포함되어 있으면 파일 업로드 파트로 간주
        if(part.getSize() > 0) {
          // 업로드된 파일의 크기가 0보다 큰 경우에만 원본 파일 이름 가져옴.
          originalFilename = part.getSubmittedFileName(); // 클라이언트가 제출한 원본 파일 이름을 가져옴.
        }
      }
      if(originalFilename != null) {
        int point = originalFilename.lastIndexOf(".");
        String extName = originalFilename.substring(point);      // .jpg
        String fileName = originalFilename.substring(0, point);  // anmal1
        filesystemName = fileName + "_" + System.currentTimeMillis() + extName;
        // 파일 시스템에 저장될 파일 이름 생성.
      }
      if(filesystemName != null) {
        part.write(uploadPath + File.separator + filesystemName);
        // 실제로 파일을 업로드 디렉토리에 저장함.
        // part.write() 메소드를 사용하여 지정된 경로에 파일 저장.
        // uploadPath는 파일이 저장될 디렉토리, filesystemName 은 저장될 파일 이름.
      }
    }
    
    // 응답
    response.setContentType("text/html; charset=UTF-8");
    // 응답의 콘텐츠 타입 설정함. HTML, 문자 인코딩해줌.
    PrintWriter out = response.getWriter();
    // 클라이언트에게 응답을 보낼 PrintWriter 객체 가져옴.
    out.println("<div><a href=\"/servlet/pkg06_upload/NewFile.html\">입력폼으로 돌아가기</a></div>");
    out.println("<hr>");
    out.println("<div>첨부파일명 : " + originalFilename + "</div>");
    out.println("<div>저장파일명 : " + filesystemName + "</div>");
    out.println("<div>저장경로 : " + uploadPath + "</div>");
    out.println("<hr>");
    
    File[] files = uploadDir.listFiles();
    // 업로드 된 디렉터리 내의 파일 목록 가져오기
    for(File file : files) { // 각 파일에 대해 반복문 실행
      String fileName1 = file.getName();  // 파일명_1234567890.jpg
      String ext = fileName1.substring(fileName1.lastIndexOf("."));
      String fileName2 = fileName1.substring(0, fileName1.lastIndexOf("_"));
      out.println("<div><a href=\"/servlet/download?filename=" + URLEncoder.encode(fileName1, "UTF-8") + "\">" + fileName2 + ext + "</a></div>");
      // 각 파일에 대한 다운로드 링크 추출.
      // fileName1을 URLEncoder를 사용해서 UTF-8로 인코딩함.
    }
    
    out.flush();
    out.close();
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}