package member.vo;

public class MemberVO {

	// VO는 테이블에 있는 컬럼을 기준으로 데이터를 객체화할 클래스이다.
	// DB 테이블의 컬럼영이 이 VO클래스의 멤버 번수명이 된다.

	// DB테이블과 컬럼과 클래스의 멤버변수를 매필하는 역할을 수애한다.

	private String mem_id;
	private String mem_name;
	private String mem_tel;
	private String mem_addr;

	
	/////////////////////
	
	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMem_addr() {
		return mem_addr;
	}

	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}

	// 만약 기본 생성

}
