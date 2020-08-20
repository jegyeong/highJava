package basic;

import java.util.HashSet;

public class EqualsHashCodeTest {

	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("일지매");
		
		Person p2 = new Person();
		p2.setId(1);
		p2.setName("일지매");
		
		Person p3 = new Person();
		p3.setId(1);
		p3.setName("홍길동");
		
		
		//System.out.println(p1==p2);
		System.out.println(p1.equals(p2));
		System.out.println(p1.equals(p3));
		
		
		HashSet<Person> pSet = new HashSet<>();
		pSet.add(p1);
		pSet.add(p2);
		
		System.out.println("size => " + pSet.size());
		

	}

}

/*
	- equals()메서드는 두 객체의 내용이 같은지 여부를 검사하는 메서드이고,
	- hashCode()메서드는 두 객체가 같은 객체인지 여부를 검사하는 메서드 이다.
	
	- HashSet, HashMap, HashTable등과 같은 객체들은 객체의 의미상 동등성을 비교하기 위해 hashCode()메서드를
	   호출해서 비교한다.
	   그러므로, 객체가 같은지 여부를 결정하려면 hashCode()메서드를 재정의 해야 한다.
	- hashCode()메서드에서는 '해싱 알고리즘'을 사용해서 값을 만든다.
	  hashCode() 메서드에서 사용되는 기본적인 '해싱 알고리즘'은 객체의 참조값을 기반으로 값을 만든다.
	   

*/
class Person{
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
	/*
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		
		if(this.getClass() != obj.getClass()){
			return false;
		}
		
		if(this==obj){
			return true;
		}
		
		Person temp = (Person) obj;
		if(this.name == null && temp.name != null){
			return false;
		}
		if(this.id==temp.id && this.name == temp.name){
			return true;
		}
		if(this.id==temp.id && this.name.equals(temp.name)){
			return true;
		}
		
		return false;
	}
	*/
	
}
