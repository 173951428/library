package com.library.system.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.system.entity.NoticeEntity;
import com.library.system.service.INoticeService;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
	@Autowired
	INoticeService noticeService;

	@ResponseBody
	@RequestMapping(value = "/showNotice", method = RequestMethod.POST)
	public List<Map<String,Object>>  clickTest() {
		List<Map<String,Object>> lists=new ArrayList<Map<String,Object>> ();
		lists=  noticeService.showNotice();
		return lists;
	}
	@ResponseBody
	@RequestMapping(value = "/queryById", method = RequestMethod.POST)
	public Map<String,Object> selectById(@RequestBody Map<String, Object> param){
		Integer id=(Integer) param.get("id");
		return noticeService.selectById(id);
	}
	@ResponseBody
	@RequestMapping(value = "/noticePublic", method = RequestMethod.POST)
	public  Integer noticePublic(@RequestBody Map<String, Object> param){
		Integer id=Integer.valueOf((String) param.get("id"));
		return noticeService.NoticePublic(id);
		
	}
	@ResponseBody
	@RequestMapping(value = "/cancelPublic", method = RequestMethod.POST)
	public  Integer cancelPublic(@RequestBody Map<String, Object> param){
		Integer id=Integer.valueOf((String) param.get("id"));
		return noticeService.CancelPublic(id);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	public  Integer deleteById(@RequestBody Map<String, Object> param){
		Integer id=Integer.valueOf((String) param.get("id"));
		return noticeService.deleteById(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/selectById", method = RequestMethod.POST)
	public Map<String,Object> queryById(@RequestBody Map<String, Object> param){
		Integer id=Integer.valueOf((String) param.get("id"));
		return noticeService.queryById(id);
	}
	
	//保存或者修改
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public Integer saveOrUpdate(@RequestBody NoticeEntity notice,HttpServletRequest request){
		 HttpSession session= request.getSession();
		 String userId=(String) session.getAttribute("userId");
		return noticeService.saveOrUpdate(notice,request);
	}
	
}
