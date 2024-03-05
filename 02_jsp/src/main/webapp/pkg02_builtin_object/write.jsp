<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <% String contextPath = request.getContextPath(); %>
  <!-- 위의 결과는 /jsp 이다. contextPath를 불러들이는 메소드이기 때문에 -->
  <!-- 이때 request는? 내장객체이기 때문에 그냥 쓰면 된다! -->
  

  <form method="POST"
        action="<%=contextPath %>/pkg02_builtin_object/save.jsp"> <!-- 여기서 위의 contextPath가 /jsp를 대체할 수 있다! -->
    <div>
      <label for="created_at">작성일자</label>
      <input type="text" id="created-at" name="created-at" value="<%=LocalDate.now()%>" readonly> 
    </div>
    <div>
      <label for="title">제목</label>
      <input type="text" id="title" name="title">
    </div>
    <div>
      <textarea rows="5" cols="50" name="contents" placeholder="내용"></textarea>
    </div>
    <div>
      <button type="submit">작성완료</button>
      <button type="reset">다시작성</button>
    </div>
  
  
  </form>

</body>
</html>