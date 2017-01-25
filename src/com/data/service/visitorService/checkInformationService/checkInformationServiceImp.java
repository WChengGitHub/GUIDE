package com.data.service.visitorService.checkInformationService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.dao.singleForm.tb_travelsDao;
import com.data.dao.singleForm.tb_visitorDao;
import com.data.dao.singleForm.tb_visitorDaoImp;
import com.data.model.tb_visitorModel;

public class checkInformationServiceImp implements checkInformationService{

	static String Vid;
	static List<Object> param,param2;
	List<Object> tb_visitormodellist;
	@Override
	public List<Object> CheckInformation(tb_visitorModel tb_visitormodel) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		tb_visitorDao tb_visitordao=(tb_visitorDao) factory.getBean("visitorDao");
		tb_visitorDaoImp tb_visitordaoimp=(tb_visitorDaoImp) factory.getBean("tb_visitordaoimp");
		
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
		
		String sql2=" select * FROM tb_visitor WHERE Vid=? ";
		param2=new LinkedList<Object>();
		param2.add(Vid);
		tb_visitormodellist=tb_visitordaoimp.query(sql2, param2);
		//测试
		System.out.println(tb_visitormodellist);
		return tb_visitormodellist;
	}
}
