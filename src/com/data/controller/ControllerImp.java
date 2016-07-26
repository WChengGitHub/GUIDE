package com.data.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;



import com.data.loginservice.LoginService;
import com.data.model.Visitor;

//    @RequestMapping(method = RequestMethod.POST) 
	public class ControllerImp implements Controller{
		
		public ModelAndView handleRequest(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			PrintWriter out = response.getWriter();
			System.out.println("sss");//检查是否运行到这里
			String name = request.getParameter("name");//获取
			String password = request.getParameter("pass");
			System.out.println(name+password);//调试时 用于检查获取得是否与用户输入的一致
			
			
		try {
			@SuppressWarnings("resource")
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");//应用上下文
			Visitor U=(Visitor)factory.getBean("user");//将数据传给Model
			U.setName(name);
			U.setPassword(password);
			
			LoginService LS=(LoginService)factory.getBean("service");//getBean("service")相当于调用这个service来处理事务
			int b=LS.Login(U);//用LoginService中的方法查找用户 实现登录验证
			
			out.print(b);

			} catch (Exception e) {
				System.out.println("Loading...");
			e.printStackTrace();
			}
		return null;
//		return new ModelAndView();
    }
}	

























//    	if (!"admin".equals(username) || !"admin".equals(password)) {
//            return "loginError"; 
//        }
//        return "loginSuccess";
//    }
//
//    @RequestMapping("/test/login2.do")
//    public ModelAndView testLogin2(String username, String password, int age){
//        
//    	
//        if (!"admin".equals(username) || !"admin".equals(password) || age < 5) {
//            return new ModelAndView("loginError");
//        }
//        return new ModelAndView(new RedirectView("../index.jsp"));  
//    }
//
//    @RequestMapping("/test/login3.do")
//    public ModelAndView testLogin3(Model model) {
//        String username = model.getVid();
//        String password = model.getTelephone();
//        
//        if (!"admin".equals(username) || !"admin".equals(password)) {
//            return new ModelAndView("loginError");
//        }
//        return new ModelAndView("loginSuccess");
//    }
//
//    @Resource(name = "loginService")  
//    private LoginService loginService;  
//    @RequestMapping("/test/login4.do")
//    public String testLogin4(Model model) {
//        if (loginService.login(model) == false) {
//            return "loginError";
//        }
//        return "loginSuccess";
//    }






//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//
//import com.data.loginservice.ImpLoginService;
//import com.data.model.Model;
//
//@Controller
//@RequestMapping("/Action")
//public class ImpController {
//	private ImpLoginService is;
//	private Model model;
//    @RequestMapping(method = RequestMethod.POST)  
//    public void testLogin(HttpServletRequest request,HttpServletResponse response) {
//    	String N = request.getParameter("name");
//		String P = request.getParameter("pass");
//		model.setVid(N);
//		String Name =model.getVid();
//		model.setTelephone(P);
//		String Password =model.getTelephone();
//		boolean b=is.Login(Name, Password);
//		
//    }
//}	
