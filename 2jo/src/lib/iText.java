package lib;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class iText {
	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		Document doc = new Document();
		PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\SEM-PC\\Desktop\\ddd\\test01.pdf"));
		
		doc.open();
		doc.add(new Paragraph("Hello World"));
		
		System.out.println("파일생성완료");
		doc.close();
		
		}
}
