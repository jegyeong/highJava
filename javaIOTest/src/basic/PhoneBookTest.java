package basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
/*
문제) 이름, 주소, 전화번호 속성을 갖는 Phone클래스를 만들고
	이 Phone클래스를 이용하여 전화번호 정보를 관리하는 프로그램을 완성하시오.
	이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력하는 기능이 있다.

	전체 전화번호 정보는 Map을 이용하여 관리한다.
	(key값은 '이름'으로 사용하고, value값으로는 Phone클래스의 인스턴스로 한다.)
	
	- 저장 파일명은 'd:/d_other/phoneBook.dat'로 한다.
	- 이 프로그램이 처음 실행될 때 저장된 파일이 있으면 해당 파일의 내용을 읽어온다.
	- '6. 전화번호 저장' 메뉴를 선택하면 모든 데이터를 파일로 저장한다.
	- 프로그램을 종료할 때 데이터의 변동이 있으면 '저장후 종료'하도록 처리한다.
	
	실행 예시)
	===============================
			전화번호 관리 프로그램
	===============================
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		6. 전화번호 저장
		0. 프로그램 종료
	-------------------------------
	메뉴선택 >> 1
	
	새롭게 등록할 전화번호 정보를 입력하세요.
	이름 >> 홍길동
	전화번호 >> 010-1111-2222
	주소 >> 대전시
	
	홍길동 전화번호 정보가 추가되었습니다.
	
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
	-------------------------------
	메뉴선택 >> 5
	
	
	===============================
	번호     이름      전화번호               주소
	===============================
	 1   홍길동   010-1111-2222    대전시
	 ~~~~
	===============================
	출력 완료...
	
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
	-------------------------------
	메뉴선택 >> 0
	
	프로그램을 종료합니다...

*/
public class PhoneBookTest {
	private Scanner scan;
	private HashMap<String, Phone> phoneBookMap;
	private String fileName = "d:/d_other/phoneBook.dat";
	private boolean dataChange;		// 데이터의 변경이 있는지 여부를 저장하는 변수 (있으면 true);
	
	
	// 생성자
	public PhoneBookTest() {
		scan = new Scanner(System.in);
		
		phoneBookMap = load();  // 파일에 저장된 자료를 가져와 Map객체에 저장한다.
				
		if(phoneBookMap == null ){	// 파일이 없거가 읽기에 실패하면 새로운 Map객체를 생성한다.
			phoneBookMap = new HashMap<>();
		}
	}

	public static void main(String[] args) {
		new PhoneBookTest().phoneBookStart();
	}
	
	// 메뉴를 출력하고 실행할 메뉴 번호를 입력받아 반환하는 메서드
	public int displayMenu(){
		System.out.println();
		System.out.println("    1. 전화번호 등록");
		System.out.println("    2. 전화번호 수정");
		System.out.println("    3. 전화번호 삭제");
		System.out.println("    4. 전화번호 검색");
		System.out.println("    5. 전화번호 전체 출력");
		System.out.println("    6. 전화번호 저장");
		System.out.println("    0. 프로그램 종료");
		System.out.println("-------------------------------");
		System.out.print("메뉴선택 >> ");
		int num = scan.nextInt();
		return num;
	}
	
	// 프로그램을 시작하는 메서드
	public void phoneBookStart(){
		System.out.println("==========================================");
		System.out.println("            전화번호 관리 프로그램 ");
		System.out.println("==========================================");
		
		while(true){
			int choice = displayMenu();
			
			switch(choice){
				case 1 : 		// 등록
					insert(); break;
				case 2 : 		// 수정
					update(); break;
				case 3 : 		// 삭제
					delete(); break;
				case 4 : 		// 검색
					search(); break;
				case 5 : 		// 전체 자료 출력
					displayAll(); break;
				case 6 : 		// 저장
					save(); break;
				case 0 : 	// 종료
					if(dataChange==true){  // 데이터의 변경이 있으면 save()메서드를 호출한다.
						System.out.println("변경된 내용을 저장합니다.");
						save();
					}
					System.out.println();
					System.out.println("프로그램을 종료합니다...");
					return;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요.");
			}
			
		}
		
	} 
	
	
	// 전화번호 정보가 저장된 파일을 읽어와서 반환하는 메서드
	public HashMap<String, Phone> load(){
		HashMap<String, Phone> pMap = null;
		File file = new File(fileName);
		
		if(!file.exists()){	// 저장된 파일이 없으면 null 반환
			return null;
		}
		
		ObjectInputStream ois = null;
		try {
			// 객체 입력용 스트림 객체 생성
			ois = new ObjectInputStream( 
					new BufferedInputStream(new FileInputStream(file)));
			
			Object obj = ois.readObject(); // 저장된 객체 읽어오기
			if(obj instanceof HashMap){
				pMap = (HashMap<String, Phone>) obj;
			}
			
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		} finally {
			if(ois!=null) try{ ois.close(); }catch (IOException e){}
		}
		
		return pMap;
		
	}
	
	
	// 전화번호 정보를 저장하는 메서드
	public void save(){
		ObjectOutputStream oos = null;
		try {
			// 출력용 스트림 객체 생성 ==> 객체 저장용
			oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(fileName)));
			
