package lib;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFBoxEx {
	public static void main(String[] args) throws IOException{
		PDDocument document =null;
		document =new PDDocument();
		
		PDPage page = new PDPage();
		document.addPage(page);
		
		PDFont font = PDType1Font.TIMES_ITALIC;
		PDPageContentStream contentStream = null;
		
		contentStream = new PDPageContentStream(document, page);
		contentStream.setFont(font,50);
		contentStream.beginText();
		contentStream.newLineAtOffset(150, 750);
		contentStream.showText("Hello~ PDF Test");
		contentStream.endText();
		
		contentStream.close();
		
		document.save("C:\\Users\\SEM-PC\\Desktop\\ddd\\test.pdf");
		System.out.println("파일생성완료");
		
		document.close();
		
	
		
		
		
		
	}
	
}
