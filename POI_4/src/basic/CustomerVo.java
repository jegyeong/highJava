package basic;

public class CustomerVo {
 
    private String custId;        //고객ID
    private String custName;    //고객명
    private String custAge;    //고객나이
    private String custEmail;    //고객이메일
    
    public CustomerVo() {
		super();
	}

	// 생성자
    public CustomerVo(String custId, String custName, String custAge,String custEmail) {
        super();
        this.custId = custId;
        this.custName = custName;
        this.custAge = custAge;
        this.custEmail = custEmail;
    }
    
    public String getCustId() {
        return custId;
    }
    public void setCustId(String custId) {
        this.custId = custId;
    }
    
    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    
    public String getCustAge() {
        return custAge;
    }
    public void setCustAge(String custAge) {
        this.custAge = custAge;
    }
    
    public String getCustEmail() {
        return custEmail;
    }
    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("ID : " + custId);
        sb.append("\tNAME : " + custName);
        sb.append("\tAGE : " + custAge);
        sb.append("\tEMAIL : " + custEmail);
        return sb.toString();
    }
}


