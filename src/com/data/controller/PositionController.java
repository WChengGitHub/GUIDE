package com.data.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.service.PositionService;
@Controller
public class PositionController {
	@RequestMapping(value="/MyCookiel")
	public @ResponseBody String testRequest(@RequestParam(value="province",required=false)String province,@RequestParam(value="city",required=false)String city,@RequestParam(value="area",required=false)String area) throws UnsupportedEncodingException
	{
		System.out.println(11);
		System.out.println(java.net.URLDecoder.decode(province,"UTF-8"));
		province=java.net.URLDecoder.decode(province,"UTF-8");
		city=java.net.URLDecoder.decode(city,"UTF-8");
		area=java.net.URLDecoder.decode(area,"UTF-8");
		System.out.println(province+","+city+","+area);
		ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
		PositionService s=(PositionService) factory.getBean("PositionService");
		System.out.println(s.queryPosition(province,city,area));
		return "newFile";
	}
	/*public static void main(String[]args)
	{
	ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	PositionService s=(PositionService) factory.getBean("PositionService");
	System.out.println(s.queryPosition("广东省","惠州市","惠城区东江西路"));
	}*/
}
