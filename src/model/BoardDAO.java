package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class BoardDAO {

	
	
	//1. 모두조회
	public List<BoardVO> selectBoardList() {  //괄호 안에 아무것도 없다.. 조건없이 전부 뿌려라....
		List<BoardVO> blist = new ArrayList<BoardVO>();
		Connection conn= null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from board order by 1";
		
		conn= DBUtil.getConnection();
		try {
			st=conn.prepareStatement(sql);
			rs= st.executeQuery();
			while(rs.next()) {
				//BoardVO board = new BoardVO();   
				BoardVO board = makeBoard(rs);
				blist.add(board);
				
				//0, sql, sql, 0, null, 0, sql, sql  이런 값이 다들어가면 어려우니 빈걸로 일단만든다
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return blist;
		
	}
	

	private BoardVO makeBoard(ResultSet rs) throws SQLException {
		//리졀트 셋에서 읽어서 vo객체를 만든다. 
		//
		BoardVO board = new BoardVO();
		board.setBoard_contents(rs.getString("Board_contents")); //익센셥 처리 던지면 위 와일문에섷 ㅐ결
		board.setBoard_date(rs.getDate("Board_date"));
		board.setBoard_image(rs.getString("Board_image"));
		board.setBoard_password(rs.getString("Board_password"));
		board.setBoarde_seq(rs.getLong("Boarde_seq"));
		board.setBoard_title(rs.getString("Board_title"));
		board.setBoard_viewcount(rs.getInt("Board_viewcount"));
		board.setBoard_writer(rs.getInt("Board_writer"));
		
		return board;
	}
	
	
	
	
	//2. 상세보기 (board번호로 상세보기)
	public BoardVO selectByNo(int boardNo) {
		BoardVO board = null;
		Connection conn= null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from board where Boarde_seq = ?";
		String sql2 = "update board set Board_viewcount= nvl(Board_viewcount,0)+1 where Boarde_seq=?";
		                                       
		conn= DBUtil.getConnection();
		try {
			st=conn.prepareStatement(sql);
			st.setInt(1, boardNo);
			rs= st.executeQuery();
			while(rs.next()) {
				//BoardVO board = new BoardVO();   
				board = makeBoard(rs);
				st = conn.prepareStatement(sql2);
				st.setInt(1, boardNo);
				int result = st.executeUpdate();
				System.out.println(result>0?"Board_viewcount 수정성공":"Board_viewcount 수정실패");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return board;
		
		
		
	}
	
	
	
	//3. 입력
	
	public int boardInsert(BoardVO board) {
		int result = 0;
		Connection conn= null;
		PreparedStatement st = null;
		
		String sql = "insert into board values(board_autonum.nextval,?,?,?,sysdate,0,?,?)";
		//sql에서 시퀀스 생성 create sequence board_autonum;
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, board.getBoard_title());
			st.setString(2, board.getBoard_contents());
			st.setInt(3, board.getBoard_writer());
			st.setString(4, board.getBoard_password());
			st.setString(5, board.getBoard_image());			
			result = st.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		
		return result;
		
		
	}
	
	
	//4. 수정
	
	public int boardUpdate(BoardVO board) {

	int result = 0;
	Connection conn= null;
	PreparedStatement st = null;
	
	String sql = 
	" update board" +
	" set board_title=?, board_contents=?, board_date=sysdate, board_password=?, board_image=?"+		
	" where boarde_seq = ?";
	conn = DBUtil.getConnection();
	try {
		st=conn.prepareStatement(sql);
		st.setString(1, board.getBoard_title());
		st.setString(2, board.getBoard_contents());
		st.setString(3, board.getBoard_password());
		st.setString(4, board.getBoard_image());
		st.setLong(5, board.getBoarde_seq());
		result=st.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.dbClose(null, st, conn);
	}
	return result;
	}
	
	//5. 삭제
	public int boardDelete(int boardNo, String passwd) {

		int result = 0;
		Connection conn= null;
		PreparedStatement st = null;
		BoardVO board = selectByNo(boardNo);
		String sql = "delete from board where boarde_seq = ?";
		conn = DBUtil.getConnection();
		try {
			if(!board.getBoard_password().equals(passwd)) throw new SQLException("비밀번호오류~삭제불가");
			st=conn.prepareStatement(sql);
			st.setLong(1, boardNo);
			result=st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		return result;
		}
	
	
	
	
	
	
	
	
	
	
	

	
	
}

















/*
	//board insert
	
			
	public int insertBoard(BoardVO brd) {
		//String seqno = "select board_seqno.nextval from dual";
		String sql="insert into Board values(board_seqno.nextval,?,?,?,SYSDATE,1,?,?)";
		
		Connection conn;
		PreparedStatement st = null;
	
		int result = 0;
		
		conn = DBUtil.getConnection();
	
	
		try {
			st = conn.prepareStatement(sql);
			
			//st.setInt(1, brd.getBoard_seq());
			st.setString(1, brd.getBoard_contents());
			st.setString(2, brd.getBoard_title());
			st.setInt(3, brd.getBoard_writer());
			//st.setDate(5, brd.getBoard_date());
			//st.setInt(6, brd.getBoard_viewcount() );
			st.setString(4, brd.getBoard_password());
		    st.setString(5, brd.getBoard_image()); 
		         
			
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	//board select
	//public int selectBoard(EmpVO emp) {}
	
	
	//board update
	//public int updateBoard(EmpVO emp) {}
	
	//board delete
	//public int deleteBoard(EmpVO emp) {}
	
	//board id로 select.....viewcount가 증가


*/