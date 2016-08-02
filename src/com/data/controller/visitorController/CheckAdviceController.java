package com.data.controller.visitorController;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.service.visitorService.checkAdviceService.CheckAdvice;
@Controller
public class CheckAdviceController {
	@RequestMapping("/getAdviceNumber")
	@ResponseBody
	public String getAdviceNumber(HttpServletResponse response) throws IOException
	{
		ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
  	    CheckAdvice checkAdvice=(CheckAdvice) factory.getBean("CheckAdvice");
  	    System.out.println(checkAdvice.queryAdviceNumber());
		Writer writer=response.getWriter();
		int number=checkAdvice.queryAdviceNumber();
		String json="{\"number\":"+number+"}";
		writer.write(json);
		System.out.println("/getAdviceNumber");
		return null;
	}
	/*
      public static void main(String []args){
    	  ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
    	  CheckAdvice checkAdvice=(CheckAdvice) factory.getBean("CheckAdvice");
    	  System.out.println(checkAdvice.queryAdviceNumber());
      }*/
}
