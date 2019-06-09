package com.library.system.service.imple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.system.entity.BooksTypeEntity;
import com.library.system.service.IBooksTypeServer;
import com.library.system.utils.SqlClient;
@Service("booksTypeServer")
public class BooksTypeServerImp implements IBooksTypeServer {
	 @Autowired
     public SqlClient sqlClient;
	 @Override
	public List<BooksTypeEntity> selectAll() {		
		return sqlClient.queryForList("mapper.BooksTypeMapper.selectAllType");
	}
	@Override
	public String selectById(Integer id) {
		  return  sqlClient.queryForObject("mapper.BooksTypeMapper.selectById",id);
	}

}
