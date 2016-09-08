package com.data.controller.adminController.superAdminController;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.data.md5.Encryption;
import com.data.model.tb_adminModel;
import com.data.service.adminService.superAdminFunctions.addAdminService.AddAdminService;

public class AddAdminController implements Controller{

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//检查是否运行到这里
		System.out.println("test");
		//用户名//避免与Visitor的对象visitor重复命名 后台的游客名 命名为Visitor
		String account = request.getParameter("account");
		//密码
		String password = request.getParameter("password");
		String privilege=request.getParameter("privilege");
		//调试时 用于检查获取得是否与用户输入的一致
		System.out.println(account+password);
		if(account==null||password==null||privilege==null){}
		else{
		  try {
			@SuppressWarnings("resource")
			//应用上下文
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
			//将数据传给Model
			tb_adminModel tb_adminmodel=(tb_adminModel)factory.getBean("tb_adminmodel");
			
			Encryption encryption=(Encryption)factory.getBean("encryption");
			@SuppressWarnings("static-access")
			String MD5password=encryption.generatePassword(password);
			tb_adminmodel.setPassword(MD5password);
			tb_adminmodel.setPrivilege(privilege);
			tb_adminmodel.setAccount(account);

			AddAdminService addadminservice=(AddAdminService)factory.getBean("addadminserviceimp");
			int result=addadminservice.addAdmin(tb_adminmodel);
			System.out.println(result);
			out.print(result);
			
			} catch (Exception e) {
				System.out.println("error add admin controller");
			e.printStackTrace();
			}
		  }
		return null;
	}
}
