package com.data.loginservice;

import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;


import com.data.dao.*;
import com.data.model.User;

public class LoginServiceImp implements LoginService{
	
	public int Login(User U){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		 QueryUserDao Dq=(QueryUserDao) factory.getBean("QueryDao");
		 
		 try{
		 int count1=Dq.query_user(U.getName());
		 if(count1==1){
			 int count2=Dq.query_password(U.getName(),U.getPassword());
			 if(count2==1){
					return 0;//密码正确返回0
				}
				else
					return 2;//密码错误返回2
			}
		 else
			return 1;//cout1为零 即查找到0个 用户不存在返回1
		 }catch (Exception e) {
				return 1;//查找出错作为用户不存在返回1
		 }

	}
}
	
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
//		 JdbcTemplate jt=impdao.getJdbcTemple();
//		 try {
//				String 	sql="select count(*) from tb_visitor "+" where Vid= "+Name;
//				int count=jt.queryForInt(sql);
//				if(count==1){
//					String 	sql2="select Telephone from tb_visitor "+" where Vid= "+Name;
//					String Telephone=jt.queryForObject(sql2,String.class);
//					if(Password.equals(Telephone)==true){
//						return 0;//密码正确返回0
//					}
//					else
//						return 2;//密码错误返回2
//				}
//				else
//					return 1;//cout为零 即查找到0个 用户不存在返回1
//			} catch (Exception e) {
//				return 1;//查找出错作为用户不存在返回1
//			}
