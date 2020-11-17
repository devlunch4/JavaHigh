package dao;

import java.util.List;

import vo.JdbcBoardVO;

public interface IJdbcBoardDao {
	/**
	 * JdbcBoardVO로 정보를 받아. insert
	 * 
	 * @param jBoardVo 정보 저장된 클래스?객체?
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertBoard(JdbcBoardVO jBoardVo);

	/**
	 * 게시글 번호를 받아서 삭제
	 * 
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteBoard(int boardNo);

	/**
	 * JdbcBoardVO 자료로 수정
	 * 
	 * @param jBoardVo update할 정보가 담김.
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateBoard(JdbcBoardVO jBoardVo);

	/**
	 * 게시글 번호를 입력받아 가져오기
	 * 
	 * @param boardNo 가져올 게시글 번호
	 * @return 게시글번호에 맞는 자료가 있으면 리턴
	 */
	public JdbcBoardVO getBoard(int boardNo);

	/**
	 * DB의 jdbc_board테이블 전체 받아서 리스트 넣기
	 * 
	 * @return JdbcBoardVO객체를 담고 있는 List객체
	 * 
	 */
	public List<JdbcBoardVO> getAllBoardList();

	/**
	 * 게시글의 제목으로 검색
	 * 
	 * @param jBoardTitle 제목
	 * @return 결과값
	 */
	public List<JdbcBoardVO> getSearchBoardList(String jBoardTitle);

	/**
	 * 게시글 번호를 받아서 조회하여 조회수 증가
	 * 
	 * @param boardNo 조회수 증가 게시글 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int setCountIncrement(int boardNo);
}
