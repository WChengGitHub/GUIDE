package com.data.controller.visitorController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.data.service.visitorService.adviceService.AdviceService;

@Controller
public class AdviceController {
	@RequestMapping(value = "/visitorAdvice")
	public String addviceController(HttpServletResponse response,
			@RequestParam(value = "visitor", required = false) String visitor,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "advice", required = false) String advice,
			@RequestParam(value = "type", required = false) String type)
			throws IOException {
		visitor = java.net.URLDecoder.decode(visitor, "UTF-8");
		title = java.net.URLDecoder.decode(title, "UTF-8");
		advice = java.net.URLDecoder.decode(advice, "UTF-8");
		if(visitor.length()==0||title.length()==0||advice.length()==0||type.length()==0)
			return null;
		//System.out.println(visitor + "," + title + "," + advice + " " + type);
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AdviceService adviceService = (AdviceService) factory
				.getBean("AdviceService");
		try{
		adviceService.addAdviceService(title, advice, type, visitor);
		}
		catch(Exception e)
		{
			return null;
		}
		Writer writer=response.getWriter();
		writer.write("success");
		return null;
	}
	/*
	 * public static void main(String[]args) { ApplicationContext factory=new
	 * ClassPathXmlApplicationContext("applicationContext.xml"); AdviceService
	 * adviceService=(AdviceService) factory.getBean("AdviceService");
	 * adviceService.addAdviceService("hello", "wordl","s", "guide"); }
	 */
}
