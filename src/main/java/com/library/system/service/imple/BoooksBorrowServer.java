package com.library.system.service.imple;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.system.entity.BooksBorrow;
import com.library.system.service.IBooksBorrowServer;
import com.library.system.utils.SqlClient;
@Service("booksBorrowServer")
public class BoooksBorrowServer implements IBooksBorrowServer {
	@Autowired
    public SqlClient sqlClient;
	@Override
	public Integer saveOrUpdate(BooksBorrow booksBorrow) {
		Integer result;
		 if(null==booksBorrow.getId()||booksBorrow.equals("")){
			 sqlClient.insert("mapper.BooksBorrowMapper.insertBooksBorrow", booksBorrow);
			 result=1;
		 }else{
			 sqlClient.update("mapper.BooksBorrowMapper.upateBooksBorrow",booksBorrow);
			 result=2;
		 }
		 return result;
	}
	@Override
	public Integer selectCountByStatus(String userId) {
		return sqlClient.queryForObject("mapper.BooksBorrowMapper.selectCountByStatus", userId);
	}
	/**
	 *根据借阅表id ,查询 应该还书日期 
	 */
	@Override
	public Date selectReturnTime(Integer borrowId) {
		
		return  sqlClient.queryForObject("mapper.BooksBorrowMapper.selectReturnTime", borrowId);
	}
	@Override
	public Integer updateStatusById(Map<String,Object> map) {
		 
		return sqlClient.update("mapper.BooksBorrowMapper.updateBorrowStatusId", map);
	}
	@Override
	public BooksBorrow selectBookId(Integer id) {
		  
		return sqlClient.queryForObject("mapper.BooksBorrowMapper.queryById", id);
	}
	
}
