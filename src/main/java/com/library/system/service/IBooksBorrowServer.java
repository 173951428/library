package com.library.system.service;

import java.util.Date;
import java.util.Map;

import com.library.system.entity.BooksBorrow;

/**
 * 借书表  
 * @author Administrator
 *
 */
public interface IBooksBorrowServer {
	   public Integer saveOrUpdate(BooksBorrow BooksBorrow);
	   // 借书之前先判断,该用户是否存在10本未存在的书籍
	   public Integer selectCountByStatus(String userId);
	   
	   public Date selectReturnTime(Integer borrowId);
	   
	   /**
	    * 根据当前借书的id,修改状态
	    * @param id
	    * @param borrowStatusId
	    * @return
	    */
	   public Integer updateStatusById(Map<String,Object> map);
	   
	   /**
	    * 根据借书表的id,查找书籍的id 
	    * @param id
	    * @return
	    */
	   public BooksBorrow selectBookId(Integer id);
	   
}
