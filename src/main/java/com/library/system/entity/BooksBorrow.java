package com.library.system.entity;

import java.util.Date;

// ͼ����ı� 
public class BooksBorrow {
    private Integer id;
    private String userBorrowId;  //ͼ�������id.
    private Integer bookTypeId;   // �����鼮����
    private Integer bookId;           //�鼮id
    private  Date borrowDate;     //����ʱ��
    private  Date returnTime;    //Ӧ�ù黹ʱ��
    private  Integer bookStatusId;  //�鼮״̬id 
	
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
