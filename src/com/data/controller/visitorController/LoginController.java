package com.data.controller.visitorController;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;




import com.data.md5.Encryption;
import com.data.model.tb_visitorModel;
import com.data.service.visitorService.loginService.LoginService;

//    @RequestMapping(method = RequestMethod.POST) 
	public class LoginController implements Controller{
		
		public ModelAndView handleRequest(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			PrintWriter out = response.getWriter();
			System.out.println("test");//检查是否运行到这里
			String Visitor = request.getParameter("visitor");//获取 //避免与Visitor的对象visitor重复命名 后台的游客名 命名为Visitor
			String password = request.getParameter("password");
			System.out.println(Visitor+password);//调试时 用于检查获取得是否与用户输入的一致
			
			
		try {
			@SuppressWarnings("resource")
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");//应用上下文
			tb_visitorModel tb_visitorModel=(tb_visitorModel)factory.getBean("tb_visitormodel");//将数据传给Model
			Encryption encryption=(Encryption)factory.getBean("encryption");
			@SuppressWarnings("static-access")
			String MD5password=encryption.generatePassword(password);
		
			tb_visitorModel.setVisitor(Visitor);
			tb_visitorModel.setPassword(MD5password);
			System.out.println(tb_visitorModel.getPassword());
			LoginService LS=(LoginService)factory.getBean("loginservice");//getBean("service")相当于调用这个service来处理事务
			int b=LS.Login(tb_visitorModel);//用LoginService中的方法查找用户 实现登录验证
			
			out.print(b);

			} catch (Exception e) {
				System.out.println("error3");
			e.printStackTrace();
			}
		return null;
//		return new ModelAndView();
    }
}	
