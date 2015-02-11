package com.attilax.page;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author luojun
 * 
 * Program Name:  BS坐席
 *
 * Description: 分页对象,主要用于 与ligerUI 对接分页, 
 * 
 * ligerUI 分页自带参数page(页码),pagesize(每页条数),sortname(排序方式)
 * 
 * CreateTime: 2014-6-26  上午10:41:37
 *  
 *
 */
public class Pager<T> {
	private static final int DEFAULT_PAGE_SIZE = 10; //默认每页显示条数

	private static final int DEFAULT_PAGE = 1;//当前页数

	/**
	 * 构造函数
	 */
	public Pager() {
		this.currentPage = DEFAULT_PAGE;
		this.pageSize = DEFAULT_PAGE_SIZE;
	}
	
	/**
	 * 构造函数(带参数)
	 * @param currentPage 当前页码
	 * @param pagesize 每页条数
	 */
	public Pager(Integer currentPage, Integer pagesize) {  
	        this.currentPage = currentPage;  
	        this.pageSize = pagesize;  
	}  

	/**
	 * 当前页码
	 */
	private Integer currentPage;
	/**
	 * 总页数
	 */
	private Integer totalPage;
	/**
	 * 每页条数
	 */
	private Integer pageSize;
	/**
	 * 总条数
	 */
	private Integer total = 0;
	/**
	 * 排序
	 */
	private String sortName;

	/**
	 * 分页起始数据条数
	 */
	private Integer firstResult;

	/**
	 * 分页最大数据条数
	 */
	private Integer maxResults;
	
	private List<T> data;

	public static Pager initPager(Map<String, Object> parameters) {
		Pager pager = new Pager();
		if (parameters.containsKey("currentPage")) {
			pager.setCurrentPage(new Integer(parameters.get("currentPage").toString()));
		}
		if (parameters.containsKey("pageSize")) {
			pager.setPagesize(new Integer(parameters.get("pageSize").toString()));
		}
		if (parameters.containsKey("sortName")) {
			pager.setSortName(parameters.get("sortName").toString());
		}
		return pager;
	}
 

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPagesize(Integer pageSize) {
		this.pageSize = pageSize;
	}
 

 

	public Integer getFirstResult() {
		firstResult = (currentPage - 1) <= 0 ? 0 : (currentPage - 1) * pageSize;
		return firstResult;
	}

	public Integer getMaxResults() {
		//maxResults = currentPage <= 0 ? 0 : currentPage * pageSize;
		return pageSize;
	}
 

	public Integer getTotalPage() {
		totalPage = (this.total + this.pageSize -1) / this.pageSize;
		return totalPage; 
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
