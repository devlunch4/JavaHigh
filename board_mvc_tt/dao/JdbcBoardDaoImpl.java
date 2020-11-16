package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.DBUtil;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() { 
		
	}
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null) {
			dao = new JdbcBoardDaoImpl();
		}
		return dao;
	}
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 사용한 자원을 반납하는 메서드
	public void disConnect(){
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	

	@Override
	public int insertBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into jdbc_board (board_no, board_title,"
					+ " board_writer, board_date, board_cnt, board_content) "
					+ " values (board_seq.nextVal, ?, ?, sysdate, 0, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jBoardVo.getBoard_title());
			pstmt.setString(2, jBoardVo.getBoard_writer());
			pstmt.setString(3, jBoardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally{
			disConnect();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set "
					+ "board_title = ? ,"
					+ "board_date = sysdate "
					+ "board_content = ? "
					+ "where board_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, jBoardVo.getBoard_title());
			pstmt.setString(2, jBoardVo.getBoard_content());
			pstmt.setInt(3, jBoardVo.getBoard_no());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally{
			disConnect();
		}
		return cnt;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO jBoardVo = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "select board_no, board_title, board_writer, "
					+ "to_char(board_date, 'YYYY-MM-DD') board_date, board_cnt, board_content from jdbc_board "
					+ "where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()){
				jBoardVo = new JdbcBoardVO();
				jBoardVo.setBoard_no(rs.getInt("board_no"));
				jBoardVo.setBoard_title(rs.getString("board_title"));
				jBoardVo.setBoard_writer(rs.getString("board_writer"));
				jBoardVo.setBoard_date(rs.getString("board_date"));
				jBoardVo.setBoard_cnt(rs.getInt("board_cnt"));
				jBoardVo.setBoard_content(rs.getString("board_content"));
			}
			
			
		} catch (SQLException e) {
			jBoardVo = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return jBoardVo;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		ArrayList<JdbcBoardVO> boardList = new ArrayList<JdbcBoardVO>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select board_no, board_title, board_writer, "
					+ " to_char(board_date, 'YYYY-MM-DD') board_date, board_cnt, board_content "
					+ " from jdbc_board "
					+ " order by board_no desc";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			// 반복문 안에서는 가져온 레코드 하나 하나를
			// VO에 맵핑하고 이 VO를 List에 추가한다.
			while(rs.next()){
				JdbcBoardVO jBoardVo = new JdbcBoardVO();
				jBoardVo.setBoard_no(rs.getInt("board_no"));
				jBoardVo.setBoard_title(rs.getString("board_title"));
				jBoardVo.setBoard_writer(rs.getString("board_writer"));
				jBoardVo.setBoard_date(rs.getString("board_date"));
				jBoardVo.setBoard_cnt(rs.getInt("board_cnt"));
				jBoardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add( jBoardVo );
			}
			
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String jBoardTitle) {
		ArrayList<JdbcBoardVO> boardList = new ArrayList<JdbcBoardVO>();
		if(jBoardTitle==null) {
			jBoardTitle = "";
		}
		try {
			conn = DBUtil.getConnection();
			String sql = "select board_no, board_title, board_writer, "
					+ "to_char(board_date, 'YYYY-MM-DD') board_date, board_cnt, board_content from jdbc_board "
					+ " where board_title like '%' || ? || '%' "
					+  "order by board_no desc ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jBoardTitle);
			
			rs = pstmt.executeQuery();
			
			// 반복문 안에서는 가져온 레코드 하나 하나를
			// VO에 맵핑하고 이 VO를 List에 추가한다.
			while(rs.next()){
				JdbcBoardVO jBoardVo = new JdbcBoardVO();
				jBoardVo.setBoard_no(rs.getInt("board_no"));
				jBoardVo.setBoard_title(rs.getString("board_title"));
				jBoardVo.setBoard_writer(rs.getString("board_writer"));
				jBoardVo.setBoard_date(rs.getString("board_date"));
				jBoardVo.setBoard_cnt(rs.getInt("board_cnt"));
				jBoardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add( jBoardVo );
			}
			
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set "
					+ "board_cnt = board_cnt + 1 "
					+ "where board_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally{
			disConnect();
		}
		return cnt;
	}

}
