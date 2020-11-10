package iTextSample;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class create_PDF {

	public static void main(String args[]) throws Exception {
		// PDF 작성자 생성자 생성 및 경로 설정
		String dest = "C:/Users/PC-20/Desktop/my_doc2.pdf";
		PdfWriter writer = new PdfWriter(dest);

		// PDF문서 생성
		PdfDocument pdfDoc = new PdfDocument(writer);

		// 페이지 추가
		pdfDoc.addNewPage();

		// 오류 불가 basefont 미지원 itext 7
		// PdfFont font = PdfFontFactory.createFont(
		// "D:/A_TeachingMaterial/3.HighJava/workspace/iTextSample/src/NanumGothic.ttf",
		// "UTF-8", true);

		// 문서 생성
		Document document = new Document(pdfDoc);

		// document.setFont(font);
		Paragraph p = new Paragraph("안녕아년ㅇ  hihihi ");
		document.add(p);
		// 문서 닫기
		document.close();
		System.out.println("PDF Created / PDF 생성됨");
	}
}