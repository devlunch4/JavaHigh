package kr.or.ddit.vo;

import java.io.Serializable;

//파일전송용 VO클래스
public class FileInfoVO implements Serializable {
	// 파일명이 저장될 변수
	private String fileName;
	// 파일의 내용이 저장될 변수
	private byte[] fileData;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
