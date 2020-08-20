package basic;

import java.util.ArrayList;
import java.util.List;
 
 
 
public class MainApplication {
    
    public static void main(String[] args) {
        // 엑셀로 쓸 데이터 생성
        
        List<CustomerVo> list = new ArrayList<CustomerVo>();
        list.add(new CustomerVo("heeya", "전다희", "20", "hp5192@naver.com"));
        list.add(new CustomerVo("ljg5959", "이제경", "19", "wprud0502@naver.com"));
        list.add(new CustomerVo("kwg282", "김우경", "18", "kwk950807@naver.com"));
        list.add(new CustomerVo("reummm0328", "남아름", "22", "ioio852@naver.com"));
        
        CustomerExcelWriter excelWriter = new CustomerExcelWriter();
        //xls 파일 쓰기
        excelWriter.xlsWiter(list);
        
        //xlsx 파일 쓰기
        excelWriter.xlsxWiter(list);
        
      //파일 읽어오기
         CustomerExcelReader excelReader = new CustomerExcelReader();
         
         System.out.println("*****xls*****");
         List<CustomerVo> xlsList = excelReader.xlsToCustomerVoList("C:\\Users\\SEM-PC\\Desktop\\4조\\excelTest\\testWrite.xls");
         printList(xlsList);
         
         System.out.println("*****xlsx*****");
         List<CustomerVo> xlsxList = excelReader.xlsxToCustomerVoList("C:\\Users\\SEM-PC\\Desktop\\4조\\excelTest\\testWrite.xlsx");
         printList(xlsxList);
        
    }
    
    public static void printList(List<CustomerVo> list) {
        CustomerVo vo;
        
        for (int i = 0; i < list.size(); i++) {
            vo = list.get(i);
            System.out.println(vo.toString());
        }
    }
    
}


