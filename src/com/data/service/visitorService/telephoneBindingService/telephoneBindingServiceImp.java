package com.data.service.visitorService.telephoneBindingService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.dao.singleForm.tb_visitorDao;
import com.data.dao.singleForm.tb_visitorDaoImp;
import com.data.model.tb_visitorModel;

public class telephoneBindingServiceImp implements telephoneBindingService{
	static String Vid;
	static List<Object> param;
	List<Object> tb_visitormodellist;
	@Override
	public int telephoneBinding(tb_visitorModel tb_visitormodel) {
		// TODO Auto-generated method stub
			@SuppressWarnings("resource")
			ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
			tb_visitorDao tb_visitordao=(tb_visitorDao)factory.getBean("visitorDao");
		try{
			//取visitor
			String visitor=tb_visitormodel.getVisitor();
			//sql
			String sql=" select * from tb_visitor where Visitor=? ";
			param=new LinkedList<Object>();
			param.add(visitor);
			tb_visitormodellist=tb_visitordao.queryVid(sql, param);
			tb_visitorModel tb_visitormodel2=(tb_visitorModel) tb_visitormodellist.get(0);
			Vid=tb_visitormodel2.getVid();
			//测试查到的Vid
			System.out.println(Vid+"test checkTravels Vid");
			tb_visitormodel.setVid(Vid);
			
			String sql2=" UPDATE  tb_visitor  set Telephone=? WHERE Vid=?; ";
			tb_visitordao.update2(tb_visitormodel, sql2);
			
			
			return 1;
		}
		catch (Exception e) {
		//输出出现的异常 e就是出现的异常
		System.out.println(e);
		System.out.println("Register error");
			return 0;
	  }
	}

}
