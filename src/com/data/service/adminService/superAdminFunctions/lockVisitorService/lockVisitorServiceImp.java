package com.data.service.adminService.superAdminFunctions.lockVisitorService;


import org.springframework.context.support.ClassPathXmlApplicationContext;



import com.data.dao.singleForm.tb_visitorDaoImp;
import com.data.model.tb_visitorModel;
public class lockVisitorServiceImp implements lockVisitorService{
	final int lockfailed=4,lock=5;
	@Override
	public int lockVisitor(tb_visitorModel tb_visitormodel)  {
	@SuppressWarnings("resource")
	ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
	tb_visitorDaoImp tb_visitordaoimp=(tb_visitorDaoImp) factory.getBean("tb_visitordaoimp");
	
	try{
		String sql=" UPDATE tb_visitor SET LockState = 1 WHERE Vid = ?";
		
		tb_visitordaoimp.update(tb_visitormodel, sql);
	 }catch (Exception e) {
		  	System.out.println("lock error");
		  	return lockfailed;
	 }
	return lock;
		 
	}
}

