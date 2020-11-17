package kr.or.ddit.basic;

//데몬 쓰레드 연습
public class ThreadTest09_Daemon {

	public static void main(String[] args) {
		// Thread th1 = new AutoSaveThread();
		AutoSaveThread autosave = new AutoSaveThread();

		///// 데몬 쓰레드 설정 ==> 반드시 start()메서드 호출전에 실행된다.
		autosave.setDaemon(true);
		autosave.start();

		try {
			for (int i = 0; i < 20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
		}

		// 데몬 쓰레드 설정시 메인 쓰레드가 종료되면 데몬스레드는 자동으로 종료된다.
		System.out.println("main Thread End");
	}
}

// 자동 저장하는 쓰레드

class AutoSaveThread extends Thread {
	// 작업내용을 저장하는 매서드
	private void save() {
		System.out.println("Auto Saving...");
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(3000);
				save();
			} catch (Exception e) {
				//
			}
		}
	}
}
