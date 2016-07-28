package com.data.controller.visitorController;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.data.md5.Encryption;
import com.data.model.tb_visitorModel;
import com.data.service.visitorService.registerService.RegisterService;

public class RegisterController implements Controller{

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		System.out.println("test");//检查是否运行到这里
		String Visitor = request.getParameter("visitor");//用户名
		String password = request.getParameter("password");//密码
		System.out.println(Visitor+password);//调试时 用于检查获取得是否与用户输入的一致
		
		try {
			@SuppressWarnings("resource")
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");//应用上下文
			tb_visitorModel tb_visitormodel=(tb_visitorModel)factory.getBean("tb_visitormodel");//将数据传给Model
			Encryption encryption=(Encryption)factory.getBean("encryption");
			@SuppressWarnings("static-access")
			String MD5password=encryption.generatePassword(password);
		
			tb_visitormodel.setVisitor(Visitor);
			tb_visitormodel.setPassword(MD5password);
			System.out.println(tb_visitormodel.getPassword());//test
			
			RegisterService RS=(RegisterService)factory.getBean("registerservice");
			int result=RS.Register(tb_visitormodel);
			out.print(result);
			
			} catch (Exception e) {
				System.out.println("error1");
			e.printStackTrace();
			}
		return null;
	}
}
