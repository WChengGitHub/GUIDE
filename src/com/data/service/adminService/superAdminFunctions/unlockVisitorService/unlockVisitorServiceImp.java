package com.data.service.adminService.superAdminFunctions.unlockVisitorService;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.dao.singleForm.tb_visitorDaoImp;
import com.data.model.tb_visitorModel;
public class unlockVisitorServiceImp implements unlockVisitorService{
	final int unlockfailed=4,unlock=6;
	@Override
	public int unlockVisitor(tb_visitorModel tb_visitormodel) {
	@SuppressWarnings("resource")
	ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
	tb_visitorDaoImp tb_visitordaoimp=(tb_visitorDaoImp) factory.getBean("tb_visitordaoimp");
	
	try{
		String sql=" UPDATE tb_visitor SET LockState = 0 WHERE Vid = ?";
		
		tb_visitordaoimp.update(tb_visitormodel, sql);
	 }catch (Exception e) {
		  	System.out.println("lock error");
		  	return unlockfailed;
	 }
	return unlock;
		 
	}
}

