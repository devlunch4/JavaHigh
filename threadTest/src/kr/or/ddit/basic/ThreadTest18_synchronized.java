package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

// Vector, Hashtable = Map 등의 예전부터 존재하던 Collection 객체들은

// 내부에 동기화 처리가 되어있다.
// 그런데, 최근에 새로 구성된 Collection들은 동기화처리가 되어있지 않다.
// 그래서(따라서), 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리를 한 후에 사용해야 한다.

public class ThreadTest18_synchronized {

	public static void main(String[] args) {

		Vector<Integer> vec = new Vector<>();

		// 동기화 처리가 되지않은 list
		List<Integer> list1 = new ArrayList<Integer>();

		// 동기화 처리를 한경우
		List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());

		// 익명구현체로 스레드 구현
		Runnable r = new Runnable() {

			@Override
			// 만번
			public void run() {
				for (int i = 0; i < 10000; i++) {
					vec.add(i);
					list1.add(i);
					list2.add(i);
				}
			}
		};

		// ------------------------------------------------

		Thread[] ths = new Thread[] { new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r) };

		for (Thread th : ths) {
			th.start();
		}
		for (Thread th : ths) {
			try {
				th.join();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("vec : " + vec);
		System.out.println("list1의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size());
	}
}
