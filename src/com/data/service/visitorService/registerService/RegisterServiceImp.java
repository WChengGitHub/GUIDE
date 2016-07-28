package com.data.service.visitorService.registerService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.model.tb_visitorModel;
import com.data.dao.singleForm.QueryVisitorDao;

import com.data.dao.singleForm.tb_visitorDaoImp;
public class RegisterServiceImp implements RegisterService{

	@Override
	public int Register(tb_visitorModel tb_visitorModel) {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		 tb_visitorDaoImp visitordaoimp=(tb_visitorDaoImp) factory.getBean("tb_visitordaoimp");
		 QueryVisitorDao queryvisitordao=(QueryVisitorDao) factory.getBean("QueryVisitorDao");
		 try{
			 //String 	sql="select count(*) from tb_visitor  where Visitor= ? "; 之后改QueryVisitorDao时用到的外部sql语句
		int count=queryvisitordao.queryVisitor(tb_visitorModel.getVisitor());
		
		if(count==1)return 2;
		else{
			String sqlInsert = "INSERT INTO tb_visitor (Visitor, Password, Vid)VALUES (?, ?, ?);";
			
			Calendar cal1 = Calendar.getInstance();  
	        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       //非常关键的！！！ 
	        java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
	        
	        System.out.println(sdf.format(cal1.getTime()));//test 
			tb_visitorModel.setVid(sdf.format(cal1.getTime()));
			
			System.out.println(tb_visitorModel.getVid());//test
			 visitordaoimp.add(tb_visitorModel, sqlInsert);
			 System.out.println("test1");//test
			}
			
		 } catch (Exception e) {
				System.out.println("error2");
				return 0;//表示注册失败
//			e.printStackTrace();
		}
		return 1;//表示注册成功
	}

}
