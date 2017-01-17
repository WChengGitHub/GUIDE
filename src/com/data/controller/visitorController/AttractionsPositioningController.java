package com.data.controller.visitorController;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.data.model.VisitorPositioningModel;
import com.data.model.tb_cityModel;
import com.data.service.visitorService.attractionsPositioningService.attractionsPositioningServiceImp;

@Controller //景点定位
public class AttractionsPositioningController {
	@RequestMapping("/attractionsPositioning")
	@ResponseBody
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		//解决request中文"？？？"
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//检查是否运行到这里	
		System.out.println("test                                             test");
		
		//here
		String city = request.getParameter("City");
		String latitude = request.getParameter("Latitude");
		String longitude = request.getParameter("Longitude");
		System.out.println(city+"test city ");
		System.out.println(longitude+ "test Longitude");
	
		//经纬度转为double型
		double lat = Double.parseDouble(latitude);
		double lon = Double.parseDouble(longitude);
		try {
			@SuppressWarnings("resource")
			//应用上下文
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
			//将数据传给Model
			VisitorPositioningModel visitorPositioningModel=(VisitorPositioningModel)factory.getBean("visitorPositioningModel");
			attractionsPositioningServiceImp apServiceImp=(attractionsPositioningServiceImp)factory.getBean("attractionsPositioningServiceImp");
			 //检查是否运行到这里
			 System.out.println("test controller");
	         
	         //传给model
			 visitorPositioningModel.setCity(city);
			 visitorPositioningModel.setLatitude(lat);
			 visitorPositioningModel.setLongitude(lon);
			 //测试
			 System.out.println(visitorPositioningModel.getCity()+"              test           test  ");
			 System.out.println(visitorPositioningModel.getLatitude()+"      test lat");
			 
			 //发表情况state,成功与否1/0表示
			 String state=apServiceImp.AttractionsPositioning(visitorPositioningModel);
			 out.print(state);			 
			 System.out.println(state+"        test    state      ");
		} catch (Exception e) {
			System.out.println("error5");
		e.printStackTrace();
		}
	//	}
	return null;

		
	}
}	


