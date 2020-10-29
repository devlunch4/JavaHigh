package kr.or.ddit.basic;

public class ThreadTest01_single {

	// SINGLE thread method
	public static void main(String[] args) {
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
		}

		System.out.println();
		System.out.println();

		for (int i = 0; i <= 200; i++) {
			System.out.print("$");
		}
	}

}
