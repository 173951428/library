package com.library.system.controller;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class Email {

	public static void main(String[] args) throws GeneralSecurityException, MessagingException {
		 
		Properties props = new Properties();
        
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        
        //1 .腾讯扣扣邮箱
           props.setProperty("mail.host", "smtp.qq.com");
      
        // 发送邮件协议名称
         props.setProperty("mail.transport.protocol", "smtp");
     
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        int i=0;
     while(true){
        Session session = Session.getInstance(props);
     
        Message msg = new MimeMessage(session);
        msg.setSubject("你好?");
        StringBuilder builder = new StringBuilder();
       // builder.append("验证码为:");
        builder.append("你好");
        msg.setText(builder.toString());
        msg.setFrom(new InternetAddress("719568690@qq.com"));
     
        Transport transport = session.getTransport();
       // 腾讯邮箱
     transport.connect("smtp.qq.com", "719568690@qq.com", "kngqytyqbqajbaie");
       
        transport.sendMessage(msg, new Address[] { new InternetAddress("173951428@qq.com") });
        transport.close();
        i++;
        System.out.println("--------------------------->已发出邮件"+i+"封!!<---------------------------------------");
     }
	}

}
