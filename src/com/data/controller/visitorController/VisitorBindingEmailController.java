package com.data.controller.visitorController;

import java.io.IOException;
import java.io.Writer;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.model.AreaAdminAddOrChangeOrDeleteSpotAdminModel;
import com.data.model.VisitorBindingEmailModel;
import com.data.service.adminService.areaAdminFunction.areaAdminAddOrChangeOrDeleteSpotAdmin.AreaAdminAddOrChangeOrDeleteSpotAdminService;
import com.data.service.visitorService.visitorBindingEmail.VisitorBindingEmailService;
import com.sun.org.apache.bcel.internal.generic.NEW;
@RequestMapping("/VisitorBindingEmailController")
@Controller
public class VisitorBindingEmailController {
	@RequestMapping("/sendBingingEamilInformation")
	@ResponseBody
	public String sendBingingEamilInformation(
			@RequestParam(value = "Visitor", required = false) String Visitor,@RequestParam(value = "Email", required = false) String Email)
			throws IOException {
		if(Visitor.isEmpty()||Email.isEmpty())
			return null;
		Visitor = java.net.URLDecoder.decode(Visitor, "UTF-8");
		//System.out.println("Visitor:"+Visitor+"Email:"+Email);
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	   VisitorBindingEmailService visitorBindingEmailService=(VisitorBindingEmailService) factory.getBean("visitorBindingEmailService");
	   VisitorBindingEmailModel visitorBindingEmailModel=new VisitorBindingEmailModel();
	   visitorBindingEmailModel.setVisitor(Visitor);
	   visitorBindingEmailModel.setEmail(Email);
	   visitorBindingEmailService.sendBingEmailInformation(visitorBindingEmailModel);
	   if(visitorBindingEmailService.sendBingEmailInformation(visitorBindingEmailModel))
	   {
		   String json = "{\"Status\":true}";
			return json;
	   }
	   return null;
	}
	
	@RequestMapping("/checkBingingEamilInformation")
	public void checkBingingEamilInformation(HttpServletResponse response,
			@RequestParam(value = "Vid", required = false) String Vid,@RequestParam(value = "UUID", required = false) String UUID1,@RequestParam(value = "Email", required = false) String Email,@RequestParam(value = "CreateTime", required = false) String CreateTime)
			throws IOException {
		System.out.println("CreateTime:"+CreateTime+"Email:"+Email+"Vid:"+Vid+"UUID:"+UUID1);
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	   VisitorBindingEmailService visitorBindingEmailService=(VisitorBindingEmailService) factory.getBean("visitorBindingEmailService");
	   VisitorBindingEmailModel visitorBindingEmailModel=new VisitorBindingEmailModel();
	   visitorBindingEmailModel.setVid(Vid);
	   visitorBindingEmailModel.setEmail(Email);
	   visitorBindingEmailModel.setUUID(UUID1);
	   visitorBindingEmailModel.setCreateTime(CreateTime);
	   Writer writer = response.getWriter();
	   if(visitorBindingEmailService.checkBindingEmailInformation(visitorBindingEmailModel))
		   writer.write("<h2>Success</h2>");
	   else {
		   writer.write("<h2>Fail</h2>");
	}
	   
		System.out.println("邮箱验证");
		
	}
   public static void main(String[]args) throws ParseException
   {
	   ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	   VisitorBindingEmailService visitorBindingEmailService=(VisitorBindingEmailService) factory.getBean("visitorBindingEmailService");
	   VisitorBindingEmailModel visitorBindingEmailModel=new VisitorBindingEmailModel();
//	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		String Time=df.format(new Date());
//		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
//		String Time1=df1.format(new Date());
//		Date d1 = df.parse(Time);
//		Date d2 = df1.parse(Time1);
//		long diff = d2.getTime() - d1.getTime();
//		long minutes = diff / (1000 * 60 );
//		System.out.println(d1);
//		System.out.println(d2);
//		System.out.println("minutes:"+minutes);
	   visitorBindingEmailModel.setVisitor("1");
	   visitorBindingEmailModel.setEmail("154468476@qq.com");
	   try {
		visitorBindingEmailService.sendBingEmailInformation(visitorBindingEmailModel);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	   visitorBindingEmailModel.setVid("1");
//	   visitorBindingEmailModel.setEmail("154468476@qq.com");
//	   visitorBindingEmailModel.setUUID("f668914d-8436-40aa-9895-fb5613303ea9");
//	   visitorBindingEmailModel.setCreateTime("2017-1-27 10:32:00");
//	   System.out.println(visitorBindingEmailService.checkBindingEmailInformation(visitorBindingEmailModel));
  }
}
