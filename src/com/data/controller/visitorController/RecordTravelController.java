package com.data.controller.visitorController;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.data.md5.Encryption;
import com.data.model.tb_travelsModel;
import com.data.service.visitorService.loginService.LoginService;


@Controller
	public class RecordTravelController {
	@RequestMapping("/recordTravelUrl")
	@ResponseBody
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		//解决request中文"？？？"
		request.setCharacterEncoding("utf-8");
		//检查是否运行到这里
		System.out.println("test                                             test");
		
		String text = request.getParameter("Text");
		String vid = request.getParameter("Vid");
		String sid = request.getParameter("Sid");
		String publicty = request.getParameter("Publicty");
		//调试
		System.out.println(text+vid+sid+publicty);

		
	try {
			@SuppressWarnings("resource")
			//应用上下文
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
			//将数据传给Model
			tb_travelsModel tb_travelsmodel=(tb_travelsModel)factory.getBean("tb_travelsmodel");
			
			//设置Tid
			Calendar cal1 = Calendar.getInstance();  
	         TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       
	         java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
	         //test
	         System.out.println(sdf.format(cal1.getTime())); 
	         //随机数
	         int randomNumber = (int)(Math.random() * 10 );
	         System.out.println(randomNumber);//test
			 
	         tb_travelsmodel.setTid(sdf.format(cal1.getTime())+randomNumber);
			 System.out.println(tb_travelsmodel.getTid());//test
			

			 tb_travelsmodel.setVid(vid);
			 tb_travelsmodel.setSid(sid);
			 tb_travelsmodel.setText(text);
			 tb_travelsmodel.setPublicty(publicty);
			
		System.out.println(tb_travelsmodel.getPublicty()+tb_travelsmodel.getText()+tb_travelsmodel.getSid()+tb_travelsmodel.getVid());
		out.print(vid);
		} catch (Exception e) {
			System.out.println("error3");
		e.printStackTrace();
		}
	//	}
	return null;
//	return new ModelAndView();
		
	}
}	

