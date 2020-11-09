package pdfboxTest;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

public class pdfboxTest {

	public static void main(String[] args) throws IOException {
		// PDF 문서 객체 생성
		PDDocument document = new PDDocument();

		// 문서 저장 ==> 저장후 출력문으로 확인
		document.save("C:/Users/PC-20/Desktop/my_doc.pdf");
		System.out.println("PDF created / PDF 생성됨");

		// 문서 닫기
		document.close();
	}
}