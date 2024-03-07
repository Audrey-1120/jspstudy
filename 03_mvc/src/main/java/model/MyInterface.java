package model;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public interface MyInterface {
   
  // 날짜요청이 오면 날짜를 만들어서 문자열로 반환하기
  // 모델이 반환하는 대상은 컨트롤러이다.
  // 뷰에서 넘겨준 요청들(파라미터 등)을 다시 서비스로 넘겨주어야 함. - request 자체를 그냥 넘겨준다.
  ActionForward getDate(HttpServletRequest request);
  ActionForward getTime(HttpServletRequest request);
  ActionForward getDateTime(HttpServletRequest request);
  
}
