package com.data.service.adminService.adminFunctions.loginService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;




import com.data.dao.singleForm.tb_adminDaoImp;

import com.data.model.tb_adminModel;


public class LoginServiceImp implements LoginService{
	
	static List<Object> param;
	List<Object> tb_adminmodellist;
	public int Login(tb_adminModel tb_adminmodel){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		 tb_adminDaoImp tb_admindaoimp=(tb_adminDaoImp) factory.getBean("tb_admindaoimp");
		 
		 try{
			 String sql="select * from tb_admin where Account=?  ";
			 param=new LinkedList<Object>();
			 //填sql语句参数的
			 param.add(tb_adminmodel.getAccount());	
			 tb_adminmodellist=tb_admindaoimp.query(sql, param);	

			 
			 if(tb_adminmodellist.size()==0)
			 {System.out.println("??????????????!!");
					return 1;}
			 else{
				 	//将查询返回的模型层集合转为模型层
					 tb_adminModel tb_adminmodel2=(tb_adminModel) tb_adminmodellist.get(0);
					 //用户输入的密码
					 String password=tb_adminmodel.getPassword();
					 //查找得到的密码
					 String password2=tb_adminmodel2.getPassword();
	
					 
				     if(password.equals(password2))
				     //密码正确则进行管理员类型判断
				     {
					     if(tb_adminmodel.getPrivilege()=="a")return 3; //超级管理员
					     else if(tb_adminmodel.getPrivilege()=="s")return 4;//景区管理员
					     else return 5;//审核管理员
				     }
				    	
				     else 
				    	 //密码错误返回2
				    	 return 2;
			 }

		 }catch (Exception e) {
			  	//查找出错作为用户不存在返回1
				return 1;
		 }
	}
}