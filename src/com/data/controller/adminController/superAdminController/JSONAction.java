package com.data.controller.adminController.superAdminController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;



import com.data.dao.singleForm.tb_visitorDaoImp;

import net.sf.json.JSONObject;

public class JSONAction extends HttpServlet implements Controller{
public JSONAction() {
   super();
}
public void destroy() {
   super.destroy(); 
}


static List<Object> param;
List<Object> tb_visitormodellist;

@Override
public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		tb_visitorDaoImp tb_visitordaoimp=(tb_visitorDaoImp) factory.getBean("tb_visitordaoimp");
	
		System.out.println("VVVVVVVVV"+request.getParameter("a"));
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		List list = new ArrayList();
	   
	   
		String sql="select * from tb_visitor  ";
		param=new LinkedList<Object>();
		tb_visitormodellist=tb_visitordaoimp.query(sql, param);	

		list=tb_visitormodellist;
	   
	  
		Map map = new HashMap();
		map.put("list",list);
		JSONObject jso = JSONObject.fromObject(map);
		System.out.println(jso);
		out.print(jso);
		out.flush();
		out.close();
		return null;
}
}
