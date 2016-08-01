package com.data.controller.visitorController;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.service.visitorService.checkAdviceService.CheckAdvice;

public class CheckAdviceController {
      public static void main(String []args){
    	  ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
    	  CheckAdvice checkAdvice=(CheckAdvice) factory.getBean("CheckAdvice");
    	  System.out.println(checkAdvice.queryAdviceNumber());
      }
}
