package board.service;

import java.util.List;

import board.dao.IJdbcBoardDao;
import board.dao.JdbcBoardDaoImpl;
import board.vo.JdbcBoardVO;



public class JdbcBoardServiceImpl implements IJdbcBoardService {
	private IJdbcBoardDao dao;
	private static JdbcBoardServiceImpl service;

	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDaoImpl.getInstance();
	}

	public static JdbcBoardServiceImpl getInstance() {
		if (service == null) {
			service = new JdbcBoardServiceImpl();
		}
		return service;
	}

	@Override
	public int insertBoard(JdbcBoardVO jBoardVo) {
		return dao.insertBoard(jBoardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(JdbcBoardVO jBoardVo) {
		return dao.updateBoard(jBoardVo);
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		// 조회수를 1 증가시킨다.
		if (setCountIncrement(boardNo) == 0) {
			return null;
		}
		return dao.getBoard(boardNo);
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		return dao.getAllBoardList();
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String jBoardTitle) {
		return dao.getSearchBoardList(jBoardTitle);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}
}
