package basic;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * MyHandler class defines the actions to be taken
 * in response to SAX Parser callback events.
 */
public class MyHandler extends DefaultHandler {

    //List to hold Employees object
    private List<Computer> compList = null;
    private Computer comp = null;


    // Getter method for list of computers list
    public List<Computer> getCompList() {
        return compList;
    }

    boolean bModel;
    boolean bOs;
    boolean bCpu;
    boolean bRam;
    boolean bPrice;

    @Override
    //이 메소드는 태그가 시작될때 이것을 읽으면서 발생하는 이벤트 핸들링 메소드이다.
    //예 : <tag>data</tag> 여기서, 왼쪽에 있는 <tag> 가 읽히면서 발생하는 이벤트
    public void startElement(String uri, String localName, String qName, Attributes attributes)throws SAXException {
    	//uri - 이름 공간 URI. 요소가 이름 공간 URI를 가지지 않는 경우, 또는 이름 공간 처리를 하지 않는 경우는 공문자열
    	//localName - 접두사를 포함하지 않는 로컬명. 이름 공간 처리를 하지 않는 경우는 공문자열
    	//qName - 접두사를 가지는 수식명. 수식명을 사용할 수 없는 경우는 공문자열
    	//attributes - 요소에 부가된 속성. 속성이 존재하지 않는 경우, 비어있는 Attributes 객체
        if (qName.equalsIgnoreCase("Inventory")) {
            if (compList == null)
                compList = new ArrayList<Computer>();
        } else if (qName.equalsIgnoreCase("Computer")) {
            // Create a new Computer object, and set the serial number from the attribute
            comp = new Computer();
            // Get the serialNo attribute
            String serialNumber = attributes.getValue("serialno");
            comp.setSerialNo(serialNumber);

        // Set boolean values for fields, will be used in setting Employee variables
        } else if (qName.equalsIgnoreCase("model")) {
            bModel = true;
        } else if (qName.equalsIgnoreCase("os")) {
            bOs = true;
        } else if (qName.equalsIgnoreCase("cpu")) {
            bCpu = true;
        } else if (qName.equalsIgnoreCase("ram")) {
            bRam = true;
        } else if (qName.equalsIgnoreCase("price")) {
            bPrice = true;
        }
    }
    
    //이 메소드는 태그가 끝날때 이것을 읽으면서 발생하는 이벤트 핸들링 메소드이다.
    //예 : <tag>data</tag> 여기서, 오른쪽에 있는 <tag> 가 읽히면서 발생하는 이벤트
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	//uri - 이름 공간 URI. 요소가 이름 공간 URI를 가지지 않는 경우, 또는 이름 공간 처리를 하지 않는 경우는 공문자열
    	//localName - 접두사를 포함하지 않는 로컬명. 이름 공간 처리를 하지 않는 경우는 공문자열
    	//qName - 접두사를 가지는 수식명. 수식명을 사용할 수 없는 경우는 공문자열
       
    	if (qName.equalsIgnoreCase("Computer")) {
            // Add the Computer object to the list
            compList.add(comp);
        }
    }
    
    //이 메소드는 태그의 내용을 읽으면서 발생하는 이벤트 핸들링 메소드이다.
    //예 : <tag>data</tag> 여기서, 가운데 있는 data 가 읽히면서 발생하는 이벤트
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
    	//요소내의 문자 데이터의 통지를 받습니다.
    	//디폴트에서는 아무것도 실시하지 않습니다. 
    	//어플리케이션의 작성자는 이 메서드를 오버라이드(override) 해, 각 문자 데이타체크에 대해서 특수한 액션 (노드 또는 버퍼에의 데이터의 추가 데이터의 파일에의 출력등)을 실행할 수 있습니다.
    	//ch - 문자 데이터
    	//start - 문자 배열내의 개시 위치
    	//length - 문자 배열로부터 사용되는 문자수
        if (bModel) {
            // Set computer model age
            comp.setModel(new String(ch, start, length));
            bModel = false;
        } else if (bOs) {
            comp.setOs(new String(ch, start, length));
            bOs = false;
        } else if (bCpu) {
            comp.setCpu(new String(ch, start, length));
            bCpu = false;
        } else if (bRam) {
            comp.setRam(new String(ch, start, length));
            bRam = false;
        } else if (bPrice) {
            comp.setPrice(Double.parseDouble(new String(ch, start, length)));
            bPrice = false;
        }
    }
}
