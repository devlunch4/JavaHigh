package member.service_teach;

import java.util.List;


import member.dao_teach.IMemberDao_teach;
import member.dao_teach.MemberDaoImpl_teach;
import member.vo_teach.MemberVO_teach;

public class MemberServiceImpl_teach implements IMemberService_teach {
	private IMemberDao_teach dao;   // DAO객체가 저장될 변수 선언
	private static MemberServiceImpl_teach service;  // 1번
	
	// 생성자 - 2번
//	public MemberServiceImpl() {
	private MemberServiceImpl_teach() {
		//dao = new MemberDaoImpl();
		dao = MemberDaoImpl_teach.getInstance();
	}
	
	// 3번
	public static MemberServiceImpl_teach getInstance() {
		if(service==null) service = new MemberServiceImpl_teach();
		return service;
	}

	@Override
	public int insertMember(MemberVO_teach memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO_teach memVo) {
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO_teach> getAllMemberList() {
		return dao.getAllMemberList();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}

}
