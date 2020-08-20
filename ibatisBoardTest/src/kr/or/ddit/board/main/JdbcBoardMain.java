package kr.or.ddit.board.main;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IJdbcBoardService;
import kr.or.ddit.board.service.JdbcBoardServiceImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardMain {
	
	private IJdbcBoardService service;
	private Scanner scan;
	
	public JdbcBoardMain() {
		service = JdbcBoardServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new JdbcBoardMain().boardStart();
	}
	
	public void boardStart(){
		String title = null;
		int choice = -1;
		while(true){
			if(choice!=3){
				title = null;
			}
			choice = displayMenu(title);
			
			switch(choice){
				case 1 :    // 새글 작성
					insertBoard(); 
					break;
				case 2 :    // 게시글 보기
					viewBoard(); 
					break;
				case 3 :    // 검색
					title = searchBoard(); 
					break;
				case 0 :    // 작업 종료
					System.out.println();
					System.out.println(" 게시판 프로그램 종료...");
					return;
				default : 
					System.out.println(" 번호를 잘못 입력했습니다. 다시 입력하세요. ");
					
			}
		}
		
	}
	
	// 게시판 목록을 보여주고 메뉴를 나타내며, 사용자가 입력한 메뉴 번호를 반환하는 메서드
	public int displayMenu(String boardTitle ){
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("   No        제 목                       작성자        조회수   ");
		System.out.println("---------------------------------------------");
		
		List<JdbcBoardVO> boardList;
		if(boardTitle==null){
			boardList = service.getAllBoardList();
		}else{
			boardList = service.getSearchBoardList(boardTitle);
		}
		
		if(boardList==null || boardList.size()==0){
			System.out.println("     출력할 게시글이 하나도 없습니다. ");
		}else{
			for(JdbcBoardVO boardVo : boardList){
				System.out.println("   " + boardVo.getBoard_no() + "   " 
						+ boardVo.getBoard_title() + "    "
						+ boardVo.getBoard_writer() + "    "
						+ boardVo.getBoard_cnt()
					);
			}  // for
		} // else
		
		System.out.println("---------------------------------------------");
		System.out.println(" 메뉴 : 1.새글 작성     2.게시글 보기    3.검색     0.작업 끝");
		System.out.print(" 작업 선택 >> ");
		int num = scan.nextInt();
		return num;
	}
	
	
	// 새글을 작성하는 메서드
	public void insertBoard(){
		System.out.println();
		scan.nextLine();  // 버퍼 비우기
		
		System.out.println("   새글 작성하기");
		System.out.println("--------------------------------------");
		System.out.print("- 제 목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 작 성 자 : ");
		String writer = scan.nextLine();
		
		System.out.print("- 내 용 : ");
		String content = scan.nextLine();
		
		// 입력한 정보를 VO객체에 넣는다.
		JdbcBoardVO jBoardVo = new JdbcBoardVO();
		jBoardVo.setBoard_title(title);
		jBoardVo.setBoard_writer(writer);
		jBoardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(jBoardVo);
		
		if(cnt>0){
			System.out.println(" 새글이 추가되었습니다.");
		}else{
			System.out.println(" 새글 추가 실패!!!");
		}
		
		
	}

	// 검색할 내용을 입력받아  반환하는 메서드 
	public String searchBoard(){
		scan.nextLine();  // 버퍼 비우기
		
		System.out.println();
		System.out.println("  검색 작업");
		System.out.println("----------------------------------------");
		System.out.print("- 검색할 제목 입력 >> ");
		String title = scan.nextLine();
		
		return title;
		
	}
	
	// 게시글의 내용을 보여부는 메서드
	public void viewBoard(){
		System.out.println();
		System.out.print(" 보기를 원하는 게시물 번호 입력 >> ");
		int boardNo = scan.nextInt();
		
		// 조회수 증가하기
		int cnt = service.setCountIncrement(boardNo);
		
		if(cnt==0){
			System.out.println( boardNo + "번의 게시글은 존재하지 않습니다.");
			return;
		}
		
		JdbcBoardVO boardVo = service.getBoard(boardNo);
		
//		if(boardVo==null){
//			System.out.println( boardNo + "번의 게시글은 존재하지 않습니다.");
//			return;
//		}
		
		System.out.println();
		System.out.println( boardNo + "번 글 내용");
		System.out.println("-------------------------------------------");
		System.out.println("- 제  목 : " + boardVo.getBoard_title());
		System.out.println("- 작성자 : " + boardVo.getBoard_writer());
		System.out.println("- 내  용 : " + boardVo.getBoard_content());
		System.out.println("- 작성일 : " + boardVo.getBoard_date());
		System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
		System.out.println("-------------------------------------------");
		System.out.println(" 메뉴 : 1.수정      2.삭제      3.리스트로 가기");
		System.out.print(" 작업 선택 >> ");
		int choiceNum = scan.nextInt();
		
		switch(choiceNum){
			case 1 :	// 수정
				updateBoard(boardNo);
				break;
			case 2 :	// 삭제
				deleteBoard(boardNo);
				break;
			case 3 :	// 리스트로 가기
				return;
		}
		
	}
	
	
	// 게시글을 수정하는 메서드
	public void updateBoard(int boardNo){
		System.out.println();
		scan.nextLine(); // 버퍼 비우기
		System.out.println(" 수정 작업하기");
		System.out.println("--------------------------------------");
		System.out.print("- 제 목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 내 용 : ");
		String content = scan.nextLine();
		
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		
		int cnt = service.updateBoard(boardVo);
		
		if(cnt>0){
			System.out.println(boardNo + "번글이 수정되었습니다.");
		}else{
			System.out.println(boardNo + "번글 수정작업 실패!!!");
		}
		
	}
	
	// 게시글을 삭제하는 메서드
	public void deleteBoard(int boardNo){
		int cnt = service.deleteBoard(boardNo);
		if(cnt>0){
			System.out.println(boardNo + "번글을 삭제하였습니다.");
		}else{
			System.out.println(boardNo + "번글 삭제작업 실패!!!");
		}
	}
}






