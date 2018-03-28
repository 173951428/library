package com.library.system.controller;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.system.entity.MyUser;
import com.library.system.service.IMyUserService;
import com.library.system.utils.JsonResult;
import com.library.system.utils.MessageUtil;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



@Controller
@RequestMapping(value = "/user")
public class UserController {
	//@Autowired 
	 @Resource(name = "userService")
	private IMyUserService userService;
	//Resource注解是指定哪个bean的ID .
	 @Resource(name = "taskExecutor") //线程池的ID 名字
    private TaskExecutor taskExecutor;

	/**
	 * 登录
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public int show(@RequestBody  MyUser user,HttpServletRequest request) {
	    int result=0;
		 MyUser checkUser = userService.CheckUser(user);
		 HttpSession session = request.getSession();
		if(null!=checkUser){
			session.setAttribute("userId",checkUser.getId());
			  if(checkUser.getLevel()==0){
				  result=0;
			  }else if(checkUser.getLevel()==1){
				  result=1;
			  }
		 }else{ 
			 return 2;
		 }
		return result;
	}
	
	/**
	 * 发送短信验证码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkMessage", method = RequestMethod.POST)
	public Map<String,Object> checkMessage(@RequestBody Map<String, Object> param) {
		Map<String,Object> result=new HashMap<String,Object>();
		JsonResult jr=new JsonResult();
		String phoneNumber=String.valueOf(param.get("phoneNumber"));
		 jr=MessageUtil.getMessage(phoneNumber);
		if(jr.getResult()){
			result.put("code",jr.getMsg());
		} else{
			result.put("code", "ERROR");
		}
		 
		return result;	
	}	
	
	

	
	
  /**
   * 增加或者修改用户
   * @param user
   * @return
   */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public int addUser(@RequestBody MyUser user) {
		return userService.SaveOrUpdate(user);
	}
	
	/**
	 * 根据ID查询用户
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryById", method = RequestMethod.POST)
	public MyUser queryById(@RequestBody Map<String, Object> param) {
		String id = String.valueOf(param.get("id"));
		MyUser user=userService.QueryById(id);
		return user;
	}
	
	
	/**
	 * 根据用户名检查用户是否存在 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public Integer checkUser(@RequestBody  Map<String, Object> param) {
		Integer result = null;
		String userName = String.valueOf(param.get("userName"));
		 try {
			if(userService.checkUser(userName)>0){	
				 result=1;
			}else{
				result=0;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
	


	@ResponseBody
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	public int deleteById(@RequestBody Map<String, Object> param) {
		String id = String.valueOf(param.get("id"));
		return userService.deleteById(id);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/clickTest", method = RequestMethod.POST)
	public List<Map<String,Object>>  clickTest() {
		List<Map<String,Object>> lists=new ArrayList<Map<String,Object>> ();
		lists=  userService.slectByMap();
		return lists;
	}
	
	
	/**
	 * 发送电子邮件
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	
	@ResponseBody
	@RequestMapping(value = "/getEmail", method = RequestMethod.POST)
	public Integer getEmail(@RequestBody Map<String, String> param,HttpServletRequest request) throws Exception,GeneralSecurityException, MessagingException {
		String  receiveMailAccount=String.valueOf(param.get("email")); //要发送的邮箱
		String userName=String.valueOf(param.get("userName"));
		Integer result = null;
		if( userService.checkUserAndEmail(param)>0){
			Properties props = new Properties();
	        // 开启debug调试
	        props.setProperty("mail.debug", "true");
	        // 发送服务器需要身份验证
	        props.setProperty("mail.smtp.auth", "true");
	        // 设置邮件服务器主机名
	        props.setProperty("mail.host", "smtp.qq.com");
	        // 发送邮件协议名称
	        props.setProperty("mail.transport.protocol", "smtp");
	     
	        MailSSLSocketFactory sf = new MailSSLSocketFactory();
	        sf.setTrustAllHosts(true);
	        props.put("mail.smtp.ssl.enable", "true");
	        props.put("mail.smtp.ssl.socketFactory", sf);
	        Session session = Session.getInstance(props);
	        Message msg = new MimeMessage(session);
	        msg.setSubject("图书管理系统修改密码验证");
	        StringBuilder builder = new StringBuilder();
	        String checkCode= String.valueOf((new Random().nextInt(899999) + 100000));
	        HttpSession sysSession= request.getSession();
	        sysSession.setAttribute("checkCode",checkCode);
	        sysSession.setAttribute("userName",userName);
	        builder.append("您的验证码为:");
	        builder.append(" ");
	        builder.append(checkCode);
	        builder.append("\n该验证码用于重置图书管理系统密码,请不要告诉别人哦 !");
	        msg.setText(builder.toString());
	        msg.setFrom(new InternetAddress("719568690@qq.com"));  //发送人的邮箱
	        Transport transport = session.getTransport();
	        /**
	         * param1  发件人的邮箱协议
	         * 
	         * param2 发件人的邮箱地址
	         * 
	         * param2 发件人邮箱的第三方密码
	         */
	        transport.connect("smtp.qq.com", "719568690@qq.com", "kngqytyqbqajbaie"); 
	        try {
				transport.sendMessage(msg, new Address[] {new InternetAddress(receiveMailAccount)});
			} catch (Exception e) {
				  result=2;
				e.printStackTrace();
			}
	        transport.close();
            result=1;
			
		}else{
			result=0;
		}
		 
		return result;
	}
	
	
	
	/**
	 * 检查验证码是否正确 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkCode", method = RequestMethod.POST)
	public Integer checkCode(@RequestBody Map<String, Object> param,HttpServletRequest request){
		 Integer result=null;
		 HttpSession session= request.getSession();
		 String checkCode1= (String) session.getAttribute("checkCode");
		 String checkCode2=String.valueOf(param.get("checkCode"));	     	
		 if(null!=checkCode1&&checkCode1.equals(checkCode2)){
			   result=1;
		 }else{
			 result=2; 
		 }
		return result;	
	}
	
	/**
	 * 修改密码 
	 * @param param
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public Integer changePassword(@RequestBody Map<String, Object> param,HttpServletRequest request){
		 Integer result=null;
		 HttpSession session= request.getSession();
		 String userName=(String) session.getAttribute("userName");
		 if(null!=userName&&!userName.equals("")){
			 param.put("userName", userName);
			 if(userService.changePassword(param)>0){
				 result=1; 
			 }else{
				 result=2; 
			 }
		 }else{
			 result=3;
		 }
		return result;
	}
	

  
}
