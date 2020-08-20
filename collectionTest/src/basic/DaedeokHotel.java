package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class DaedeokHotel {
	private Map<Integer, Room> myHotel = new HashMap<Integer, Room>();
	Scanner scan = new Scanner(System.in);
	
	// 생성자  ==> 방번호와 종류 초기화 작업 수행
	public DaedeokHotel() {
		// 객실 초기화
		for(int i=2; i<=4; i++){
			String roomType = null;
			switch(i){	// 방 종류 정하기
				case 2 : roomType = "싱글룸"; break;
				case 3 : roomType = "더블룸"; break;
				case 4 : roomType = "스위트룸"; break;
			}
			
			for(int j=1; j<=9; j++){
				int roomNum = i * 100 + j;		// 방번호 만들기
				Room room = new Room(roomNum, roomType);  // Room객체 생성
				myHotel.put(roomNum, room);			// Map에 Room객체 추가
			}
		}
	}

	public static void main(String[] args) {
		new DaedeokHotel().hotelStart();
	}
	
	// 메뉴 출력 메서드
	public int displayMenu(){
		System.out.println();
		System.out.println("");
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print(" 선택>> ");
		int num = scan.nextInt();
		
		return num;
	}
	
	// 프로그램이 시작되는 메서드
	public void hotelStart(){
		System.out.println();
		System.out.println("*********************************************");
		System.out.println("          호텔문을 열었습니다. 어서오십시오.");
		System.out.println("*********************************************");
		System.out.println();
		
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1 : 				// 체크인
					checkIn(); 	break;
				case 2 : 				// 체크아웃
					checkOut();  break;
				case 3 : 				// 전체 객실 상태 출력
					showRoom(); break;
				case 4 : // 업무 종류
					System.out.println();
					System.out.println("*********************************************");
					System.out.println("            호텔문을 닫았습니다.");
					System.out.println("*********************************************");
					return;
					
				default : System.out.println("번호를 잘못입력했습니다. 다시입력하세요.");
			}
		}
	}
	
	
	// 체크인하는 메서드
	public void checkIn(){
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("                  체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.print(" 방 번호 입력 >> ");
		
		int roomNum = scan.nextInt();
		
		// 객실 번호가 있는지 여부 검사
		if(!myHotel.containsKey(roomNum)){
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}
		
		// 해당 객실에 손님이 있는지 여부 검사
		if(myHotel.get(roomNum).getGuestName()!=null){
			System.out.println(roomNum + "호 객실에는 이미 손님이 있습니다.");
			return;
		}
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String name = scan.next();
		
		// 입력한 손님이름을 해당 객실에 입력한다.
		myHotel.get(roomNum).setGuestName(name);
		
		System.out.println(roomNum + "호 객실에 " + name + "씨가 체크인 했습니다.");
	}
	
	// 체크 아웃을 처리하는 메서드
	public void checkOut(){
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("                  체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃할 방번호를 입력하세요.");
		System.out.print(" 방 번호 입력 >> ");
		
		int roomNum = scan.nextInt();
		
		// 객실 번호가 있는지 여부 검사
		if(!myHotel.containsKey(roomNum)){
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}
		
		// 해당 객실에 손님이 있는지 여부 검사
		if(myHotel.get(roomNum).getGuestName() == null ){
			System.out.println(roomNum + "호 객실에는 손님이 없습니다.");
			return;
		}
		
		// 체크 아웃 작업  ==> 해당 객실의 손님이름을 null로 설정한다.
		String name = myHotel.get(roomNum).getGuestName();
		myHotel.get(roomNum).setGuestName(null);
		
		System.out.println(roomNum + "호 객실의 " + name + "님이 체크아웃되었습니다.");
		
	}

	// 전체 객실 상태를 출력하는 메서드
	public void showRoom(){
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("           현재 객실 상태");
		System.out.println("----------------------------------------");
		System.out.println("방 번호\t 방 종류\t 투숙객 이름");
		System.out.println("----------------------------------------");
		
		// 호텔의 객실 번호를 저장하는 List생성 및 초기화
		ArrayList<Integer> roomNumList = new ArrayList<>(myHotel.keySet());
		Collections.sort(roomNumList);  // 객실번호 정렬하기
		
		//Iterator<Integer> roomNumList = myHotel.keySet().iterator();
		
		// 객실 개수만큼 반복
		for(int roomNum : roomNumList){
		//while(roomNumList.hasNext()){
			//int roomNum = roomNumList.next();
			Room room = myHotel.get(roomNum);
			System.out.print(room.getRoomNumber() + "\t");
			System.out.print(room.getRoomType() + "\t");
			
			String name = room.getGuestName();
			if(name==null){
				name = "  -";
			}
			System.out.println(name);
		}
		System.out.println("-----------------------------------------");
		System.out.println();
		
	}
	
	
}


class Room{
	private int roomNumber;			// 방번호
	private String roomType;		// 방종류
	private String guestName;		// 투숙객 이름
	
	// 생성자
	public Room(int roomNumber, String roomType) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
}

