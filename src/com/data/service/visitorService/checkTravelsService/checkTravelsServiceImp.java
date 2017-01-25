package com.data.service.visitorService.checkTravelsService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.dao.singleForm.tb_travelsDao;
import com.data.dao.singleForm.tb_visitorDao;
import com.data.model.tb_visitorModel;

public class checkTravelsServiceImp implements checkTravelsService{
	
	static String Vid;
	static List<Object> param,param2;
	List<Object> tb_visitormodellist;
	List<Object> checktravelmodellist;
	@Override
	public List<Object> CheckTravels(tb_visitorModel tb_visitormodel) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		tb_visitorDao tb_visitordao=(tb_visitorDao) factory.getBean("visitorDao");
		tb_travelsDao tb_travelsdao=(tb_travelsDao) factory.getBean("tb_travelsdao");
		
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
		
		String sql2=" select tb_travels.* ,tb_spot.Spot FROM tb_travels,tb_spot WHERE tb_travels.Sid=tb_spot.Sid&&tb_travels.Vid=? ";
		param2=new LinkedList<Object>();
		param2.add(Vid);
		checktravelmodellist=tb_travelsdao.query(sql2, param2);
		return checktravelmodellist;
	}

}
