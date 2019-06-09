package com.library.system.entity;

import java.util.Date;

/**
 * 图书归还表实体类 
 * @author Administrator
 *
 */
public class BooksReturnEntity {
    private Integer id;
    private String userBorrowId;  //图书借阅人id.
    private Integer bookTypeId;   // 借阅书籍类型
    private Integer bookId;           //书籍id
    private  Date borrowDate;     //借阅时间
    private  Date returnDateInfo;    //实际归还时间 
    private  Integer bookStatusId;  //书籍状态id
    private Integer  bookBorrowId; // 借书表的id
      
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserBorrowId() {
		return userBorrowId;
	}
	public void setUserBorrowId(String userBorrowId) {
		this.userBorrowId = userBorrowId;
	}
	public Integer getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	public Integer getBookStatusId() {
		return bookStatusId;
	}
	public void setBookStatusId(Integer bookStatusId) {
		this.bookStatusId = bookStatusId;
	}
	public Date getReturnDateInfo() {
		return returnDateInfo;
	}
	public void setReturnDateInfo(Date returnDateInfo) {
		this.returnDateInfo = returnDateInfo;
	}
	public Integer getBookBorrowId() {
		return bookBorrowId;
	}
	public void setBookBorrowId(Integer bookBorrowId) {
		this.bookBorrowId = bookBorrowId;
	}
}
