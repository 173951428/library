package com.library.system.service;

import java.util.List;

import com.library.system.entity.BooksTypeEntity;

public interface IBooksTypeServer {
	
     public List<BooksTypeEntity> selectAll();
     
     public String selectById(Integer id);
}
