package org.lanqiao.entity;

public class PasswordAnswer {
	private String answerid;
	private String userid;
	private String aquestion;
	private String answer;
	private String email;
	
	public PasswordAnswer(String answerid, String userid, String aquestion,
			String answer, String email) {
		super();
		this.answerid = answerid;
		this.userid = userid;
		this.aquestion = aquestion;
		this.answer = answer;
		this.email = email;
	}
	public PasswordAnswer() {
		super();
	}
	public String getAnswerid() {
		return answerid;
	}
	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAquestion() {
		return aquestion;
	}
	public void setAquestion(String aquestion) {
		this.aquestion = aquestion;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
