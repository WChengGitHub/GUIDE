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

import com.data.model.tb_travelsModel;
import com.data.model.tb_visitorModel;
import com.data.service.visitorService.recordTravelService.recordTravelService;


@Controller
	public class RecordTravelController {
	@RequestMapping("/recordTravelUrl")
	@ResponseBody
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//解决request中文"？？？"
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//检查是否运行到这里	
		System.out.println("test                                             test");
		
		
		String text = request.getParameter("Text");
		String visitor = request.getParameter("Visitor");
		String sid = request.getParameter("Sid");
		String publicty = request.getParameter("Publicty");
		//调试
		System.out.println(text+visitor+sid+publicty);

		
	try {
			@SuppressWarnings("resource")
			//应用上下文
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
			//将数据传给Model
			tb_travelsModel tb_travelsmodel=(tb_travelsModel)factory.getBean("tb_travelsmodel");
			tb_visitorModel tb_visitormodel=(tb_visitorModel)factory.getBean("tb_visitormodel");
			//设置Tid
			Calendar cal1 = Calendar.getInstance();  
	         TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       
	         java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
	         //test
	         System.out.println(sdf.format(cal1.getTime())); 
	         //随机数
	         int randomNumber = (int)(Math.random() * 10 );
	         System.out.println(randomNumber);//test
			 
	         recordTravelService recordtravelservice=(recordTravelService)factory.getBean("recordtravelserviceimp");
			 //检查是否运行到这里
			 System.out.println("test controller");
	         
	         //获取Vid
	         tb_visitormodel.setVisitor(visitor);
	         String vid = recordtravelservice.GetVid(tb_visitormodel);
	         
	         System.out.println(vid + "   test                         test                              test ");
	         
	         tb_travelsmodel.setTid(sdf.format(cal1.getTime())+randomNumber);
			 System.out.println(tb_travelsmodel.getTid());//test
			

			 tb_travelsmodel.setVid(vid);
			 tb_travelsmodel.setSid(sid);
			 tb_travelsmodel.setText(text);
			 tb_travelsmodel.setPublicty(publicty);
			
			 System.out.println(tb_travelsmodel.getPublicty()+tb_travelsmodel.getText()
					 +tb_travelsmodel.getSid()+tb_travelsmodel.getVid());
			 
			 
			 //发表情况state,成功与否1/0表示
			 int state=recordtravelservice.Publish(tb_travelsmodel);
			 out.print(state);			 
			 System.out.println(state+"        test    state      ");
		} catch (Exception e) {
			System.out.println("error3");
		e.printStackTrace();
		}
	//	}
	return null;
//	return new ModelAndView();
		
	}
}	

