package member.service;

import java.util.List;
import java.util.Map;

import member.vo.MemberVO;

/**
 * Service객체는 DAO에 설정된 메서드를 원하는 작업에 맞게 호출하여
 * 결과를 받아오고, 받아온 결과 자료를 Controller에게 보내주는 역할을 한다.
 *
 * 보통 DAO의 메서드 구조와 구조가 같게 만든다.
 */
public interface IMemberService {
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert작업 성공 : 1, 실패 : 0
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원ID를 매개값으로 받아서 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId 삭제할 회원Id
	 * @return	삭제성공 : 1, 삭제실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * MemberVO에 담겨진 정보를 이용하여 회원 정보를 수정하는 메서드
	 * @param memVo 수정할 정보가 저장된 MemberVO객체
	 * @return 수정성공 : 1, 실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * DB의 회원테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> getAllMemberList();
	
	/**
	 * 회원ID를 매개값으로 받아서 해당 회원의 개수를 반환하는 메서드
	 * @param memId 검색할 회원 ID
	 * @return 검색된 회원ID 개수
	 */
	public int getMemberCount(String memId);
	
	/**
	 * Map의 정보를 이용하여 회원 정보들 중 원하는 컬럼을 수정하는 메서드
	 * 	Map의 key값 : 회원ID(memId), 변경할컬럼명(field),  변경할데이터(data)
	 * 
	 * @param paramMap 회원ID, 변경할컬럼, 변경할데이터가 저장된 Map객체
	 * @return  수정성공 : 1, 실패 : 0
	 */
	public int updateMember2(Map<String, String> paramMap);
}
