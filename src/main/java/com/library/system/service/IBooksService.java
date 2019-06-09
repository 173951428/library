package com.library.system.service;

import java.util.List;
import java.util.Map;

import com.library.system.entity.BookManageEntity;

/**
 * 图书管理service
 * @author Administrator
 *
 */
public interface IBooksService {
	//根据id查询
   public Map<String,Object> selectById(Integer id);
   //保存或者修改图书内容
   public Integer saveOrUpdate(BookManageEntity bookManageEntity);
   //删除书籍
   public Integer deleteById(Integer id);
   //查询新书推荐,推荐前10条,根据录入书籍的顺序
   public List<Map<String,Object>> selectNewTopSeven();
   //根据id查询图书数量
   public  Integer selectCountById(Integer id);
   
   //根据借阅的次数,现实前8个
   public List<Map<String,Object>> selectToEight();
   
}
