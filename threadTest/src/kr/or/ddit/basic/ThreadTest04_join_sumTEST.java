package kr.or.ddit.basic;

// 1~20억까지의 합계를 구하는 프로그램
// 하나의 쓰레드가 단독으로 처리했을 때와
// 여러개의 쓰레ㅡ가 협력해서 처리할 떄의 처리시간을 비교

public class ThreadTest04_join_sumTEST {

	public static void main(String[] args) {
		// 단독으로 처리하는 쓰레드 생성
		SumThread sm = new SumThread(1L, 2_000_000_000L);

		// 협력처리하는 쓰레드 생성
		SumThread[] sums = new SumThread[] { //
				new SumThread(1L, 500_000_000L),
				//
				new SumThread(500_000_001L, 1_000_000_000L),
				//
				new SumThread(1_000_000_001L, 1_500_000_000L),
				//
				new SumThread(1_500_000_001L, 2_000_000_000L)
				//
		};

		// 계산 함수들 또는 메소드 호출

		// 단독으로 처리하기
		long startTime = System.currentTimeMillis();
		sm.start();
		try {
			sm.join();
		} catch (InterruptedException e) {
			System.out.println("오류발생");
		}
		long endTime = System.currentTimeMillis();

		System.out.println("단독으로 처리했을떄 처리시간 : " + (endTime - startTime));
		System.out.println();

		// 여러 쓰레드가 협력해서 처리하는경우
		startTime = System.currentTimeMillis();

		for (int i = 0; i < sums.length; i++) {
			sums[i].start();
		}

		for (SumThread th : sums) {
			try {
				th.join();
			} catch (Exception e) {

			}
			endTime = System.currentTimeMillis();

		}
		System.out.println("협력해서 처리했을떄 처리시간 : " + (endTime - startTime));

	}
}

// 1~20억 단일>>> 변환 >>> 시작값과 입력값을 받도록 수정
class SumThread extends Thread {
	// 합계를 구할 영역의 시작값과 종료값을 저장할 변수 선언
	private long startnum;
	private long endnum;

	public SumThread(long startsum, long endnum) {
		super();
		this.startnum = startsum;
		this.endnum = endnum;
	}

	@Override
	public void run() {
		long sum = 0L;
		// 값 수정후 메소드 변경
		// for (long i = 1L; i <= 2_000_000_000L; i++) {
		for (long i = startnum; i <= endnum; i++) {
			sum += i;
		}
		System.out.println("단일 메소드 합계 : " + sum);
	}

}

//////////////////
// 미사용-미사용-미사용-미사용-미사용-미사용
// 1~5억 단일
class SumThread2 extends Thread {
	private long startnum;
	private long endnum;

	public SumThread2(long startsum, long endnum) {
		super();
		this.startnum = startsum;
		this.endnum = endnum;
	}

	@Override
	public void run() {
		long sum = 0L;
		for (long i = startnum; i <= endnum; i++) {
			sum += i;
		}
		System.out.println("단일합계 : " + sum);
	}
}