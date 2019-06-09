package com.library.system.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.system.entity.BookStatus;
import com.library.system.service.IBookStatus;
import com.library.system.utils.SqlClient;
@Service("bookStatusServer")
public class BookStatusImp implements IBookStatus {
    
	 @Autowired
     public SqlClient sqlClient;
	 @Override
	public List<BookStatus> selectAll() {
		 return  sqlClient.queryForList("mapper.BookStatusMapper.selectAll");
	}

}
