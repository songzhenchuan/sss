package org.lanqiao.entity;

public class KeyWord {
	private String key;
	private String desc;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public KeyWord(String key, String desc) {
		super();
		this.key = key;
		this.desc = desc;
	}
	
	public KeyWord() {
		// TODO Auto-generated constructor stub
	}
}
