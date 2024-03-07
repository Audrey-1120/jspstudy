package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public class MyInterfaceImpl implements MyInterface {
  
  // 실제 내용 구현은 이곳에서

  @Override
  public ActionForward getDate(HttpServletRequest request) {
    request.setAttribute("date", DateTimeFormatter.ofPattern("yyyy. MM. dd.").format(LocalDate.now()));
    return new ActionForward("/view/date.jsp", false);
  }
  // getDate가 날짜를 반환할 필요 없다. getDate의 날짜 결과를 보여줄 view의 이름을 말함(반환함)
  // 나는 내 결과를 @@jsp에서 보여줄겅요 형태.
  // 화면에 보여지는 날짜, 시간, 정보는? 
  // -> jsp 가 정보를 가지고 이동할 수 있게끔 forward 사용할 것임.
  // -> 날짜 및 시간은 request 객체에 저장할 것임. 데이터 자체는 request에 저장.
  // -> @jsp로 가자! forward 시킴. forward는 요청을 그대로 들고 다음 목적지로 이동한다.
  

  @Override
  public ActionForward getTime(HttpServletRequest request) {
    request.setAttribute("time", DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalTime.now()));
    return new ActionForward("/view/time.jsp", false);
  }

  @Override
  public ActionForward getDateTime(HttpServletRequest request) {
    request.setAttribute("datetime", DateTimeFormatter.ofPattern("yyyy. MM. ss. HH:mm:ss.SSS").format(LocalDateTime.now()));
    return new ActionForward("/view/datetime.jsp", false);
  }

}
