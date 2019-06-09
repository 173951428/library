package com.library.system.service.imple;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.system.entity.BooksReturnEntity;
import com.library.system.service.IBooksReturnServer;
import com.library.system.utils.SqlClient;
@Service("booksReturnServer")
public class BooksReturnServerImp implements IBooksReturnServer {
  
	@Autowired
    public SqlClient sqlClient;
	
	@Override
	public Integer saveOrUpdate(BooksReturnEntity booksReturnEntity) {
		 
		Integer result;
		 if(null==booksReturnEntity.getId()||booksReturnEntity.equals("")){
			 
			 sqlClient.insert("mapper.BooksReturnMapper.insertBooksReturn", booksReturnEntity);
			 result=1;
		 }else{
			 sqlClient.update("mapper.BooksReturnMapper.upateBooksReturn",booksReturnEntity);
			 result=2;
		 }
		 return result;
	}

	@Override
	public BooksReturnEntity selectByBorrowId(Integer borrowId) {
	    return sqlClient.queryForObject("mapper.BooksReturnMapper.selectByBorrowId", borrowId);
	}

	@Override
	public Integer updateStatusByBorrowId(Map<String, Object> map) {
		return sqlClient.update("mapper.BooksReturnMapper.updateBorrowStatusId", map);
	}

}
