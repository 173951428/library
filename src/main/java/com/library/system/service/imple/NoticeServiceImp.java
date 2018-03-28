package com.library.system.service.imple;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.system.entity.NoticeEntity;
import com.library.system.service.INoticeService;
import com.library.system.utils.SqlClient;

@Service("noticeService")
public class NoticeServiceImp implements INoticeService {
	@Autowired
	public SqlClient sqlClient;

	@Override
	public List<Map<String, Object>> showNotice() {

		return sqlClient.queryForList("mapper.NoticeMapper.showNotice");
	}

	@Override
	public Map<String,Object> selectById(Integer id) {	
		return sqlClient.queryForObject("mapper.NoticeMapper.selectById", id);
	}

	@Override
	public Integer NoticePublic(Integer id) {
		// TODO Auto-generated method stub
		return sqlClient.update("mapper.NoticeMapper.NoticePublic", id);
	}

	@Override
	public Integer CancelPublic(Integer id) {
		return sqlClient.update("mapper.NoticeMapper.CancelPublic", id);
	}

	@Override
	public Integer deleteById(Integer id) { 
		return sqlClient.delete("mapper.NoticeMapper.deleteById", id);
	}

	@Override
	public Map<String, Object> queryById(Integer id) {
		
		return sqlClient.queryForObject("mapper.NoticeMapper.queryById", id);
	}

	@Override
	public Integer saveOrUpdate(NoticeEntity notice,HttpServletRequest request) {
		Integer result=0;
		  if(null==notice.getId()){
			   
			  String Nowtime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			  java.sql.Date mysqldate=java.sql.Date.valueOf(Nowtime);
			  notice.setPublicTime(mysqldate);
			 notice.setPubilcAuthorId((String) request.getSession().getAttribute("userId"));
			 notice.setIsPlulic(0);
			  sqlClient.insert("mapper.NoticeMapper.insertNotice", notice);
			  result=1;
		  }else if(null!=notice.getId()){
			   sqlClient.update("mapper.NoticeMapper.updateNoticeById", notice) ;
			   result=2;
		  }
			  
		return result;
	}

	
	
	

}
