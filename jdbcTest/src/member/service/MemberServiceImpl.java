package member.service;

import java.util.List;

import member.dao.IMemberDao;
import member.dao.MemberDaoImpl;
import member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao dao;

	// 생성자
	public MemberServiceImpl() {
		// 싱글톤으로 변경시 오류발생
		dao = new MemberDaoImpl();

		// 싱글톤 사용시 아래 메소드로 dao 호출
		// dao = MemberDaoImpl_ex_singleton.getInstance();
	}

	@Override
	public int insertMember(MemberVO memVO) {
		// TODO Auto-generated method stub
		return dao.insertMember(memVO);
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		// TODO Auto-generated method stub
		return dao.getAllMemberList();
	}

	@Override
	public int getMemberCount(String memId) {
		// TODO Auto-generated method stub
		return dao.getMemberCount(memId);
	}

}