			oos.writeObject(phoneBookMap);  // Map객체를 저장한다.
			
			System.out.println("저장이 완료되었습니다.");
			dataChange = false;
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try { oos.close(); } catch (IOException e) { }
		}
	}
	
	
	
	// 전화번호 정보를 검색하는 메서드
	public void search(){
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			return;
		}
		
		// 검색할 이름(key값)을 이용해서 해당 Phone객체를 구한다.
		Phone p = phoneBookMap.get(name);
		
		// 검색 결과 출력하기
		System.out.println(name + "씨의 전화번호 정보");
		System.out.println("이     름 : " + p.getName());
		System.out.println("전화번호 : " + p.getTel());
		System.out.println("주     소 : " + p.getAddr());
		
		
	}
	
	
	
	// 전화번호 정보를 삭제하는 메서드
	public void delete(){
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			return;
		}
		
		phoneBookMap.remove(name);
		System.out.println(name + "씨의 전화번호 정보를 삭제했습니다.");
		dataChange = true;
	}
	
	
	// 전화번호 정보를 수정하는 메서드
	public void update(){
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		
		System.out.print("이름 >> ");
		String name = scan.next();
		
		// 수정할 사람이 이미 등록 되었는지 여부 검사
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 등록되지 않은 사람입니다...");
			return;
		}
		
		System.out.print("수정할 전화번호 >> ");
		String newTel = scan.next();
		
		System.out.print("수정할 주소 >> ");
		String newAddr = scan.next();
		
		// 변경된 데이터를 Map에 넣는다.
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));
		
		System.out.println(name + "씨 전화번호 정보가 수정되었습니다.");
		dataChange = true;
	}
	
	
	
	// 새로운 전화번호 정보를 등록(추가)하는 메서드
	// 이미 등록된 사람은 등록되지 않는다.
	public void insert(){
		System.out.println();
		System.out.println("등록할 새로운 전화번호 정보를 입력하세요.");
		
		System.out.print("이름 >> ");
		String name = scan.next();
		
		// 이미 등록된 사람인지 여부를 검사한다.
		if(phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}
		
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		
		System.out.print("주 소 >> ");
		String addr = scan.next();
		
		// 입력한 값을 이용해서 Phone객체 생성
		Phone p = new Phone(name, tel, addr);
		
		// 생성된 Phone객체를 Map에 추가
		phoneBookMap.put(name, p);
		
		System.out.println(name + "씨 전화번호 정보가 추가 되었습니다.");
		dataChange = true;
	}
	
	
	
	// 전체 자료를 출력하는 메서드
	public void displayAll(){
		System.out.println();
		System.out.println("===============================");
		System.out.println("번호\t  이름\t  전화번호\t  주소");
		System.out.println("===============================");

		Set<String> nameSet = phoneBookMap.keySet();
		if(nameSet.size() == 0){
			System.out.println("   등록된 전화번호 정보가 하나도 없습니다.");
		}else{
			Iterator<String> nameIt = nameSet.iterator();
			int cnt = 0; // 번호 출력용 변수
			while(nameIt.hasNext()){
				cnt++;
				String name = nameIt.next();  // 키값(이름) 가져오기
				Phone p = phoneBookMap.get(name);  // 키값을 이용해서 Phone객체(value값) 구하기
				System.out.println(cnt + "\t " + p.getName() + 
							"\t" + p.getTel() + "\t" + p.getAddr());
			}
		}
		
		System.out.println("===============================");
		System.out.println("출력 완료...");
		
	}
	
	

}

// Phone 클래스 만들기
class Phone implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;	// 이름
	private String tel;		// 전화번호
	private String addr;	// 주소
	
	// 생성자
	public Phone() {}
	
	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}
	
	// getter, setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}




