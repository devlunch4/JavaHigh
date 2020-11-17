package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {

	public static void main(String[] args) {
		// 입력 window 생성
		// 입력시 입력값 출력, 취소시 null 출력, 
		//아무값없이 엔터(확인시) 공란이 입력. esc누를시 취소와 같음		
		
		//String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		//System.out.println("입력값 : " + str);
		
		for (int i = 10; i >= 1; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		System.out.println("입력값 : " + str);
	}

}
