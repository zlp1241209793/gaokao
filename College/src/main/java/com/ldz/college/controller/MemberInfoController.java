package com.ldz.college.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ldz.college.biz.IMemberInfoBiz;
import com.ldz.college.entity.MemberInfo;
import com.ldz.college.util.ResponseUtil;
import com.ldz.college.util.SendMailUtil;
import com.ldz.college.util.StringUtil;
import com.ldz.college.util.VerifyCode;

@Controller
@RequestMapping("/memberInfo")
public class MemberInfoController {
	@Autowired
	private IMemberInfoBiz memberInfoBiz;
	
	@Autowired
	private SendMailUtil sendMailUtil;
	
	@RequestMapping("/login")
	@ResponseBody
	public int login(HttpSession session, MemberInfo mInfo, String yzm) {
		
		int result = -1;
		if (!session.getAttribute("code").toString().equalsIgnoreCase(yzm)) { // 验证码不正确
			result =  -2;
		} else {
			MemberInfo memberInfo = memberInfoBiz.login(mInfo);
			if (memberInfo != null) {
				session.setAttribute("currentLoginmember", memberInfo);
				result =  1;
			}
		}
		return result;
	}
	
	@RequestMapping("/checkLogin")
	@ResponseBody
	private Map<String, Object> check(HttpSession session) {
		Object object = session.getAttribute("currentLoginmember");
		if (object == null) {
			return ResponseUtil.responseMap(500, null, null);
		}
		
		return ResponseUtil.responseMap(200, null, object);
	}

	@RequestMapping("/getCode")
	public void getCode(HttpServletResponse response, HttpServletRequest request) {

		/* 获取验证码图片*/
		try {

			int width=200;

			int height=100;

			BufferedImage verifyImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

			//生成对应宽高的初始图片

			String randomText = VerifyCode.drawRandomText(width,height,verifyImg);

			//单独的一个类方法，出于代码复用考虑，进行了封装。

			//功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符                   

			request.getSession().setAttribute("code", randomText);

			response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别

			OutputStream os = response.getOutputStream(); //获取文件输出流    

			ImageIO.write(verifyImg,"png",os);//输出图片流

			os.flush();

			os.close();//关闭流

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updateInfo")
	@ResponseBody
	public int updateInfo(MemberInfo mInfo) {
		int result = -1;
		result = memberInfoBiz.updateInfo(mInfo);
		
		return result;
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public MemberInfo findAll(String mno) {
		return memberInfoBiz.findAll(mno);
	}

	@RequestMapping("/getEmailCode")
	@ResponseBody
	public int getEmailCode(HttpSession session, String email, String nickName) {
		int result = -1;
		if (StringUtil.checkNull(email)) {// 邮箱为空
			result = 0; 
		} else { 
			String emailCode = "";
			if (!StringUtil.checkNull(memberInfoBiz.findByEmail(email))) { // 说明邮箱已被注册
				result = -2;
			} else { // 说明该邮箱未注册
				// 生成验证码
				Random rd = new Random();
				for (int i = 0; i < 6; i++) {
					emailCode += rd.nextInt(10);
				}
				sendMailUtil.sendHtmlMail(email, nickName, emailCode); // 发送邮件
				session.setAttribute("emailCode", emailCode);
				result = 1;
			}
		}
		return result;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public int register(HttpSession session, String code, MemberInfo memberInfo) {
		int result = -1;
		String emailCode = (String) session.getAttribute("emailCode");
		if (emailCode.equals(code)) { // 如果验证码正确
			result = memberInfoBiz.register(memberInfo);
		} else { // 验证码错误
			result = -2;
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/findMajor")
	public List<Map<String, Object>> findMajor() {
		return memberInfoBiz.findMajor();
	}
}
