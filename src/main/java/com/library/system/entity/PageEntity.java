package com.library.system.entity;
import java.util.List;
/**
 * 分页的基类 
 * @author lisujie
 *
 */
public class PageEntity extends BaseEntity{
	/**
	 * 默认当前页
	 */
	public static Integer CURRENT_PAGE = 1;
	/**
	 * 默认页面条数
	 */
	public static Integer PAGE_NUMBER = 10;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6597611703334828021L;
	/**
	 * 页面数据
	 */
	private List<?> pageList;
	/**
	 * 每页显示条数
	 */
	private int pageNumber;
	/**
	 * 总条数
	 */
	private int totalNumber;
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 第一页
	 */
	private int firstPage = 1;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 上一页
	 */
	private int upperPage;
	/**
	 * 下一页
	 */
	private int nextPage;
	
	/**
	 * 数据库中limit的参数，从第几条开始取
	 */
	private int dbIndex;
	/**
	 * 数据库中limit的参数，一共取多少条
	 */
	private int dbNumber;
	
	/**
	 * 根据当前对象中属性值计算并设置相关属性值
	 */
	public void count() {
		// 计算总页数
		int totalPageTemp = this.totalNumber / this.pageNumber;
		int plus = (this.totalNumber % this.pageNumber) == 0 ? 0 : 1;
		totalPageTemp = totalPageTemp + plus;
		if(totalPageTemp <= 0) {
			totalPageTemp = 1;
		}
		this.totalPage = totalPageTemp;
		
		// 设置当前页数
		// 总页数小于当前页数，应将当前页数设置为总页数
		if(this.totalPage < this.currentPage) {
			this.currentPage = this.totalPage;
		}
		// 当前页数小于1设置为1
		if(this.currentPage < 1) {
			this.currentPage = 1;
		}
		
		// 判断下一页
		nextPage = currentPage+1;
		if(nextPage > totalPage){
			nextPage = totalPage;
		}
		// 判断上一页
		upperPage = currentPage-1;
		if(upperPage < 1){
			upperPage = 1;
		}
		
		// 设置limit的参数
		this.dbIndex = (this.currentPage - 1) * this.pageNumber;
		this.dbNumber = this.pageNumber;
	}

	public List<?> getPageList() {
		return pageList;
	}

	public void setPageList(List<?> pageList) {
		this.pageList = pageList;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
		//this.count();
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
		this.count();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getUpperPage() {
		return upperPage;
	}

	public void setUpperPage(int upperPage) {
		this.upperPage = upperPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	public int getDbNumber() {
		return dbNumber;
	}

	public void setDbNumber(int dbNumber) {
		this.dbNumber = dbNumber;
	}
}
