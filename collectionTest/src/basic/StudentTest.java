package basic;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
 * 문제)
	학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
	이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 처리한다.
	
	이 Student객체들은 List에 저장하여 관리한다.
	
	List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분(내부 정렬기준)과
	총점의 역순으로 정렬하는 부분(외부 정렬기준)을 프로그램하시오.
	(그리고, 총점이 같으면 이름의 내림차순으로 정렬되도록 한다.)

*/
public class StudentTest {
	
	// 등수를 구하는 메서드
	public void setRanking(List<Student> stdList){
		for(Student std : stdList){   // 기준값을 찾기 위한 반복문
			int rank = 1;	// 처음 등수는 1등으로 초기화
			for(Student std2 : stdList){	// 비교 대상을 찾기 위한 반복문
				if(std.getTot() < std2.getTot()){  // 기준보다 큰 값이 있으면 rank를 증가시킨다.
					rank++;
				}
			}
			
			// 구해진 등수를 셋팅한다.
			std.setRank(rank);
		}
	}
	

	public static void main(String[] args) {
		StudentTest stdTest = new StudentTest();
		
		LinkedList<Student> stdList = new LinkedList<>();
		
		stdList.add(new Student(1, "홍길동", 90, 95, 80));
		stdList.add(new Student(3, "성춘향", 90, 75, 70));
		stdList.add(new Student(7, "강감찬", 95, 95, 80));
		stdList.add(new Student(5, "변학도", 80, 95, 90));
		stdList.add(new Student(2, "일지매", 100, 85, 80));
		stdList.add(new Student(4, "이순신", 60, 65, 60));
		stdList.add(new Student(6, "이몽룡", 90, 100, 90));
		
		// 등수를 구하는 메서드 호출
		stdTest.setRanking(stdList);
		
		System.out.println("정렬전...");
		for(Student std : stdList){
			System.out.println(std);
		}
		System.out.println();
		
		// 학번의 오름차순 정렬 (내부 정렬 기준 사용)
		Collections.sort(stdList);
		
		System.out.println("학번의 오름차순 정렬후...");
		for(Student std : stdList){
			System.out.println(std);
		}
		System.out.println();
		
		// 총점의 역순(총점이 같으면 이름의 내림차순) 정렬 - 외부 정렬 기준 사용
		Collections.sort(stdList, new SortByTotal());
		
		System.out.println("총점의 역순 정렬후...");
		for(Student std : stdList){
			System.out.println(std);
		}
		System.out.println();
		
		
		
	}

}


// 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수
class Student implements Comparable<Student>{
	private int stdNum;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private int rank;
	
	// 생성자
	public Student(int stdNum, String name, int kor, int eng, int mat) {
		super();
		this.stdNum = stdNum;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.tot = kor + eng + mat;
	}

	public int getStdNum() {
		return stdNum;
	}

	public void setStdNum(int stdNum) {
		this.stdNum = stdNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [stdNum=" + stdNum + ", name=" + name + ", kor=" + kor
				+ ", eng=" + eng + ", mat=" + mat + ", tot=" + tot + ", rank="
				+ rank + "]";
	}

	// 학번의 오름차순 정렬이 되도록 설정
	@Override
	public int compareTo(Student std) {
		return Integer.compare(this.getStdNum(), std.getStdNum());
	}
	
}


// 외부 정렬 기준 클래스 작성 => 총점의 역순 (총점이 같으면 이름의 내림차순)
class SortByTotal implements Comparator<Student>{
	@Override
	public int compare(Student std1, Student std2) {
		if(std1.getTot() == std2.getTot()){  // 총점이 같을 때
			return std1.getName().compareTo(std2.getName()) * -1;
		}else{
			return Integer.compare(std1.getTot(), std2.getTot()) * -1;
		}
	}
}











