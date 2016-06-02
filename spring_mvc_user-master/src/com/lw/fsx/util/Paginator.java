package com.lw.fsx.util;

public class Paginator {
	// 当前页
	private int currPage = 1;
	// 总页数
	private int totalPage = 0;
	// 总记录数
	private int totalCount = 0;
	// 每页多少个
	private int pageSize = 0;

	public Paginator() {
	}

	public Paginator(int currPage, int pageSize, int totalCount) {
		this.currPage = currPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.countPage();
		// 做安全检查 当前页是不能大于总页数的 如果大于 做自动处理
		if (this.currPage > this.totalPage)
			this.currPage = this.totalPage;
		// 做安全检查 当前页最小就是1不能在也1小了 做自动处理
		if (this.currPage < 1)
			this.currPage = 1;
	}

	private void countPage() {
		if (this.totalCount % this.pageSize == 0) {
			this.totalPage = this.totalCount / this.pageSize;
		} else {
			this.totalPage = this.totalCount / this.pageSize + 1;
		}
	}

	/**
	 * 看是否可以向前翻页
	 * 
	 * @param void
	 * @return boolean
	 */
	public boolean has_previous() {
		if (this.currPage <= 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 看是否可以向后翻页
	 * 
	 * @param void
	 * @return boolean
	 */
	public boolean has_next() {
		if (this.currPage != this.totalPage && this.totalPage != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 上一页的页数
	 * 
	 * @param void
	 * @return int
	 */
	public int previous_page_number() {
		if (this.currPage > 1)
			return this.currPage - 1;
		else
			return 1;
	}

	/**
	 * 下一页的页数
	 * 
	 * @param void
	 * @return int
	 */
	public int next_page_number() {
		if (this.currPage < this.totalPage)
			return this.currPage + 1;
		else
			return this.totalPage;
	}

	public String toHtmlPageStyle(String url, String condition) {
		if (condition.length() > 0) {
			if (!condition.substring(0, 1).equals("_")) {
				condition = "_" + condition;
			}
		}
		StringBuffer sn = new StringBuffer();
		sn.append("共&nbsp;" + this.getTotalCount() + "&nbsp;个");
		if (this.has_previous()) {
			sn.append("<a href=\"" + url + "/1" + condition
					+ "\">&nbsp;&nbsp;首页&nbsp;&nbsp;</a>");
			sn.append("<a href=\"" + url + "/" + this.previous_page_number()
					+ condition + "\">上一页</a>");
		} else {
			sn.append("&nbsp;&nbsp;首页&nbsp;&nbsp;");
			sn.append("上一页");
		}
		if (this.has_next()) {
			sn.append("<a href=\"" + url + "/" + this.next_page_number()
					+ condition + "\">&nbsp;&nbsp;下一页&nbsp;&nbsp;</a>");
			sn.append("<a href=\"" + url + "/" + this.getTotalPage()
					+ condition + "\">尾页</a>");
		} else {
			sn.append("&nbsp;&nbsp;下一页&nbsp;&nbsp;");
			sn.append("尾页");
		}
		sn.append("&nbsp;&nbsp;当前第&nbsp;" + this.getCurrPage()
				+ "&nbsp;页&nbsp;/&nbsp;共&nbsp;" + this.getTotalPage()
				+ "&nbsp;页.");
		return sn.toString();
	}
	/**
	 * 传？号传值的模式
	 * 通用分页方法
	 * @param url
	 * @param condition
	 * @return 分页html
	 */
	public String toHtmlPageStyleT(String url,String condition){
		StringBuffer sn = new StringBuffer();
		sn.append("<div class=\"mfy\"><h3><ul><li>共"+this.getTotalCount()+"条</li>");
		if(this.has_previous()){
			sn.append("<li><a href=\""+url+"?currPage=1"+condition+"\">首页</a></li>");
			sn.append("<li><a href=\""+url+"?currPage="+this.previous_page_number()+condition+"\">上一页</a></li>");
		}else{
			sn.append("<li><a href=\"javascript:void(0)\">首页</a></li>");
			sn.append("<li><a href=\"javascript:void(0)\">上一页</a></li>");
		}
		if(this.has_next()){
			sn.append("<li><a href=\""+url+"?currPage="+this.next_page_number()+condition+"\">下一页</a></li>");
			sn.append("<li><a href=\""+url+"?currPage="+this.getTotalPage()+condition+"\">尾页</a></li>");
		}else{
			sn.append("<li><a href=\"javascript:void(0)\">下一页</a></li>");
			sn.append("<li><a href=\"javascript:void(0)\">尾页</a></li>");
		}
		sn.append("<li>当前第"+this.getCurrPage()+"页/共"+this.getTotalPage()+"页.</li>");
		sn.append("</ul></h3>");
		/*String temp = condition.substring(1, condition.length());
		System.out.println(url+"?" +temp);
		
		sn.append("<form  action=" +"\'" + url+"?" +temp+ "\'" + " method=\'get\'><cite>");
		sn.append("跳转到第<SELECT size=1  id=currPage name=currPage  onchange='this.form.submit();' style=WIDTH:40px>");
		   for (int i = 1; i < totalPage + 1; i++) {
		    if (i == this.getCurrPage()) {
		    	sn.append("<OPTION value=" + i + " selected>" + i + "</OPTION>");
		    }else {
		    	sn.append("<OPTION value=" + i + ">" + i + "</OPTION>");
		    }
		   }
		   sn.append("</SELECT>" + "页");
		   sn.append("</cite></form><div>");*/
		 sn.append("<div>");
		
		return sn.toString();
	}
	/**
	 * post onclick
	 * 传？号传值的模式
	 * 通用分页方法
	 * @param url
	 * @param condition
	 * @return 分页html
	 */
	public String toHtmlPageStyleOnclick(String url){
		StringBuffer sn = new StringBuffer();
		sn.append("<div class=\"mfy\"><h3><ul><li>共"+this.getTotalCount()+"条</li>");
		if(this.has_previous()){
			sn.append("<li><a href=\"#\" onclick=\"onOnclick('"+url+"','1')\">首页</a></li>");
			sn.append("<li><a href=\"#\" onclick=\"onOnclick('"+url+"','"+this.previous_page_number()+"')\">上一页</a></li>");
		}else{
			sn.append("<li><a href=\"javascript:void(0)\">首页</a></li>");
			sn.append("<li><a href=\"javascript:void(0)\">上一页</a></li>");
		}
		if(this.has_next()){
			sn.append("<li><a href=\"#\" onclick=\"onOnclick('"+url+"','"+this.next_page_number()+"')\">下一页</a></li>");
			sn.append("<li><a href=\"#\" onclick=\"onOnclick('"+url+"','"+this.getTotalPage()+"')\">尾页</a></li>");
		}else{
			sn.append("<li><a href=\"javascript:void(0)\">下一页</a></li>");
			sn.append("<li><a href=\"javascript:void(0)\">尾页</a></li>");
		}
		sn.append("<li>当前第"+this.getCurrPage()+"页/共"+this.getTotalPage()+"页.</li>");
		sn.append("</ul></h3>");
		sn.append("跳转到第<SELECT size=1  id=currPage name=currPage  onchange=\"onOnclick('"+url+"',this.value)\" style=WIDTH:40px>");
		   for (int i = 1; i < totalPage + 1; i++) {
		    if (i == this.getCurrPage()) {
		    	sn.append("<OPTION value=" + i + " selected>" + i + "</OPTION>");
		    }else {
		    	sn.append("<OPTION value=" + i + ">" + i + "</OPTION>");
		    }
		   }
		   sn.append("</SELECT>" + "页");
		   sn.append("</cite><div>");
		
		return sn.toString();
	}
	/**
	 * 传？号传值的模式
	 * @param url
	 * @param condition
	 * @return
	 */
	public String toHtmlPageStyleyy(String url,String condition){
		StringBuffer sn = new StringBuffer();
		sn.append("<div class=\"mfy\"><h3><ul><li>共"+this.getTotalCount()+"条</li>");
		if(this.has_previous()){
			sn.append("<li><a href=\""+url+"?currPage=1"+condition+"\">首页</a></li>");
			sn.append("<li><a href=\""+url+"?currPage="+this.previous_page_number()+condition+"\">上一页</a></li>");
		}else{
			sn.append("<li><a href=\"javascript:void(0)\">首页</a></li>");
			sn.append("<li><a href=\"javascript:void(0)\">上一页</a></li>");
		}
		if(this.has_next()){
			sn.append("<li><a href=\""+url+"?currPage="+this.next_page_number()+condition+"\">下一页</a></li>");
			sn.append("<li><a href=\""+url+"?currPage="+this.getTotalPage()+condition+"\">尾页</a></li>");
		}else{
			sn.append("<li><a href=\"javascript:void(0)\">下一页</a></li>");
			sn.append("<li><a href=\"javascript:void(0)\">尾页</a></li>");
		}
		sn.append("<li>当前第"+this.getCurrPage()+"页/共"+this.getTotalPage()+"页.</li>");
		sn.append("</ul></h3>");
		sn.append("<form  action=" +"\'" + url + "\'" + " method=\'get\'><cite>");
		String[] str = condition.split("=");
		sn.append("<input type=\"hidden\" value=\""+str[1]+"\" name=\"itemId\" id=\"itemId\">");
		sn.append("跳转到第<SELECT size=1  id=currPage name=currPage  onchange='this.form.submit();' style=WIDTH:40px>");
		   for (int i = 1; i < totalPage + 1; i++) {
		    if (i == this.getCurrPage()) {
		    	sn.append("<OPTION value=" + i + " selected>" + i + "</OPTION>");
		    }else {
		    	sn.append("<OPTION value=" + i + ">" + i + "</OPTION>");
		    }
		   }
		   sn.append("</SELECT>" + "页");
		   sn.append("</cite></form><div>");
		
		return sn.toString();
	}
	/**
	 * 这个是ajax的分页方法 只传入掉用的js的函数名称（因为各个用的js框架不同所以函数写在页面中为最佳）
	 * 
	 * @param url
	 * @return String
	 */
	public String toAjaxHtmlPageStyle(String url) {
		StringBuffer sn = new StringBuffer();
		sn.append("共&nbsp;" + this.getTotalCount() + "&nbsp;个");
		if (this.has_previous()) {
			sn.append("<a href=\"javascript:" + url
					+ "('1');\">&nbsp;&nbsp;首页&nbsp;&nbsp;</a>");
			sn.append("<a href=\"javascript:" + url + "('"
					+ this.previous_page_number() + "');\">上一页</a>");
		} else {
			sn.append("&nbsp;&nbsp;首页&nbsp;&nbsp;");
			sn.append("上一页");
		}
		if (this.has_next()) {
			sn.append("<a href=\"javascript:" + url + "('"
					+ this.next_page_number()
					+ "');\">&nbsp;&nbsp;下一页&nbsp;&nbsp;</a>");
			sn.append("<a href=\"javascript:" + url + "('"
					+ this.getTotalPage() + "');\">尾页</a>");
		} else {
			sn.append("&nbsp;&nbsp;下一页&nbsp;&nbsp;");
			sn.append("尾页");
		}
		sn.append("&nbsp;&nbsp;当前第&nbsp;" + this.getCurrPage()
				+ "&nbsp;页&nbsp;/&nbsp;共&nbsp;" + this.getTotalPage()
				+ "&nbsp;页.");
		return sn.toString();
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
