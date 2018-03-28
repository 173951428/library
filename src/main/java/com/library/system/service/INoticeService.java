package com.library.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.library.system.entity.NoticeEntity;


public interface INoticeService {
	  //现实前7条公告
	  public List<Map<String,Object>> showNotice();
	  //根据id关联user表查询
	  public Map<String,Object> selectById(Integer id);
	  //发布公告
	  public Integer NoticePublic(Integer id);
	  //取消公告
	  public Integer CancelPublic(Integer id);
	  //根据id删除公告
	  public Integer deleteById(Integer id);
	  //根据id查询
	  public Map<String, Object> queryById(Integer id); 
	  public Integer saveOrUpdate(NoticeEntity notice, HttpServletRequest request);
	  
}
