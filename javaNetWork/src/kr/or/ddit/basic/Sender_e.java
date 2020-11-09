package kr.or.ddit.basic;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender_e extends Thread {
	private Socket socket;
	private DataOutputStream dos;
	private String name;
	private Scanner scan;

	public Sender_e(Socket socket) {
		super();
		this.socket = socket;
		
		scan = new Scanner(System.in);
		System.out.println("이름 입력 : ");
		name = scan.nextLine();
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {

		}
	}

	@Override
	public void run() {
		while (dos != null) {
			try {
				dos.writeUTF(name + ":" + scan.nextLine());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
