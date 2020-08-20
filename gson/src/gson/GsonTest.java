package gson;

import com.google.gson.*;
import java.util.*;

public class GsonTest {
	
	private int age;
	private String name;
	
	public GsonTest(int age, String name) {
		this.age=age;
		this.name=name;
	}
	
	public void getInfo()
	{
		System.out.println("이름은 >> " + this.name + " 이고, 나이는 >> " + this.age + " 입니다.");
	}
	
	public static void main(String[] args) {
		String jsonString = "{'age':20, 'name':'lsj'}";
		
		Gson gson = new GsonBuilder().create();
		GsonTest std = gson.fromJson(jsonString, GsonTest.class);
		
		std.getInfo();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

//	public static void main(String[] args) {
//		Weather w = new Weather();
//		w.setLoc("busan");
//		w.setTemp("10");
//		w.setDate("20200224");
//		
//		Weather w1 = new Weather();
//		w1.setLoc("seoul");
//		w1.setTemp("19");
//		w1.setDate("20200225");
//		
//		ArrayList<Weather> list = new ArrayList<Weather>();
//		list.add(w);
//		list.add(w1);
//		
//		System.out.println(list.toString());
//}
//}
//
//
//
//
//
//
//class Weather{
//	private String loc;
//	private String temp;
//	private String date;
//	
//	public String getLoc() {
//		return loc;
//	}
//	public void setLoc(String loc) {
//		this.loc = loc;
//	}
//	public String getTemp() {
//		return temp;
//	}
//	public void setTemp(String temp) {
//		this.temp = temp;
//	}
//	public String getDate() {
//		return date;
//	}
//	public void setDate(String date) {
//		this.date = date;
//	}	
//	@Override
//	public String toString() {
//		return "Weather [loc=" + loc + ", temp=" + temp + ", date=" + date
//				+ "]";
//	}
//}

