package com.ldz.college.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ldz.college.biz.IAdminInfoBiz;
import com.ldz.college.entity.AdminInfo;
import com.ldz.college.util.VerifyCode;

@Controller
@RequestMapping("/admin")
public class AdminInfoController {
	@Autowired
	private IAdminInfoBiz adminInfoBiz;
	
	@RequestMapping("/login")
	@ResponseBody
	public int login(HttpSession session, AdminInfo af, String yzm) {
		
		int result = -1;
		if (!session.getAttribute("code").toString().equalsIgnoreCase(yzm)) { // 验证码不正确
			result =  -2;
		} else {
			AdminInfo menberInfo = adminInfoBiz.login(af);
			if (menberInfo != null) {
				session.setAttribute("currentLoginAdmin", menberInfo);
				result =  1;
			}
		}
		return result;
	}
	
	@RequestMapping("/checkLogin")
	@ResponseBody
	private AdminInfo check(HttpSession session) {
		AdminInfo af = (AdminInfo) session.getAttribute("currentLoginAdmin");
		if (af != null) {
			return af;
		} else {
			return null;
		}
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
}
