package com.data.service.visitorService.recordTravelService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.dao.singleForm.tb_travelsDaoImp;
import com.data.dao.singleForm.tb_visitorDaoImp;
import com.data.model.tb_travelsModel;
import com.data.model.tb_visitorModel;



public class recordTravelServiceImp implements recordTravelService{

	final int recordsuccess=1,recordfail=0;
	
	static List<Object> param;
	List<Object> tb_visitormodellist;
	
	@Override
	public int Publish(tb_travelsModel tb_travelsmodel) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		tb_travelsDaoImp tb_travelsdaoimp=(tb_travelsDaoImp) factory.getBean("tb_travelsdaoimp");
		
		try {
			//sql语句
			String sqlInsert = "INSERT INTO tb_travels (Tid, Text, Vid, Sid)VALUES (?, ?, ?, ?);";
			//调用travelsdaoimp中的add函数
			tb_travelsdaoimp.add(tb_travelsmodel, sqlInsert);
			//测试
			System.out.println("test1 record ");
			return recordsuccess;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("record add fail");
			return recordfail;
		}
		
		
	}
	@Override
	public String GetVid(tb_visitorModel tb_visitormodel){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		 tb_visitorDaoImp tb_visitordaoimp=(tb_visitorDaoImp) factory.getBean("tb_visitordaoimp");
		String vid = null;
		String sql=" select * from tb_visitor where Visitor=? ";
		param=new LinkedList<Object>();
		 //填sql语句参数的
		 param.add(tb_visitormodel.getVisitor());
	
		 tb_visitormodellist=tb_visitordaoimp.query(sql, param);	
		 
		 tb_visitorModel tb_visitormodel2=(tb_visitorModel) tb_visitormodellist.get(0);
		 
		 vid=tb_visitormodel2.getVid();
		return vid;
		
		
	}

}
