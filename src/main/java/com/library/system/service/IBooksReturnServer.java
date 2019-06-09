package com.library.system.service;

import java.util.Map;

import com.library.system.entity.BooksReturnEntity;

/**
 * 图书归还表接口 
 * @author Administrator
 *
 */
public interface IBooksReturnServer {
    Integer saveOrUpdate(BooksReturnEntity booksReturnEntity);
    
    
    /**
     * 根据借书表的id查询 还书表的对象
     * @param borrowId
     * @return
     */
    BooksReturnEntity selectByBorrowId(Integer borrowId);
   /**
    * 根据图书归还表的id,修改图书状态 
    * @param map
    * @return
    */
    public Integer updateStatusByBorrowId(Map<String,Object> map);
}
