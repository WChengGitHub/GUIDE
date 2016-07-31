package com.data.controller.adminController.adminFunctions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.data.md5.Encryption;

import com.data.model.tb_adminModel;
import com.data.service.adminService.adminFunctions.loginService.LoginService;

public class LoginController implements Controller{
		
		public ModelAndView handleRequest(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			PrintWriter out = response.getWriter();
			//检查是否运行到这里
			System.out.println("test");
			//获取 //避免与Visitor的对象visitor重复命名 后台的游客名 命名为Visitor
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			//调试时 用于检查获取得是否与用户输入的一致
			System.out.println(account+password);
			
			
		try {
			@SuppressWarnings("resource")
			//应用上下文
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
			//将数据传给Model
			tb_adminModel tb_adminmodel=(tb_adminModel)factory.getBean("tb_adminmodel");
			Encryption encryption=(Encryption)factory.getBean("encryption");
			@SuppressWarnings("static-access")
			String MD5password=encryption.generatePassword(password);
		
			tb_adminmodel.setAccount(account);
			tb_adminmodel.setPassword(MD5password);
			//test
			System.out.println(tb_adminmodel.getPassword());
			//getBean("service")相当于调用这个service来处理事务
			LoginService LS=(LoginService)factory.getBean("adminloginservice");System.out.println("test5");
			//用LoginService中的方法查找用户 实现登录验证
			int b=LS.Login(tb_adminmodel);
			
			out.print(b);
			
			} catch (Exception e) {
				System.out.println("error3");
			e.printStackTrace();
			}
		return null;
//		return new ModelAndView();
    }
}


