package com.library.system.quartz;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.library.system.entity.MyUser;
import com.library.system.service.IMyUserService;

public class QuartzTest {
	   //���õķ���  
	
	  //  @Autowired
	    @Resource(name = "userService")
		private IMyUserService userService;
    public void execute(){
    	MyUser u=userService.QueryById("402897811be281890000");
    	System.out.println("��ʱ����-----111111---------�������ҵ��û��� ���� "+u.getUserName());
    }
}
