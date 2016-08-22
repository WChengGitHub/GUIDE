package com.data.controller.adminController.superAdminController;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.data.model.tb_adminModel;
import com.data.model.tb_visitorModel;
import com.data.service.adminService.superAdminFunctions.deleteAdminService.deleteAdminService;
import com.data.service.adminService.superAdminFunctions.lockVisitorService.LockVisitorService;


 @Controller
	public class DeleteAdminController{
	 @RequestMapping("/deleteadmin")
		@ResponseBody
		public ModelAndView handleRequest(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
		    request.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			//检查是否运行到这里
			System.out.println("test");
			//获取
			String account = request.getParameter("account");
			//调试用
			System.out.println(account);
			
			
		try {
			@SuppressWarnings("resource")
			//应用上下文
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
			//将数据传给Model
			tb_adminModel tb_adminmodel=(tb_adminModel)factory.getBean("tb_adminmodel");
			tb_adminmodel.setAccount(account);
			System.out.println(tb_adminmodel.getAid());
			
			deleteAdminService deleteadminservice=(deleteAdminService)factory.getBean("deleteadminserviceimp");
			//检查是否运行到这里
			System.out.println("test controller");
			//冻结情况state
			int state=deleteadminservice.deleteAdmin(tb_adminmodel);
			out.print(state);
			

			} catch (Exception e) {
				System.out.println("error3");
			e.printStackTrace();
			}
		return null;
//		return new ModelAndView();
    }
}	
