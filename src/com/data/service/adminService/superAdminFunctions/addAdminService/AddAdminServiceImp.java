package com.data.service.adminService.superAdminFunctions.addAdminService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.model.tb_adminModel;
import com.data.dao.singleForm.tb_adminDaoImp;
public class AddAdminServiceImp implements AddAdminService{
	
	final int addadminfailed=0,addadminsuccess=1,added=2;
	static List<Object> param;
	List<Object> tb_adminmodellist;
	
	@Override
	public int addAdmin(tb_adminModel tb_adminmodel) {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		tb_adminDaoImp tb_admindaoimp=(tb_adminDaoImp) factory.getBean("tb_admindaoimp");
		 
	    try{
			 String sql="select * from tb_admin where Account=? ";
			 param=new LinkedList<Object>();
			 //填sql语句参数的
			 param.add(tb_adminmodel.getAccount());
			 tb_adminmodellist=tb_admindaoimp.query(sql, param);
			 
			 //判断用户是否已存在
			 if(tb_adminmodellist.size()!=0) return added;
			 else{
				 String sqlInsert = "INSERT INTO tb_admin (Account, Password, Aid, Privilege)VALUES (?, ?, ?, ?);";
				 //getTime
				 Calendar cal1 = Calendar.getInstance();  
		         TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       
		         java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
		         //test
		         System.out.println(sdf.format(cal1.getTime())); 
		         //随机数
		         int randomNumber = (int)(Math.random() * 10 );
		         System.out.println(randomNumber);//test
				 tb_adminmodel.setAid(sdf.format(cal1.getTime())+randomNumber);
				 System.out.println(tb_adminmodel.getAid());//test
				//判断是否溢出
				 if(tb_adminmodel.getAccount().length()>11 ||
						 tb_adminmodel.getPassword().length()>33 ||
						 tb_adminmodel.getPrivilege().length()>2)
					 return addadminfailed;
				 else{
				 tb_admindaoimp.add(tb_adminmodel, sqlInsert);
				 System.out.println("test1 addadmin");//test
				 }
				}
			 return addadminsuccess;
			 } catch (Exception e) {
				 	//输出出现的异常 e就是出现的异常
				    System.out.println(e);
					System.out.println("addadmin error");
					//表示注册失败
					return addadminfailed;
	//			e.printStackTrace();
		  }
	}

}
