package kr.or.ddit.basic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/*
10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수 순으로 출력한다.
*/
public class HorseRacing_ttt {

	public static void main(String[] args) {

		List<Horse_ttt> list = new ArrayList<Horse_ttt>();
		
		list.add(new Horse_ttt("천둥"));
		list.add(new Horse_ttt("용식"));
		list.add(new Horse_ttt("번개"));
		list.add(new Horse_ttt("바람"));
		list.add(new Horse_ttt("사랑"));
		list.add(new Horse_ttt("기둥"));
		list.add(new Horse_ttt("계룡"));
		list.add(new Horse_ttt("도리"));
		list.add(new Horse_ttt("날쌘"));
		list.add(new Horse_ttt("당근"));
		
		for(Horse_ttt horse : list) {
			horse.start();
		}
		
		PrintHorse ph = new PrintHorse(list);
		ph.start();
		
	}
}

class Horse_ttt extends Thread implements Comparable<Horse_ttt>{

	private String hname;
	private int rank = 0;
	private int location = 0;
	private boolean goal = false;

	public Horse_ttt(String hname) {
		this.hname = hname;
	}

	@Override
	public void run() {
		
		while(true) {
			try {
				Thread.sleep((int)(Math.random()*400 + 150));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(location == 50) {
				break;
			}
			location++;
		}
	}
	
	public String getHname() {
		return hname;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public boolean isGoal() {
		return goal;
	}

	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(Horse_ttt horse) {
		if(this.rank > horse.rank) {
			return 1;
		}else if(this.rank == horse.rank) {
			return 0;
		}else {
			return -1;
		}
	}
}


class PrintHorse extends Thread{
	List<Horse_ttt> list;
	
	public PrintHorse(List<Horse_ttt> horseList) {
		this.list = horseList;
	}
	
	@Override
	public void run() {
		String[] arr = new String[50];
		int rank = 1;
		boolean running = true;
		
		while(running) {
			
			for(Horse_ttt horse : list) {
				if(horse.isGoal() == true) { //도착하면
					System.out.print(horse.getHname() + " : " + " ");
					
					for (int i = 0; i < 50; i++) {
						arr[i] = "*";
						System.out.print(arr[i]);
					}
					System.out.println();
					continue;
				};
				
				System.out.print(horse.getHname() + " : " + " ");
				for (int i = 0; i < 50; i++) {
					arr[i] = "-";
					if(horse.getLocation() == i) {
						arr[i] = ">";
					}
				}
				
				for (int i = 0; i < 50; i++) {
					System.out.print(arr[i]);
				}
				System.out.println();
				
				if(horse.getLocation() >= 50) {
					horse.setRank(rank);
					rank++;
					horse.setGoal(true);
				}
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (int i = 0; i < 20; i++) {
				System.out.println("");
			}
			
			if(rank == 11) {
				System.out.println("============ 경 기 끝 ============");
				running = false;
			}
		}
		Collections.sort(list);
		for(Horse_ttt horse : list) {
			System.out.println(horse.getHname() + " 말  : " + horse.getRank() + " 등!!!");
			System.out.println();
		}
		System.exit(0);
	}
}













