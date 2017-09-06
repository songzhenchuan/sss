package org.lanqiao.entity;

import java.util.Date;

public class News {
	private String tid;
	private String title;
	private String tcontent;
	private  Date tpybdate;
	
	@Override
	public String toString() {
		return "News [tid=" + tid + ", title=" + title + ", tcontent="
				+ tcontent + ", tpybdate=" + tpybdate + "]";
	}
	public News(String tid, String title, String tcontent, Date tpybdate) {
		super();
		this.tid = tid;
		this.title = title;
		this.tcontent = tcontent;
		this.tpybdate = tpybdate;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTcontent() {
		return tcontent;
	}
	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}
	public Date getTpybdate() {
		return tpybdate;
	}
	public void setTpybdate(Date tpybdate) {
		this.tpybdate = tpybdate;
	}
	
}
