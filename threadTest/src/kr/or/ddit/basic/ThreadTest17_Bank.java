package kr.or.ddit.basic;

//은행의 입출금을 쓰레드로 처리하는 에제
//(동기화 예제)
public class ThreadTest17_Bank {

	// 잔액이 저장될 변수
	private int balance;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금하는 메서드 (동기화 처리 필요 - 동기화처리를 안하면 입금자가 손해 현재 콘솔에선 패스)
	public void deposit(int money) {
		// 잔액에 입금량 추가
		balance += money;
	}

	// 출금하는 매서드 (출금 성공 true / 출금 실패 : false 반환)
	// 메소드 동기화시
	// public synchronized boolean withdraw(int money) {
	public boolean withdraw(int money) { // 메소드 동기화시

		// 블록 동기화시 synchronized (this) {
		synchronized (this) { // 블록 동기화시 사용
			// 출금 가능 확인 - 출금이 가능
			if (balance >= money) {
				// 다른 메소드 실행을 위해 약간의 지연을 줌. 시간 지연용 for문
				for (int i = 0; i < 100000000; i++) {
				}
				// 출금 처리.
				balance -= money;
				System.out.println("출금 완료 !! balance = " + getBalance());
				return true;
			} else {
				// 출금 불가
				System.out.println("잔액부족으로 출금이 불가능 합니다.");
				return false;
			}
		} // 동기화 블록시 마감
	}

	public static void main(String[] args) {
		ThreadTest17_Bank acount = new ThreadTest17_Bank();
		// 잔액 지정 1만원
		acount.setBalance(10000);

		// 익명 구현체로 쓰레드 구현
		Runnable test = new Runnable() {

			@Override
			public void run() {
				// 6000원 출금
				boolean result = acount.withdraw(6000);
				System.out.println("쓰레드에서 result = " + result + //
				", balance : " + acount.getBalance());
			}
		};
		// ----------------

		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);

		th1.start();
		th2.start();
	}

}
