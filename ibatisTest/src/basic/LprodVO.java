package basic;

public class LprodVO {
	private int lprod_id;
	private String lprod_gu;
	private String lprod_nm;
	
	// iBatis에서 사용되는 VO객체에 임의의 생성자를 만들게 되면
	// 반드시 기본 생성자도 만들어 줘야 한다.
	// 이유 : iBatis는 기본 생성자로 객체를 생성해서 사용하기 때문에...
	
	public int getLprod_id() {
		return lprod_id;
	}
	public void setLprod_id(int lprod_id) {
		this.lprod_id = lprod_id;
	}
	public String getLprod_gu() {
		return lprod_gu;
	}
	public void setLprod_gu(String lprod_gu) {
		this.lprod_gu = lprod_gu;
	}
	public String getLprod_nm() {
		return lprod_nm;
	}
	public void setLprod_nm(String lprod_nm) {
		this.lprod_nm = lprod_nm;
	}
	
}
