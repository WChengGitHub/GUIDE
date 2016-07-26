package com.data.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

//import com.data.dao.ImpDao;//不用getBean用import怎么不能调用getjdbcTemplate

public class QueryVisitorDao{
	
//	private JdbcTemplate jdbcTemplate;
	
	public int queryVisitor(String Visitor){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		 VisitorDaoImp impdao=(VisitorDaoImp) factory.getBean("impdao");
		JdbcTemplate jt=impdao.getJdbcTemple();
		try {
			String 	sql="select count(*) from tb_visitor  where Visitor= ? ";//+Name;
			@SuppressWarnings("deprecation")
			int count=jt.queryForInt(sql, new Object[]{Visitor});
			return count;
		} catch (Exception e) {
		  return 0;
		}
	}
	
	@SuppressWarnings("deprecation")
	public int queryPassword(String Visitor,String Password){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		VisitorDaoImp impdao=(VisitorDaoImp)factory.getBean("impdao");
		JdbcTemplate jt=impdao.getJdbcTemple();
		try {
			String 	sql="select count(*) from tb_visitor "+" where Visitor= ? ";//+Name;
			
			String 	sql2="select count(*) from tb_visitor "+" where Visitor= ? and Password= ? ";
			int count=jt.queryForInt(sql2, new Object[]{Visitor,Password});
			return count;
		} catch (Exception e) {
		  return 0;
		}
	}
}

