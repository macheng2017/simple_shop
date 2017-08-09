package com.spzwl.web.formbean;

public class QueryInfo {
	int currentpage = 1; // 当前页 默认1
	int pagesize = 15; // 每页多少条记录 默认 15
	int startindex; // 数据的开始记录数,需要根据当前页与每页显示记录数,自动计算,供数据库使用
	// 第1 页  每页显示 5条 从第0条  开始显示  (1-1) *5
    //   2           5     5			(2-1)*5
	//   3           5     10			(3-1)*5
	//   4           5     15		(currentpage-1)*pagesize	
	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getStartindex() {
		this.startindex = (currentpage-1)*pagesize;
		return startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}

}
