package com.data.controller.visitorController;

import java.io.UnsupportedEncodingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.data.service.visitorService.adviceService.AdviceService;
@Controller
public class AddviceController {
	@RequestMapping(value = "/visitorAdvice")
	public String addviceController(@RequestParam(value = "visitor", required = false) String visitor,@RequestParam(value = "title", required = false) String title,@RequestParam(value = "advice", required = false) String advice,@RequestParam(value = "type", required = false) String type)throws UnsupportedEncodingException
	{
		visitor = java.net.URLDecoder.decode(visitor, "UTF-8");
		title = java.net.URLDecoder.decode(title, "UTF-8");
		advice = java.net.URLDecoder.decode(advice, "UTF-8");
		 System.out.println(visitor+","+title+","+advice+" "+type);
		 ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
  	   AdviceService adviceService=(AdviceService) factory.getBean("AdviceService");
  	   adviceService.addAdviceService(title, advice, type, visitor);
  	   return null;
	}
      /* public static void main(String[]args)
       {
    	   ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
    	   AdviceService adviceService=(AdviceService) factory.getBean("AdviceService");
    	   adviceService.addAdviceService("hello", "wordl","s", "guide");
       }*/
}
