package com.library.system.entity;

import java.util.Date;

/**
 * ͼ��黹��ʵ���� 
 * @author Administrator
 *
 */
public class BooksReturnEntity {
    private Integer id;
    private String userBorrowId;  //ͼ�������id.
    private Integer bookTypeId;   // �����鼮����
    private Integer bookId;           //�鼮id
    private  Date borrowDate;     //����ʱ��
    private  Date returnDateInfo;    //ʵ�ʹ黹ʱ�� 
    private  Integer bookStatusId;  //�鼮״̬id
    private Integer  bookBorrowId; // ������id
      
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
