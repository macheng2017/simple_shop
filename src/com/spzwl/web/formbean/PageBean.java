package com.spzwl.web.formbean;

import java.util.List;

import com.spzwl.admin.custromer.domain.Upfile;

public class PageBean {
	private List<Upfile> list; // 分页记录集(查询)
	private int totalrecord; // 总记录数(查询)
	private int pagesize; // 页面大小(用户)
	private int currentpage; // 当前页数(用户)
	private int totalpage; // 总页数(计算) totalrecord%pagesize==0 ? totalrecord/pagesize:totalrecord/pagesize+1
	private int previouspage; // 前一页页码(计算)(currentpage-1)
	private int nextpage; // 向后一页页码(计算)
	private int pagebar[]; // 页码条 (计算)

	public List<Upfile> getList() {
		return list;
	}

	public void setList(List<Upfile> list) {
		this.list = list;
	}

	public int getTotalrecord() {
		
		return totalrecord;
	}

	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalpage() {
		totalpage = totalrecord%pagesize==0 ? totalrecord/pagesize:totalrecord/pagesize+1;
		return totalpage;
	}
	
	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getPreviouspage() {
		//需要考虑极值问题
//		if(currentpage-1<=0){
//			previouspage=0;
//		}else{
//			previouspage = currentpage-1;
//		}
		previouspage = currentpage-1<=0?1: currentpage-1;
		return previouspage;
	}

	public int getNextpage() {
		
	/*	if(currentpage+1>=totalpage){
			nextpage=totalpage;
		}else{
			nextpage = currentpage+1;
		}*/
		nextpage = currentpage+1>=totalpage?totalpage:currentpage+1;
		return nextpage;
	}


	//页码条设计  
	/***
	 * 1,2,3,4,5,6,7,8,9,10 2,3...11 3,4.....12
		 在分页数量比较少的时候可以一次性的 罗列出来,但是页面比较多的时候就不可行了
		 先将页码条的长度定为10
		 当前页总是在页码条的中间位置首需要确定页码条的开始位置与结束位置
		 先定义两个变量 分别记录页码条的开始和结束位置
		startbar endbar;
	 **/
	public int[] getPagebar(){
		int startbar=0;
		int endbar=0;
		int [] pagebar = null;
		// 这里分为两种情况,这两种情况产生的数组长度不一样
				// 1.当总页数小于10的时候,这个时候当前页无论在哪个位置整个页码条都不会变动
		if(totalpage<=10){
			startbar = 1;
			endbar = this.totalpage;
			pagebar = new int[endbar];
					
			// 2. 当总页数大于10的时候,需要考虑startbar,endbar与currentpage之间的相对位置
		}else if(totalpage>10){
			startbar = currentpage-4;
			endbar = currentpage+5;
			if(startbar<=1){
				startbar=1;
				endbar=10;
			}else if(endbar >= totalpage){
				startbar = totalpage-9;
				endbar = totalpage; 
			}
			pagebar = new int[10];
		}
		int b =0;
		for(int i=startbar;i<=endbar;i++){
			pagebar[b++] = i;
		}
		
		return pagebar;
	}

	

}
