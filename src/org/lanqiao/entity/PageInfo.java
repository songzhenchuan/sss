package org.lanqiao.entity;

import java.util.ArrayList;
import java.util.List;

public class PageInfo<T>{
	private int pagesize;
	private int pageindex;
	private int totalnumber;
	private int totalpage;
	private boolean isfirstpage;
	private boolean islastpage;
	private List<T> data =new ArrayList<T>();
	
	
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPageindex() {
		return pageindex;
	}
	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}
	public int getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(int totalnumber) {
		this.totalnumber = totalnumber;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public boolean isIsfirstpage() {
		return isfirstpage;
	}
	public void setIsfirstpage(boolean isfirstpage) {
		this.isfirstpage = isfirstpage;
	}
	public boolean isIslastpage() {
		return islastpage;
	}
	public void setIslastpage(boolean islastpage) {
		this.islastpage = islastpage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	

}
