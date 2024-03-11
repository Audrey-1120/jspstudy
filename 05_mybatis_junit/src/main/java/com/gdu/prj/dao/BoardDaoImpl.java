package com.gdu.prj.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gdu.prj.dto.BoardDto;

public class BoardDaoImpl implements BoardDao {
  
  // SQLSession (Connection/{PreparedStatement/ResultSet 처리) 만드는 sqlSessionFactory
  private SqlSessionFactory factory = null;
  
  // SingletonPattern
  private static BoardDao boardDao = new BoardDaoImpl();
  private BoardDaoImpl() {
    
    // mybatis-config.xml 파일을 이용한 sqlSessionFactory 객체 생성
    try {
      String resource = "com/gdu/prj/config/mybatis-config.xml";
      InputStream in = Resources.getResourceAsStream(resource);
      factory = new SqlSessionFactoryBuilder().build(in); 
      // 입력 스트림을 기반(mybatis-config)으로 factory를 짓는다.
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  public static BoardDao getInstance() {
    return boardDao;
  }
  
  
  @Override
  public int insertBoard(BoardDto board) {
    SqlSession sqlSession = factory.openSession(false); // autoCommit을 하지 않는다.
    int insertCount = sqlSession.insert("com.gdu.prj.dao.board_t.insertBoard", board);
    if(insertCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return insertCount;
  }

  @Override
  public int updateBoard(BoardDto board) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int deleteBoard(int board_no) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int deleteBoards(String param) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<BoardDto> selectBoardList(Map<String, Object> params) {
    SqlSession sqlSession = factory.openSession();
    List<BoardDto> boardList = sqlSession.selectList("com.gdu.prj.dao.board_t.selectBoardList", params);
    sqlSession.close();
    return boardList;
  }

  @Override
  public int getBoardCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public BoardDto selectBoardByNo(int board_no) {

    return null;
  }

  @Override
  public void close() {
    // TODO Auto-generated method stub

  }

}
