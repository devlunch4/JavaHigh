package pdfboxTest;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Adding_Pages {

	public static void main(String args[]) throws IOException {

		// 문서 객체 생성
		PDDocument document = new PDDocument();

		for (int i = 0; i < 10; i++) {
			// 빈페이지 생성
			PDPage blankPage = new PDPage();

			// 빈페이지 문서에 추가
			document.addPage(blankPage);
		}

		// 문서 저장 및 생성완료 출력
		document.save("C:/Users/PC-20/Desktop/my_doc.pdf");
		System.out.println("PDF created / PDF 생성됨");

		// 문서 닫기
		document.close();
	}
}