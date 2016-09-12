package com.data.controller.adminController.checkAdminController;

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

import com.data.model.CheckAdviceModel;
import com.data.model.tb_adminModel;
import com.data.service.adminService.checkAdminFunctions.checkAdvice.CheckAdviceService;
import com.data.service.visitorService.checkAndReplyAdviceService.CheckAndReplyAdvice;

@Controller
public class CheckAdviceController {
	@RequestMapping("/getCheckAdviceNumber")
	@ResponseBody
	public void getAdviceNumber(
			HttpServletResponse response,
			@RequestParam(value = "Privilege", required = false) String Privilege)
			throws IOException {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdviceService checkAdviceService = (CheckAdviceService) factory
				.getBean("CheckAdviceService");
		int adviceNumber = 0;
		if (Privilege.length() == 0) {
			System.out.println("管理员权限获取失败");
		} else {
			CheckAdviceModel checkAdviceModel = new CheckAdviceModel();
			checkAdviceModel.setPrivilege(Privilege);
			adviceNumber = checkAdviceService.getAdviceNumber(checkAdviceModel);
			System.out.println("AdviceNumber:" + adviceNumber);
		}

		Writer writer = response.getWriter();
		String json = "{\"adviceNumber\":" + adviceNumber + "}";
		writer.write(json);
	}

	@RequestMapping("/getCheckAdvices")
	@ResponseBody
	public JSONArray getCheckAdvices() throws IOException {
		// System.out.println("getAdviceRecordList");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdviceService checkAdviceService = (CheckAdviceService) factory
				.getBean("CheckAdviceService");
		List<Object> list;
		list = checkAdviceService.getAdvices();
		System.out.println(list);
		if (list != null) {
			// System.out.println(adviceRecordList.size());
			// System.out.println("数据提取成功");
			JSONArray jsonArray = JSONArray.fromObject(list);
			System.out.println(jsonArray);
			// tb_adviceModel t=(tb_adviceModel) adviceRecordList.get(0);
			// System.out.println(t.getADid());
			return jsonArray;

		}

		return null;
	}

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CheckAdviceService checkAdviceService = (CheckAdviceService) factory
				.getBean("CheckAdviceService");
		CheckAdviceModel checkAdviceModel = new CheckAdviceModel();
		checkAdviceModel.setPrivilege("s");
		checkAdviceModel.setADid("1");
		checkAdviceService.setAdminAdvice(checkAdviceModel);
//		List<Object> list;
//		list = checkAdviceService.getSuperAdminAids();
//		tb_adminModel adminModel=(tb_adminModel) list.get(0);
//		System.out.println(adminModel.getAid());
//		JSONArray jsonArray = JSONArray.fromObject(list);
//		System.out.println(jsonArray);
		/*checkAdviceModel.setPrivilege("c");
		List<Object> list;
		list = checkAdviceService.getAdvices();
		System.out.println(list);
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray);*/
		// System.out.println("AdviceNumber:"
		// + checkAdviceService.getAdviceNumber(checkAdviceModel));
       
	}
}
