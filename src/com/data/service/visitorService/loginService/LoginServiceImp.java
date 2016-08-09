package com.data.service.visitorService.loginService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;




import com.data.dao.singleForm.tb_visitorDaoImp;
import com.data.model.tb_visitorModel;

public class LoginServiceImp implements LoginService{
	
	final int locked=3,passwordwrong=2,undefindvisitor=1,loginsuccess=0;
	
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
			 {System.out.println("??????????????!!");
					return 1;}
			 else{
				 	//将查询返回的模型层集合转为模型层
					 tb_visitorModel tb_visitormodel2=(tb_visitorModel) tb_visitormodellist.get(0);
					 //用户输入的密码
					 String password=tb_visitormodel.getPassword();
					 //查找得到的密码
					 String password2=tb_visitormodel2.getPassword();
					 //是否冻结的字段
					 String lockstate=tb_visitormodel2.getLockstate();
					 System.out.println(lockstate+"lockstate test");
					 String lock="1";
				if(tb_visitormodel2.getLockstate().equals(lock)){
					System.out.println("Lock");
					return locked;}
				 else if(password.equals(password2))
			    	 return loginsuccess;
			     else 
			    	 return passwordwrong;
			 	}

	 }catch (Exception e) {
			return undefindvisitor;
	 }
}
}