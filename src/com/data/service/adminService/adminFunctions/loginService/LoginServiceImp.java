package com.data.service.adminService.adminFunctions.loginService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;






import com.data.dao.singleForm.tb_adminDaoImp;
import com.data.model.tb_adminModel;


public class LoginServiceImp implements LoginService{
	
	final int spotadmin=4,superadmin=3,checkadmin=5,wrongpassword=2,undefindadmin=1,deladmin=6,wrongPrivilege=7;
	static List<Object> param;
	static List<Object> tb_adminmodellist;
	static tb_adminModel tb_adminmodel;
	
	
	public void setAdminModel(tb_adminModel tb_adminmodel){
		this.tb_adminmodel=tb_adminmodel;
	}
	public tb_adminModel getAdminModel(){
		return this.tb_adminmodel;
	}
	//test
	public int getnumber(){
		return 0;
	}
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
			 {	//test
				 System.out.println("test tb_adminmodellist.size()==0");
			   	 return undefindadmin;}
			 else{
				 	//将查询返回的模型层集合转为模型层
					 tb_adminModel tb_adminmodel2=(tb_adminModel) tb_adminmodellist.get(0);
					 //将模型传给控制层用于将权限返回前台
					 setAdminModel(tb_adminmodel2);
					 System.out.println(getAdminModel()+" test");
					 //用户输入的密码
					 String password=tb_adminmodel.getPassword();
					 //查找得到的密码
					 String password2=tb_adminmodel2.getPassword();
					 //是否被删除的字段内容
					 String del=tb_adminmodel2.getDel();
					 //test
					 System.out.println("test del: "+del);
					 if(del.equals("1")){return deladmin;}
					 else if(password.equals(password2))
				     //密码正确则进行管理员类型判断
				     {	// tb_adminmodel.setPrivilege("a");
				    	 String privilege=tb_adminmodel2.getPrivilege();
				    	 System.out.println(privilege);
				    	 //管理员Privilege字段，a为超级管理员，s为景区管理员，c为审核管理员
				    	 String a="a";
				    	 String s="s";
				    	 String c="c";
				    	 System.out.println(privilege);
					     if(privilege.equals(a)){System.out.println("test super");return superadmin;} //超级管理员
					     //景区管理员
					     else if(privilege.equals(s))return spotadmin;
					     //审核管理员
					     else if(privilege.equals(c)) return checkadmin;
					     else return wrongPrivilege;
				     }
				    	
				     else 
				    	 //密码错误返回2
				    	 return wrongpassword;
			 }

		 }catch (Exception e) {
			 	//输出出现的异常 e就是出现的异常
			    System.out.println(e);
			  	//查找出错作为用户不存在返回1
				return undefindadmin;
		 }
	}
}