package com.library.system.quartz;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.library.system.entity.MyUser;
import com.library.system.service.IMyUserService;

public class QuartzTest {
	   //调用的方法  
	
	  //  @Autowired
	    @Resource(name = "userService")
		private IMyUserService userService;
    public void execute(){
    	MyUser u=userService.QueryById("402897811be281890000");
    	System.out.println("定时任务-----111111---------启动查找的用户的 名字 "+u.getUserName());
    }
}
