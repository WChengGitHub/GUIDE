package com.data.controller.adminController.superAdminController;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.data.model.tb_visitorModel;
import com.data.service.adminService.superAdminFunctions.unlockVisitorService.UnlockVisitorService;


 
	public class UnlockVisitorController implements Controller{
		
		public ModelAndView handleRequest(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			PrintWriter out = response.getWriter();
			//检查是否运行到这里
			System.out.println("test");
			//获取 
			String Vid = request.getParameter("vid");
			//调试时 用于检查获取得是否与用户输入的一致
			System.out.println(Vid);
			
			
		try {
			@SuppressWarnings("resource")
			//应用上下文
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
			//将数据传给Model
			tb_visitorModel tb_visitormodel=(tb_visitorModel)factory.getBean("tb_visitormodel");
			tb_visitormodel.setVid(Vid);
			System.out.println(tb_visitormodel.getVid());
			
			UnlockVisitorService unlockvisitorservice=(UnlockVisitorService)factory.getBean("unlockvisitorservice");
			//检查是否运行到这里
			System.out.println("test controller");
			int d=unlockvisitorservice.unlockVisitor(tb_visitormodel);
			out.print(d);
			
			
			} catch (Exception e) {
				System.out.println("error3");
			e.printStackTrace();
			}
		return null;
//		return new ModelAndView();
    }
}	
