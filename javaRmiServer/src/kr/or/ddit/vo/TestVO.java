package kr.or.ddit.vo;

import java.io.Serializable;

// RMI 통신에서 데이터 전달용으로 사용할 객체 작성하기
//==> 이 객체는 네트워크를 통해서 데이터가 전달되어야하기 때문에 직렬화가 필요하다.
//==> 그래서 Serializable 을 구현하여 작성해야한다.

public class TestVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private int num;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public static void main(String[] args) {

	}

}
