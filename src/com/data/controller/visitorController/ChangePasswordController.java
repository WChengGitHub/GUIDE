package com.data.controller.visitorController;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.data.md5.Encryption;
import com.data.model.tb_visitorModel;
import com.data.service.visitorService.changePasswordService.changePasswordServiceImp;


@Controller
	public class ChangePasswordController {
	@RequestMapping("/changePasswordUrl")
	@ResponseBody
	public ModelAndView handleRequest(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		//解决request中文"？？？"
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//检查是否运行到这里	
		System.out.println("test                                             test");
		
		String visitor = request.getParameter("Visitor");
		String password = request.getParameter("Password");
		//调试
		System.out.println(visitor+password);

		
	try {
			@SuppressWarnings("resource")
			//应用上下文
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
			//将数据传给Model
			tb_visitorModel tb_visitormodel=(tb_visitorModel)factory.getBean("tb_visitormodel");
			//设置Tid
			 
			changePasswordServiceImp cPServiceImp=(changePasswordServiceImp)factory.getBean("changePasswordServiceImp");
			//检查是否运行到这里
			System.out.println("test changePassword controller");
	         
	        //传值给model 
			tb_visitormodel.setVisitor(visitor);
			//密码转md5加密
			Encryption encryption=(Encryption)factory.getBean("encryption");
			@SuppressWarnings("static-access")
			String MD5password=encryption.generatePassword(password);
			tb_visitormodel.setPassword(MD5password);
			
			 
			 //发表情况state,成功与否1/0表示
			 int state=cPServiceImp.changePassword(tb_visitormodel);
			 out.print(state);			 
			 System.out.println(state+"        test    state      ");
		} catch (Exception e) {
			System.out.println("error changePassword Controller");
		e.printStackTrace();
		}
	//	}
	return null;
//	return new ModelAndView();
		
	}
}	

