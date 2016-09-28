package com.data.controller.adminController.adminFunctions;

import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.md5.Encryption;
import com.data.model.tb_adminModel;
import com.data.service.adminService.adminFunctions.loginService.LoginService;
import com.data.service.adminService.adminFunctions.loginService.LoginServiceImp;
@Controller
public class LoginController {
	@RequestMapping("/adminLoginRequert")
	@ResponseBody
	public void adminLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			PrintWriter out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			//account = URLDecoder.decode(account, "GBK");
			//test
			System.out.println(account+" "+password);
			if(account==null||password==null){}
			else{
			//Writer writer = response.getWriter();
			@SuppressWarnings("resource")
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
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
			int tip=LS.Login(tb_adminmodel);
//			String json = "{\"tip\":" + tip + "}";
//			writer.write(json);
			out.print(tip);
			}
    }
	@RequestMapping("/getTheAdminPrivilege")
	@ResponseBody
	public void getAdminPrivilege(HttpServletResponse response)
			throws Exception {
		Writer writer = response.getWriter();
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		LoginServiceImp loginserviceimp=(LoginServiceImp)factory.getBean("loginserviceimp");
		tb_adminModel tb_adminmodel2=(tb_adminModel)loginserviceimp.getAdminModel();
		String privilege=tb_adminmodel2.getPrivilege();
		//System.out.println(tb_adminmodel2.getPrivilege()+" "+privilege);
		int a=1,s=2,c=3;
		if(privilege.equals("a")){
			String json2 = "{\"privilege\":" + a + "}";
			System.out.println(json2+"test json2");
			writer.write(json2);}
		else if(privilege.equals("s")){
			String json2 = "{\"privilege\":" + s + "}";
			System.out.println(json2+"test json2");
			writer.write(json2);}
		else if(privilege.equals("c")){
			String json2 = "{\"privilege\":" + c + "}";
			System.out.println(json2+"test json2");
			writer.write(json2);}
	}
}


