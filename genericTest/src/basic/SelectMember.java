package basic;

public class SelectMember {

	public static void main(String[] args) {
		String[][]names = {
			{"김종완", "강현철", "이혁진", "김준우", "박기완"},
			{"박선정", "연지은", "송효진", "심주영"},
			{"정영수", "홍종욱", "김철희", "정주환"},
			{"이제경", "전다희", "김우경", "남아름"},
			{"임수진", "박혜진", "김태유", "박정민"}
		};
		
		for(int i=0; i<names.length; i++){
			int rnd = (int)(Math.random() * names[i].length);
			System.out.println((i+1) + "조 발표자 : " + names[i][rnd]);
		}

	}

}
