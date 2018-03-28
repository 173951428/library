package com.library.system.entity;

import java.sql.Date;

/**
 * 公告实体类
 * @author Administrator
 *
 */
public class NoticeEntity {
  private Integer id;
  private String title;
  private String details;
  private Date publicTime;
  private String pubilcAuthorId;
  private Integer isPlulic;
public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDetails() {
	return details;
}

public void setDetails(String details) {
	this.details = details;
}

public Date getPublicTime() {
	return publicTime;
}

public void setPublicTime(Date publicTime) {
	this.publicTime = publicTime;
}

public String getPubilcAuthorId() {
	return pubilcAuthorId;
}

public void setPubilcAuthorId(String pubilcAuthorId) {
	this.pubilcAuthorId = pubilcAuthorId;
}

public Integer getIsPlulic() {
	return isPlulic;
}

public void setIsPlulic(Integer isPlulic) {
	this.isPlulic = isPlulic;
}

  
}
