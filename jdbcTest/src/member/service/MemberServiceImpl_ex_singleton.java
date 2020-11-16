package member.service;

import java.util.List;

import mebmer.dao.IMemberDao;
import mebmer.dao.MemberDaoImpl_ex_singleton;
import member.vo.MemberVO;

public class MemberServiceImpl_ex_singleton implements IMemberService {

	private IMemberDao dao;
	// 싱글톤 생성자 생성.
	// 1번
	private static MemberServiceImpl_ex_singleton single;
	
	
	//// 기존
	// // 생성자
	// public MemberServiceImpl_ex_singleton() {
	// // 싱글톤으로 변경시 오류발생
	// // dao = new MemberDaoImpl();
	
	// 2번
	private MemberServiceImpl_ex_singleton() {
		dao = MemberDaoImpl_ex_singleton.getInstance();
	}

	// 3번
	public static MemberServiceImpl_ex_singleton getInstance() {
		if (single == null) {
			single = new MemberServiceImpl_ex_singleton();
		}
		return single;
	}
	
	// 싱글톤 생성자 마감
	////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////

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
