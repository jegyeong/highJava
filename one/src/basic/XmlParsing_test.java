package basic;

import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlParsing_test {

	String url = "inventory.xml";

	public static void main(String[] args) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		XmlParsing_test test = new  XmlParsing_test();
		test.DOM();
	//	test.Xpath();
	}
	
	void DOM() {
		try {
			/*
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			*/
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
			
			doc.getDocumentElement().normalize();
			System.out.println("Root element :"+ doc.getDocumentElement().getNodeName());
		
			NodeList nList = doc.getElementsByTagName("computer");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("######################");
					System.out.println("serialNo : "+ eElement.getAttributes().item(0).getNodeValue());
					System.out.println("model : "+ eElement.getElementsByTagName("model").item(0).getChildNodes().item(0).getNodeValue());
					System.out.println("os : "+ eElement.getElementsByTagName("os").item(0).getChildNodes().item(0).getNodeValue());
					System.out.println("cpu : "+ eElement.getElementsByTagName("cpu").item(0).getChildNodes().item(0).getNodeValue());
					System.out.println("ram : "+ eElement.getElementsByTagName("ram").item(0).getChildNodes().item(0).getNodeValue());
					System.out.println("price :"+ eElement.getElementsByTagName("price").item(0).getChildNodes().item(0).getNodeValue());
				}
			}
			System.out.println("######################");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void Xpath(){
		try {
		InputSource is = new InputSource(new FileReader(url));
		
		/*
		DocumentBuilderFactory  dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(is);
		*/
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	
		/*
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();
		*/
		XPath  xpath = XPathFactory.newInstance().newXPath();

		String expression = "//*/computer";
		NodeList node = (NodeList) xpath.evaluate(expression,document, XPathConstants.NODESET);
		
		System.out.println("----------------------------");
		for( int i=0; i<node.getLength(); i++ ){
			Element el =(Element)node.item(i);
			Node Element = el.getElementsByTagName("model").item(0).getChildNodes().item(0);
			Node attribute = node .item(i).getAttributes().item(0);
			
			System.out.println((i+1)+". od : "+attribute.getTextContent());
			System.out.println((i+1)+". model : " + Element.getTextContent() );
		}
		System.out.println("----------------------------");
		
		expression = "*/computer/price";
		NodeList cols= (NodeList) xpath.evaluate(expression,document, XPathConstants.NODESET);
		
		System.out.println("-----------검색한 결과-----------");
		System.out.println("*/computer/os");
		for( int i=0; i<cols.getLength(); i++ ){
		// attributes중 'android:label'의 값을 가져온다.
		  Node d = cols.item(i);
		  System.out.println((i+1)+". " + d.getTextContent());
		}
		System.out.println("----------------------------");
		
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}	





