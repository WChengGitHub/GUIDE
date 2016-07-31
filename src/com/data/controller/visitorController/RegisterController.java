package com.data.controller.visitorController;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

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
		//检查是否运行到这里
		System.out.println("test");
		//用户名//避免与Visitor的对象visitor重复命名 后台的游客名 命名为Visitor
		String Visitor = request.getParameter("visitor");
		//密码
		String password = request.getParameter("password");
		String email=request.getParameter("email");
		//调试时 用于检查获取得是否与用户输入的一致
		System.out.println(Visitor+password);
		
		try {
			@SuppressWarnings("resource")
			//应用上下文
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
			//将数据传给Model
			tb_visitorModel tb_visitormodel=(tb_visitorModel)factory.getBean("tb_visitormodel");
			
			Encryption encryption=(Encryption)factory.getBean("encryption");
			@SuppressWarnings("static-access")
			String MD5password=encryption.generatePassword(password);
			tb_visitormodel.setPassword(MD5password);
			tb_visitormodel.setEmail(email);
			tb_visitormodel.setVisitor(Visitor);

			
			/*在这里setVid提示错误?
			Calendar cal1 = Calendar.getInstance();  
			TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       //非常关键的！！！ 
	        java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
	        System.out.println(sdf.format(cal1.getTime()));//test 
			tb_visitorModel.setVid(sdf.format(cal1.getTime()));
			System.out.println(tb_visitorModel.getVid());//test*/
			
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
