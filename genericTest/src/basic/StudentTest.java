package basic;

/*
  학생이름과 여러과목의 점수를 매개변수로 받아서 그 학생의 평균과 평점을 구한 후
  학생이름, 각과목 점수, 평균, 평점을 출력하는 메서드를 만드시오.
 (각 학생별로 시험 과목수가 일정하지 않다.)
*/
public class StudentTest {
	public void calcScore(String name, int...scores){
		int tot = 0;
		for(int i=0; i<scores.length; i++){
			tot += scores[i];
		}
		
		double avg = (double)tot / scores.length;
		String g = "";
		if(avg>=90){
			g = "A";
		}else if(avg>=80){
			g = "B";
		}else if(avg>=70){
			g = "C";
		}else if(avg>=60){
			g = "D";
		}else {
			g = "F";
		}
		
		System.out.print(name + "\t");
		for(int score : scores){
			System.out.print(score + "\t");
		}
		System.out.println(avg + "\t" + g);
		
		
	}

	public static void main(String[] args) {
		StudentTest test = new StudentTest();
		
		test.calcScore("홍길동", 100, 60, 90, 67, 45);
		test.calcScore("홍길서", 90, 60, 90, 67);
		test.calcScore("홍길남", 100, 60, 90, 67, 45, 60);
		test.calcScore("홍길부", 100, 60, 90, 67, 45, 90, 90, 90);
		

	}

}
