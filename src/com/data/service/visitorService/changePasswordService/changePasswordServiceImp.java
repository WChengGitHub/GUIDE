package com.data.service.visitorService.changePasswordService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.dao.singleForm.tb_travelsDaoImp;
import com.data.dao.singleForm.tb_visitorDao;
import com.data.dao.singleForm.tb_visitorDaoImp;
import com.data.model.tb_visitorModel;

public class changePasswordServiceImp implements changePasswordService{
	static String Vid;
	static List<Object> param;
	List<Object> tb_visitormodellist;
	static int changeSuccess=1,changeFail=0;
	@Override
	public int changePassword(tb_visitorModel tb_visitormodel) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		tb_visitorDao tb_visitordao=(tb_visitorDao) factory.getBean("visitorDao");
	try {
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
		//sql语句
		String sql2 = " UPDATE tb_visitor  SET Password = ? WHERE Vid=?; ";
		//调用tb_visitordaoimp中的add函数
		tb_visitordao.update(tb_visitormodel, sql2);
		//测试
		System.out.println("changePassword success ");
		return changeSuccess;
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("changePassword  fail");
		return changeFail;
	}
 }
}


