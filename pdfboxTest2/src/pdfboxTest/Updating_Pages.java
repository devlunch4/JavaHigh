package pdfboxTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class Updating_Pages {

	public static void main(String args[]) throws IOException {

		// 파일 불러오기 (경로지정 >>> 파일 읽기)
		File file = new File("C:/Users/PC-20/Desktop/my_doc.pdf");
		PDDocument document = PDDocument.load(file);

		// 3번째 페이지 변수 설정 페이지는 0번부터 시작
		PDPage page3 = document.getPage(2);

		// 문서에서 페이지3 설정 가져오기
		PDPageContentStream contentStream = new PDPageContentStream(document, page3);

		// contentStream 문자 추가 시작
		contentStream.beginText();

		// 폰트 설정 시작
		// 한글폰트 가져오기 (나눔고딕)
		InputStream fontStream = new FileInputStream("D:/D_Other/NanumGothic.ttf");
		PDType0Font fontNanum = PDType0Font.load(document, fontStream);

		// 설정 폰트 로딩 및 폰트 크기 설정
		contentStream.setFont(fontNanum, 25);

		// 폰트 위치 설정 (x축 y축)
		contentStream.newLineAtOffset(10, 500);

		// 입력할 문자
		String ntext = "나눔고딕으로 폰트 설정후 작성 했습니다...";

		// 문자 추가
		contentStream.showText(ntext);

		// 문자 스트림 마감.
		contentStream.endText();

		// 문자 스트림 종료
		contentStream.close();

		/////////////////////////////////////////////////////////////////////////
		// 이미지넣기
		/////////////////////////////////////////////////

		// 이미지 설정
		// InputStream inimg = new FileInputStream(new File("D:/D_Other/Koala.jpg"));
		PDImageXObject pdImage = PDImageXObject.createFromFile("D:/D_Other/Koala.jpg", document);
		// 2번째 페이지 변수 설정 페이지는 0번부터 시작
		PDPage page2 = document.getPage(1);

		// 문서에서 이미지 스트림 설정
		PDPageContentStream contentStream2 = new PDPageContentStream(document, page2);

		// 문서에 이미지 크기 설정
		pdImage.setHeight(300);
		pdImage.setWidth(400);
		
		contentStream2.drawImage(pdImage, 10, 100);

		// 이미지 스트림 종료
		contentStream2.close();

		// 확인1
		System.out.println("Content added / 내용 추가 완료");

		// 파일저장
		document.save("C:/Users/PC-20/Desktop/my_doc.pdf");
		System.out.println("저장완료");

		// 문서 종료
		document.close();
		System.out.println("문서종료");

	}
}