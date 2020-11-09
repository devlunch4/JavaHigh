package iTextSample;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public class create_PDF {

	public static void main(String args[]) throws Exception {
		// PDF 작성자 생성자 생성 및 경로 설정
		String dest = "C:/Users/PC-20/Desktop/my_doc2.pdf";
		PdfWriter writer = new PdfWriter(dest);

		// PDF문서 생성
		PdfDocument pdfDoc = new PdfDocument(writer);

		// 페이지 추가
		pdfDoc.addNewPage();

		// 문서 생성
		Document document = new Document(pdfDoc);

		// 문서 닫기
		document.close();
		System.out.println("PDF Created / PDF 생성됨");
	}
}