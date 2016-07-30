package com.data.service.visitorService.loginService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;


import com.data.dao.singleForm.tb_visitorDaoImp;
import com.data.model.tb_visitorModel;

public class LoginServiceImp implements LoginService{
	
	static List<Object> param;
	List<Object> tb_visitormodellist;
	public int Login(tb_visitorModel tb_visitormodel){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		 tb_visitorDaoImp tb_visitordaoimp=(tb_visitorDaoImp) factory.getBean("tb_visitordaoimp");
		 
		 try{
			 String sql="select * from tb_visitor where Visitor=?  ";
			 param=new LinkedList<Object>();
			 //填sql语句参数的
			 param.add(tb_visitormodel.getVisitor());
			// param.add(tb_visitormodel.getPassword());
			//System.out.println(param.get(1)+"8888");	
			 tb_visitormodellist=tb_visitordaoimp.query(sql, param);	
			 //System.out.println("List test"+tb_visitormodellist.get(0)+"666");
			 //System.out.println(tb_visitormodelList.get(1)+"444");
			 
			 if(tb_visitormodellist.size()==0)
					return 1;
			 else{
				 	//将查询返回的模型层集合转为模型层
					 tb_visitorModel tb_visitormodel2=(tb_visitorModel) tb_visitormodellist.get(0);
					 //用户输入的密码
					 String password=tb_visitormodel.getPassword();
					 //查找得到的密码
					 String password2=tb_visitormodel2.getPassword();
	
					 
				     if(password.equals(password2))
				    	  //密码正确返回0
				    	 return 0;
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
