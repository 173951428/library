package com.library.system.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 图书管理实体类
 * @author Administrator
 *
 */
public class BookManageEntity {
	
	private Integer id;
	private String bookNo;					//图书编号
	private String  bookName;				// 书名
	private String  bookAuthor;				//作者
	private String  publishingHouse;        //出版社
	private Date    bookOutTime;			// 出版时间
	private String  bookInfomation;			//图书详情
	private String  bookShelf;				//书架位置
	private Integer bookTypeId;				//图书类型id
	private Integer bookCount;				//库存数量
	private String  bookImgUrl;				//书籍图片存放路径
	private Integer bookOutSum;				//图书借阅总数
	private Timestamp    bookIntTime;				// 图书录入本地系统的时间
	
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public Date getBookOutTime() {
		return bookOutTime;
	}
	public void setBookOutTime(Date bookOutTime) {
		this.bookOutTime = bookOutTime;
	}
	public String getBookInfomation() {
		return bookInfomation;
	}
	public void setBookInfomation(String bookInfomation) {
		this.bookInfomation = bookInfomation;
	}
	public Integer getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	public Integer getBookCount() {
		return bookCount;
	}
	public void setBookCount(Integer bookCount) {
		this.bookCount = bookCount;
	}
	public String getBookImgUrl() {
		return bookImgUrl;
	}
	public void setBookImgUrl(String bookImgUrl) {
		this.bookImgUrl = bookImgUrl;
	}
	public Integer getBookOutSum() {
		return bookOutSum;
	}
	public void setBookOutSum(Integer bookOutSum) {
		this.bookOutSum = bookOutSum;
	}
	public String getBookShelf() {
		return bookShelf;
	}
	public void setBookShelf(String bookShelf) {
		this.bookShelf = bookShelf;
	}
	public Timestamp getBookIntTime() {
		return bookIntTime;
	}
	public void setBookIntTime(Timestamp bookIntTime) {
		this.bookIntTime = bookIntTime;
	}
	public String getPublishingHouse() {
		return publishingHouse;
	}
	public void setPublishingHouse(String publishingHouse) {
		this.publishingHouse = publishingHouse;
	}
	
	
	

}
