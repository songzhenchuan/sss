package org.lanqiao.entity;

public class User {
	private String userid;
	private String uemail;
	private String uloginid;
	private String upassword;
	private String usex;
	private String uaddress;
	private String utel;
	private String uroleid;
	private String ustateid;
	public User() {
		super();
	}
	
	public User(String userid, String uemail, String uloginid,
			String upassword, String usex, String uaddress, String utel,
			String ustateid, String uroleid) {
		super();
		this.userid = userid;
		this.uemail = uemail;
		this.uloginid = uloginid;
		this.upassword = upassword;
		this.usex = usex;
		this.uaddress = uaddress;
		this.utel = utel;
		this.uroleid = uroleid;
		this.ustateid = ustateid;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUloginid() {
		return uloginid;
	}
	public void setUloginid(String uloginid) {
		this.uloginid = uloginid;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUroleid() {
		return uroleid;
	}
	public void setUroleid(String uroleid) {
		this.uroleid = uroleid;
	}
	public String getUstateid() {
		return ustateid;
	}
	public void setUstateid(String ustateid) {
		this.ustateid = ustateid;
	}
	
}
