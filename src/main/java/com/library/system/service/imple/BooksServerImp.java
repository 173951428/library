package com.library.system.service.imple;

import org.springframework.stereotype.Service;

import com.library.system.entity.BookManageEntity;
import com.library.system.service.IBooksService;
@Service("boosServer")
public class BooksServerImp implements IBooksService {

	@Override
	public BookManageEntity selectById(Integer id) {
			return null;
	}

}
