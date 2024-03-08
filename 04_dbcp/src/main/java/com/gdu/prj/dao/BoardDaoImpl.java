package com.gdu.prj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.gdu.prj.dto.BoardDto;

/*
 * view - controller - service - dao - db
 */

public class BoardDaoImpl implements BoardDao {
  
  // dao는 db 를 처리한다.
  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  
  // Connection Pool 관리를 위한 DataSource 객체 선언
  private DataSource dataSource;
  
  // SingletonPattern
  private static BoardDao boardDao = new BoardDaoImpl();
  private BoardDaoImpl(){
    // META-INF\context.xml 파일에 명시된 Resource 를 이용해 DataSource 객체를 생성하기
    try {
      Context context = new InitialContext();
      Context env = (Context)context.lookup("java:comp/env");
      dataSource = (DataSource)env.lookup("jdbc/myoracle");
    } catch (NamingException e) {
      System.out.println("관련 자원을 찾을 수 없습니다.");
    }
    
  }
  public static BoardDao getInstance() { // 만들어둔 boardDao 객체를 호출해서 사용할 수 있음!!
    return boardDao;
  }
  

  @Override
  public int insertBoard(BoardDto board) { // title, contents는 파라미터의 board 객체 안에 있음.
    int insertCount = 0;
    try {
      // dataSource는 connection pool 관리 객체임 - 커넥션 풀 좀 줘(요청)
      con = dataSource.getConnection();
      String sql = "INSERT INTO BOARD_T(BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, CURRENT_DATE, CURRENT_DATE)";
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle()); // 첫번째 물음표에 title 집어넣기
      ps.setString(2, board.getContents());
      insertCount = ps.executeUpdate(); // 실행하고 실행 결과는 insertCount. - DML 수행시에는 executeUpdate 사용.
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return insertCount;
  }

  @Override
  public int updateBoard(BoardDto board) {
    int updateCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "UPDATE BOARD_T SET TITLE = ?, CONTENTS = ?, MODIFIED_AT = CURRENT_DATE WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle());
      ps.setString(2, board.getContents());
      ps.setInt(3, board.getBoard_no());
      updateCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return updateCount;
  }

  @Override
  public int deleteBoard(int board_no) {
    int deleteCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "DELETE FROM BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      deleteCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return deleteCount;
  }

  @Override
  public List<BoardDto> selectBoardList(Map<String, Object> map) {
    List<BoardDto> boardList = new ArrayList<BoardDto>();
    try {
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT FROM BOARD_T ORDER BY BOARD_NO DESC";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery(); // SELECT의 실행쿼리는 executeQuery이다.
      while(rs.next()) { // rs.next()는 한줄.
        BoardDto board = BoardDto.builder()
                            .board_no(rs.getInt(1)) // 숫자는 select 문의 칼럼 순서!
                            .title(rs.getString(2))
                            .contents(rs.getString(3))
                            .modified_at(rs.getDate(4))
                            .created_at(rs.getDate(5))
                           .build();
        boardList.add(board);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return boardList;
  }

  @Override
  public int getBoardCount() {
    int boardCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "SELECT COUNT(*) FROM BOARD_T";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      if(rs.next()) {
        boardCount = rs.getInt(1);
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    return boardCount;
  }

  @Override
  public BoardDto selectBoardByNo(int board_no) {
    BoardDto board = null;
    try { 
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT FROM BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      rs = ps.executeQuery(); // 검색 결과는 0아니면 1임.
      if(rs.next()) { // 만약 존재한다면,
        board = BoardDto.builder()
                    .board_no(rs.getInt(1))
                    .title(rs.getString(2))
                    .contents(rs.getString(3))
                    .modified_at(rs.getDate(4))
                    .created_at(rs.getDate(5))
                   .build();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return board;
  }

  @Override
  public void close() {
    try {
      if(rs != null) rs.close();
      if(ps != null) ps.close();
      if(con != null) con.close();  // Connection 반납으로 동작
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
