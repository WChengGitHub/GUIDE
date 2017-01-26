package com.data.controller.visitorController;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.data.model.tb_visitorModel;
import com.data.service.SendMsg_webchinese;
 
@Controller
public class SendMessageController {
	
	@RequestMapping("/sendVerificationCodeUrl")
	@ResponseBody
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
				PrintWriter out = response.getWriter();
				//解决request中文"？？？"
				request.setCharacterEncoding("utf-8");
				//检查是否运行到这里
				System.out.println("test");
				String tele = request.getParameter("Telephone");
				//调试时 用于检查获取得是否与用户输入的一致
				System.out.println(tele+"   telephone");
				
			try{
				@SuppressWarnings("resource")
				//应用上下文
				ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
				//将数据传给Model
				tb_visitorModel tb_visitormodel=(tb_visitorModel)factory.getBean("tb_visitormodel");
				SendMsg_webchinese sendmessage=(SendMsg_webchinese)factory.getBean("sendmessageservice");
				
				tb_visitormodel.setTelephone(tele);
				int state=sendmessage.sendMessage(tb_visitormodel);
				out.print(state);
				out.close();
			}catch (Exception e) {
				System.out.println("sendMessage Controller error");
			e.printStackTrace();
			}
		return null;
	}
}