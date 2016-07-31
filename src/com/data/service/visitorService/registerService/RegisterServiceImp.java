package com.data.service.visitorService.registerService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.model.tb_visitorModel;
import com.data.dao.singleForm.tb_visitorDaoImp;
public class RegisterServiceImp implements RegisterService{
	
	static List<Object> param;
	List<Object> tb_visitormodellist;
	
	@Override
	public int Register(tb_visitorModel tb_visitormodel) {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		 tb_visitorDaoImp tb_visitordaoimp=(tb_visitorDaoImp) factory.getBean("tb_visitordaoimp");
		 
		 try{
			 String sql="select * from tb_visitor where Visitor=? ";
			 param=new LinkedList<Object>();
			 //填sql语句参数的
			 param.add(tb_visitormodel.getVisitor());
			// param.add(tb_visitormodel.getPassword());
			//System.out.println(param.get(1)+"8888");	
			 tb_visitormodellist=tb_visitordaoimp.query(sql, param);	
			 if(tb_visitormodellist.size()!=0)
					return 2;
			 else{
				 String sqlInsert = "INSERT INTO tb_visitor (Visitor, Password, Vid, Email)VALUES (?, ?, ?, ?);";
				System.out.println("0000000000");
				 //getTime
				 Calendar cal1 = Calendar.getInstance();  
		         TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       
		         java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
		         //test
		         System.out.println(sdf.format(cal1.getTime())); 
		         //随机数
		         int randomNumber = (int)(Math.random() * 10 );
		         System.out.println(randomNumber);//test
				 tb_visitormodel.setVid(sdf.format(cal1.getTime())+randomNumber);
				 System.out.println(tb_visitormodel.getVid());//test
				//判断是否溢出
				 if(tb_visitormodel.getVisitor().length()>11 ||
						 tb_visitormodel.getPassword().length()>33 ||
						 tb_visitormodel.getEmail().length()>31)
					 return 0;
				 else{
				 tb_visitordaoimp.add(tb_visitormodel, sqlInsert);
				 System.out.println("test1");//test
				 }
				}
				
			 } catch (Exception e) {
					System.out.println("error2");
					//表示注册失败
					return 0;
	//			e.printStackTrace();
		  }
		  //表示注册成功
		  return 1;
	}

}
