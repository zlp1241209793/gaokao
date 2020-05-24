package com.ldz.college.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailUtil {
	private String sendEmail; // 发件�?
	private String pwd; // 发件箱的授权�?
	
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public boolean sendHtmlMail(String receiveEmail, String name, String code) {
		if (StringUtil.checkNull(receiveEmail, name, code)) {
			return false;
		}
		
		try {
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl(); 
			
			senderImpl.setHost("smtp.qq.com"); // 邮箱主机。如果是网易smtp.163.com
			senderImpl.setDefaultEncoding("utf-8"); // 编码�?
			
			// 建立邮件的消息，我们�?要发送的是html格式邮件
			MimeMessage mimeMessage = senderImpl.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			
			// 设置收件人，寄件�?
			messageHelper.setTo(receiveEmail);
			messageHelper.setFrom(sendEmail);
			messageHelper.setSubject("源辰在线考试系统");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd�? HH:mm:ss");
			String str = "<!DOCTYPE html><html><head><meta charset='UTF-8'></head><body><p style='font-size: 20px;font-weight: bold;'>尊敬的："+name+"，您好！</p>"
					+ "<p style='text-indent: 2em;font-size: 20px;'>欢迎使用源辰在线考试系统密码找回功能，您本次的注册码�?"
					+ "<span style='font-size: 30px;font-weight: bold;color: red;'>"+code+"</span>�?3分钟之内有效，请尽快使用�?</p>"
					+ "<p style='text-align: right;padding-right: 20px;'><a href='http://www.hyycinfo.com' style='font-size: 18px;'>衡阳市源辰信息科�?有限公司�?术部</a></p>"
					+ "<span style='font-size: 18px;float: right;margin-right: 60px;'>"+sdf.format(new Date())+"</span></body></html>";
			
			// 设置邮件正文
			messageHelper.setText(str, true);
			
			// 设置名称
			senderImpl.setUsername(sendEmail); // 发件�?
			senderImpl.setPassword(pwd); // 发件箱密�?
			
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", "true"); // 让服务器去认证用户名和密�?
			prop.put("mail.smtp.timeout", 2500); // 连接超时时间
			
			senderImpl.setJavaMailProperties(prop);
			senderImpl.send(mimeMessage);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
