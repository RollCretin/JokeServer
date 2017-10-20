package com.cretin.po.vo;

import java.util.List;

/**
*<p>Title: JokesQueryVo </p>
*<p>Description: </p>
*<p>Company: www.cretin.com </p> 
* @author cretin
* @date 2017年10月20日
*/
public class JokesQueryVo<T> {
	//当前页
	private int page;
	//数据总条数
	private int totalCount;
	//总页数
	private int totalPage;
	//每次返回的数据长度
	private int limit;
	//返回的数据
	private List<T> jokesList;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getJokesList() {
		return jokesList;
	}
	public void setJokesList(List<T> jokesList) {
		this.jokesList = jokesList;
	}
	
}
