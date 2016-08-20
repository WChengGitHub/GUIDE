package com.data.service.adminService.superAdminFunctions.deleteAdminService;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.dao.singleForm.tb_adminDaoImp;
import com.data.model.tb_adminModel;

public class deleteAdminServiceImp implements deleteAdminService{
	final int deletefailed=4,delete=5;
	@Override
	public int deleteAdmin(tb_adminModel tb_adminmodel)  {
	@SuppressWarnings("resource")
	ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
	tb_adminDaoImp tb_admindaoimp=(tb_adminDaoImp) factory.getBean("tb_admindaoimp");
	
	try{
		String sql=" DELETE FROM tb_admin WHERE Account=?";
		
		tb_admindaoimp.update(tb_adminmodel, sql);
	 }catch (Exception e) {
		//输出出现的异常 e就是出现的异常
		System.out.println(e);
		System.out.println("lock error");
		return deletefailed;
	 }
	return delete;
		 
	}
}

