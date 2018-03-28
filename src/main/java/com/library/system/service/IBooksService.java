package com.library.system.service;

import com.library.system.entity.BookManageEntity;

/**
 * 图书管理service
 * @author Administrator
 *
 */
public interface IBooksService {
	//根据id查询
   public BookManageEntity selectById(Integer id);
   
   
}
