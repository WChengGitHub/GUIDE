package com.data.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

//import com.data.dao.ImpDao;//不用getBean用import怎么不能调用getjdbcTemplate

public class QueryUserDao{
	
//	private JdbcTemplate jdbcTemplate;
	
	public int query_user(String Name){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		 UserDaoImp impdao=(UserDaoImp) factory.getBean("impdao");
		JdbcTemplate jt=impdao.getJdbcTemple();
		try {
			String 	sql="select count(*) from user "+" where Name= ? ";//+Name;
			int count=jt.queryForInt(sql, new Object[]{Name});
			return count;
		} catch (Exception e) {
		  return 0;
		}
	}
	
	public int query_password(String Name,String Password){
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDaoImp impdao=(UserDaoImp)factory.getBean("impdao");
		JdbcTemplate jt=impdao.getJdbcTemple();
		System.out.println(Password);
		System.out.println(Name);
		try {
			String 	sql="select count(*) from user "+" where Name= ? ";//+Name;
			System.out.println( jt.queryForInt(sql, new Object[]{Name}));
			String 	sql2="select count(*) from user "+" where name= ? and password= ? ";
			int count=jt.queryForInt(sql2, new Object[]{Name,Password});
			System.out.println(count);
			return count;
		} catch (Exception e) {
		  return 0;
		}
	}
}

