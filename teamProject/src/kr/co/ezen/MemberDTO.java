package kr.co.ezen;

import java.io.Serializable;

public class MemberDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String email;
	private String pw;
	private String birth;
	private int tel;
	private String address;
	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public MemberDTO(String id, String name, String email, String pw, String birth, int tel, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pw = pw;
		this.birth = birth;
		this.tel = tel;
		this.address = address;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPw() {
		return pw;
	}




	public void setPw(String pw) {
		this.pw = pw;
	}




	public String getBirth() {
		return birth;
	}




	public void setBirth(String birth) {
		this.birth = birth;
	}




	public int getTel() {
		return tel;
	}




	public void setTel(int tel) {
		this.tel = tel;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
