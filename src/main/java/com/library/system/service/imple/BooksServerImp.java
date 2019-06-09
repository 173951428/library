package com.library.system.service.imple;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.system.entity.BookManageEntity;
import com.library.system.service.IBooksService;
import com.library.system.utils.SqlClient;
@Service("booksServer")
public class BooksServerImp implements IBooksService {
	 @Autowired
     public SqlClient sqlClient;
	@Override
	public Map<String,Object> selectById(Integer id) {
			return sqlClient.queryForObject("mapper.BooksMapper.selectById", id);
	}

	@Override
	public Integer saveOrUpdate(BookManageEntity bookManageEntity) {
		Integer result = null;
		  Integer id =bookManageEntity.getId();
		  if(null==id){
			  // 默认借出数量为0
			  bookManageEntity.setBookOutSum(0);
			  String stringTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//将时间格式转换成符合Timestamp要求的格式.
			  Timestamp nowTime =Timestamp.valueOf(stringTime);//把时间转换
			  bookManageEntity.setBookIntTime(nowTime);
			  sqlClient.insert("mapper.BooksMapper.insertBook", bookManageEntity);
			  result=1;
		  }else{
			  sqlClient.update("mapper.BooksMapper.updateBook", bookManageEntity);
			  result=2;
		  }
		return result;
	}

	@Override
	public Integer deleteById(Integer id) {    
		return sqlClient.delete("mapper.BooksMapper.deleteById", id);
	}

	@Override
	public List<Map<String, Object>> selectNewTopSeven() {
		// TODO Auto-generated method stub
		return sqlClient.queryForList("mapper.BooksMapper.selectNewTopSeven");
	}

	@Override
	public Integer selectCountById(Integer id) {
		// TODO Auto-generated method stub
		return sqlClient.queryForObject("mapper.BooksMapper.selectCountById",id);
	}

	@Override
	public List<Map<String, Object>> selectToEight() {
		
		return sqlClient.queryForList("mapper.BooksMapper.selectToEight");
	}

}
