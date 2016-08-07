package com.data.controller.visitorController;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.model.tb_adviceModel;
import com.data.model.tb_visitorModel;
import com.data.service.visitorService.checkAdviceService.CheckAdvice;
import com.data.service.visitorService.checkAdviceService.SendEmail;

@Controller
public class CheckAdviceController {
	@RequestMapping("/getAdviceNumber")
	@ResponseBody
	public void getAdviceNumber(HttpServletResponse response)
			throws IOException {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdvice checkAdvice = (CheckAdvice) factory.getBean("CheckAdvice");
		//System.out.println(checkAdvice.getAdviceNumber());
		Writer writer = response.getWriter();
		//long number = checkAdvice.getAdviceNumber();
		int number=checkAdvice.queryAdviceNumber();
		String json = "{\"number\":" + number + "}";
		writer.write(json);
		//System.out.println("/getAdviceNumber");
	}

	@RequestMapping("/getAdviceRecordList")
	@ResponseBody
	public List<Object> getAdviceRecordList() throws IOException {
		//System.out.println("getAdviceRecordList");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdvice checkAdvice = (CheckAdvice) factory.getBean("CheckAdvice");
		List<Object> adviceRecordList = new LinkedList<Object>();
		//adviceRecordList = checkAdvice.getAdviceRecord();
		adviceRecordList=checkAdvice.queryAdviceRecord();
		if (adviceRecordList != null)
		{
			//System.out.println(adviceRecordList.size());
			//System.out.println("数据提取成功");
			JSONArray jsonArray=JSONArray.fromObject(adviceRecordList);
			System.out.println(jsonArray);
			//tb_adviceModel t=(tb_adviceModel) adviceRecordList.get(0);
			//System.out.println(t.getADid());
			return jsonArray;
			
		}
		
		return null;
	}
	
	@RequestMapping("/getAdvice")
	@ResponseBody
    public JSONArray getAdvice(@RequestParam(value = "ADid", required = false) String ADid)
    {
		//System.out.println(ADid);
		//System.out.println(ADid.length()==0);
		if(ADid.length()==0)
			return null;
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdvice checkAdvice = (CheckAdvice) factory.getBean("CheckAdvice");
		tb_adviceModel tbAdviceModel=new tb_adviceModel();
		tbAdviceModel.setADid(ADid);
		//tbAdviceModel=checkAdvice.getAdvice(tbAdviceModel);
		tbAdviceModel=(tb_adviceModel) checkAdvice.queryTitleAndAdvice(ADid);
		JSONArray jsonArray=JSONArray.fromObject(tbAdviceModel);
		System.out.println(jsonArray);
		return jsonArray;
    }
	@RequestMapping("/sendAdviceStatus")
	@ResponseBody
	public void sendAdviceStatus(HttpServletResponse response,@RequestParam(value = "ADid", required = false) String ADid,@RequestParam(value = "Vid", required = false) String Vid,@RequestParam(value = "Status", required = false) String Status) throws IOException
	{
		//System.out.println("sendAdviceStatus");
		//System.out.println("ADid:"+ADid+" Vid:"+Vid+" Status:"+Status);
		if(ADid.length()==0||Vid.length()==0||Status.length()==0)
			return;
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdvice checkAdvice = (CheckAdvice) factory.getBean("CheckAdvice");
		tb_adviceModel adviceModel=new tb_adviceModel();
		adviceModel.setADid(ADid);
		adviceModel.setStatus(Status);
		adviceModel.setVid(Vid);
		//System.out.println("chanceAdviceStatus:before");
		//checkAdvice.chanceAdviceStatus(adviceModel);
		checkAdvice.updateAdviceStatus(ADid, Status);
		//System.out.println("chanceAdviceStatus:after");
		//System.out.println(ADid+" "+Vid+" "+Status);
		//System.out.println("Status:"+Status);
		//String string="f";
		//System.out.println(Status.equals(string));
		if(Status.equals("f"))
		{   
			System.out.println("Vid:Hello"+Vid);
			tb_visitorModel visitorModel=new tb_visitorModel();
			//visitorModel=checkAdvice.getAdviceEmail(adviceModel);
			if(visitorModel==null)
				return;
			//System.out.println("visitorEmail:"+visitorModel.getEmail());
		    String title="Hello";
		    String content="Hello　World";	
			//SendEmail.sendEmail(visitorModel.getEmail(),title,content);
		    SendEmail.sendEmail(checkAdvice.queryVisitorEmail(Vid), title, content);
			System.out.println("SendEmail:successful");
		}
		Writer writer = response.getWriter();
		String json = "{\"status\":\"success\"}";
		writer.write(json);
	}
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdvice checkAdvice = (CheckAdvice) factory.getBean("CheckAdvice");
		//checkAdvice.getAdviceNumber();
		/*System.out.println(checkAdvice.queryAdviceNumber());
		List<Object> adviceRecordList = new LinkedList<Object>();
		adviceRecordList = checkAdvice.queryAdviceRecord();
		if (adviceRecordList != null)
			System.out.println(adviceRecordList.size());
		else {
			System.out.println(0);
		}*/
		//tb_adviceModel tbAdviceModel=(tb_adviceModel) checkAdvice.queryTitleAndAdvice("201608031535331");
		//checkAdvice.updateAdviceStatus("201608020854105","0");
		//System.out.println(checkAdvice.queryVisitorEmail("1"));
		//String visitorEmail=checkAdvice.queryVisitorEmail("1");
		//SendEmail.sendEmail(visitorEmail);
		//System.out.println(tbAdviceModel.getTitle()+" "+tbAdviceModel.getAdvice());
	}
}
