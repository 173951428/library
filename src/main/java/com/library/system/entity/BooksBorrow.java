package com.library.system.entity;

import java.util.Date;

// 图书借阅表 
public class BooksBorrow {
    private Integer id;
    private String userBorrowId;  //图书借阅人id.
    private Integer bookTypeId;   // 借阅书籍类型
    private Integer bookId;           //书籍id
    private  Date borrowDate;     //借阅时间
    private  Date returnTime;    //应该归还时间
    private  Integer bookStatusId;  //书籍状态id 
	
	public String getUserBorrowId() {
		return userBorrowId;
	}
	public void setUserBorrowId(String userBorrowId) {
		this.userBorrowId = userBorrowId;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	public Integer getBookStatusId() {
		return bookStatusId;
	}
	public void setBookStatusId(Integer bookStatusId) {
		this.bookStatusId = bookStatusId;
	}
	

}
