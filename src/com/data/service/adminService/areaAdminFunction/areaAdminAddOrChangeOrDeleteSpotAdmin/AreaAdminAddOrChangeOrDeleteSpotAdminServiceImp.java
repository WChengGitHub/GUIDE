package com.data.service.adminService.areaAdminFunction.areaAdminAddOrChangeOrDeleteSpotAdmin;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import net.sf.json.JSONArray;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.data.dao.singleForm.tb_adminDao;
import com.data.dao.singleForm.tb_spotDao;
import com.data.md5.Encryption;
import com.data.model.AreaAdminAddOrChangeOrDeleteSpotAdminModel;
import com.data.model.CompleteVisitorInformationModel;
import com.data.model.tb_adminModel;
import com.data.model.tb_spotModel;

public class AreaAdminAddOrChangeOrDeleteSpotAdminServiceImp implements AreaAdminAddOrChangeOrDeleteSpotAdminService{
	private tb_adminDao adminDao;
	private tb_spotDao spotDao;
	private List<Object>param;
	private List<Object>list;
	
	public tb_adminDao getAdminDao() {
		return adminDao;
	}


	public void setAdminDao(tb_adminDao adminDao) {
		this.adminDao = adminDao;
	}


	public tb_spotDao getSpotDao() {
		return spotDao;
	}


	public void setSpotDao(tb_spotDao spotDao) {
		this.spotDao = spotDao;
	}


	//根据景区管理员Account得到景区管理员所在景区的景点管理员的Account,Aid,以及景点管理员所管理景点的Spot,Sid
	@Override
	public List<AreaAdminAddOrChangeOrDeleteSpotAdminModel> getSpotAdminInformations(
			AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel) {
		String sql="select Account,Aid,Sid,CreateTime from tb_admin where Sid in(select Sid from tb_spot where Arid =(select Arid from tb_admin where Account=? and Privilege=?) and Status=?) and del=?";
		String Account=areaAdminAddOrChangeOrDeleteSpotAdminModel.getAccount();
		List<AreaAdminAddOrChangeOrDeleteSpotAdminModel> list1=new LinkedList<AreaAdminAddOrChangeOrDeleteSpotAdminModel>();
		param=new LinkedList<Object>();
		param.add(Account);
		param.add("s");
		param.add(1);
		param.add(0);
		try {
			list=adminDao.query2(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//JSONArray jsonArray = JSONArray.fromObject(list);
		//System.out.println("1:"+jsonArray);
		
		if(list!=null||list.size()!=0)
        {
        	int length=list.size();
        	for(int i=0;i<length;i++)
        	{
        		tb_adminModel adminModel=(tb_adminModel) list.get(i);
        		AreaAdminAddOrChangeOrDeleteSpotAdminModel addOrChangeOrDeleteSpotAdminModel1=new AreaAdminAddOrChangeOrDeleteSpotAdminModel();
        		addOrChangeOrDeleteSpotAdminModel1.setAccount(adminModel.getAccount());
        		addOrChangeOrDeleteSpotAdminModel1.setAid(adminModel.getAid());
        		addOrChangeOrDeleteSpotAdminModel1.setSid(adminModel.getSid());
        		addOrChangeOrDeleteSpotAdminModel1.setCreateTime(adminModel.getCreateTime());
        		String Sid=addOrChangeOrDeleteSpotAdminModel1.getSid();
        		//System.out.println("aidString:"+sidString);
        		String sql1="select Spot from tb_spot where Sid=\""+Sid+"\"";
        		try {
        			addOrChangeOrDeleteSpotAdminModel1.setSpot((String) spotDao.querySpot(sql1));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
        		list1.add(addOrChangeOrDeleteSpotAdminModel1);
        	}
        	
        	
        }
		// TODO Auto-generated method stub
		return list1;
	}

	//根据景区管理员Account得到景区管理员所在景区的还没有管理景点的景点管理员（还没有管理景点的景点管理员,Arid=景区Arid,Sid=null)的Account,Aid
	@Override
	public List<Object> getSpotAdminInformations1(
			AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel) {
		// TODO Auto-generated method stub
		String sql="select Account,Aid from tb_admin where Arid =(select Arid from tb_admin where Account=? and Privilege=?) and Privilege=? and del=? and Sid is null";
		String Account=areaAdminAddOrChangeOrDeleteSpotAdminModel.getAccount();
		param=new LinkedList<Object>();
		param.add(Account);
		param.add("s");
		param.add("r");
		param.add(0);
		try {
			list=adminDao.query3(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	//用来更换景点的景点管理员  参数：Aid,newAid(新景点管理员Aid),Sid(景点id) ,希望用事务管理器
	@Override
	public boolean changeAdmin(
			AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel) {
		String sql="update tb_admin set Arid=(select Arid from tb_spot where Sid=?),Sid=null where Aid=?";
		String sql1="update tb_admin set Arid=null,Sid=? where Aid=?";
		String Aid=areaAdminAddOrChangeOrDeleteSpotAdminModel.getAid();
		String newAid=areaAdminAddOrChangeOrDeleteSpotAdminModel.getNewAid();
		String Sid=areaAdminAddOrChangeOrDeleteSpotAdminModel.getSid();
		param=new LinkedList<Object>();
		List<Object>param1=new LinkedList<Object>();
		param.add(Sid);
		param.add(Aid);
		param1.add(Sid);
		param1.add(newAid);
		try {
			adminDao.update(sql, param);
			adminDao.update(sql1,param1);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//撤销第一个更新效果
			List<Object>param2=new LinkedList<Object>();
			param2.add(Sid);
			param2.add(Aid);
			adminDao.update(sql1, param2);
		}
		// TODO Auto-generated method stub
		return false;
	}

	//用来删除景点管理员 参数：Aid,Sid
	@Override
	public boolean deleteAdmin(
			AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel) {
		String sql="update tb_admin set Del=? where Aid=?";
		String sql1="update tb_spot set Status=? where Sid=?";
		String Aid=areaAdminAddOrChangeOrDeleteSpotAdminModel.getAid();
		String Sid=areaAdminAddOrChangeOrDeleteSpotAdminModel.getSid();
		param=new LinkedList<Object>();
		List<Object>param1=new LinkedList<Object>();
		param.add("1");
		param.add(Aid);
		param1.add("0");
		param1.add(Sid);
		try {
			adminDao.update(sql, param);
			spotDao.update(sql1,param1);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//撤销第一个更新效果
			List<Object>param2=new LinkedList<Object>();
			param2.add("0");
			param2.add(Aid);
			adminDao.update(sql, param2);
		}
		// TODO Auto-generated method stub
		return false;
	}

	//用来得到还没有景点管理员的景点（status=0） 参数：景区管理员Account
	@Override
	public List<Object> getSpotInformations(
			AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel) {
		// TODO Auto-generated method stub
		String sql="select Sid,Spot from tb_spot where Arid =(select Arid from tb_admin where Account=? and Privilege=?) and Status=? and del=?";
		String Account=areaAdminAddOrChangeOrDeleteSpotAdminModel.getAccount();
		param=new LinkedList<Object>();
		param.add(Account);
		param.add("s");
		param.add("0");
		param.add(0);
		try {
			list=spotDao.querySpotInformations1(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

//用来增加一个景点管理员      参数：景区管理员Account,景点管理员Account,景点Sid   思路：Aid，CreateTime自动生成，密码默认为123456，邮箱默认为"无"。如果Sid不为空，则把该景点的Status变为1
	@Override
	public boolean addSpotAdmin(
			AreaAdminAddOrChangeOrDeleteSpotAdminModel addOrChangeOrDeleteSpotAdminModel) {
		// TODO Auto-generated method stub
		String sql="insert into tb_admin(Aid,Account,Password,Email,Privilege,Sid,CreateTime) values(?,?,?,?,?,?,?)";
		String sql2="insert into tb_admin(Aid,Account,Password,Email,Privilege,Arid,CreateTime) values(?,?,?,?,?,?,?)";
		String sql4="update tb_admin set Arid=null,Sid=? where Account=?"; 
		String sql1="update tb_spot set Status=? where Sid=?";
		String Account=addOrChangeOrDeleteSpotAdminModel.getAccount();
		String sql3="select Arid from tb_admin where Account=\""+Account+"\"";
		String Arid=(String) adminDao.queryArid(sql3);
		Calendar cal1 = Calendar.getInstance();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
		int randomNumber = (int) (Math.random() * 10);
		String Aid = (sdf.format(cal1.getTime()) + randomNumber);
		
		String spotAccount=addOrChangeOrDeleteSpotAdminModel.getSpotAccount();
		String spotAccount1=addOrChangeOrDeleteSpotAdminModel.getSpotAccount1();
		System.out.println("spotAccount1:"+spotAccount1);
		String MD5password=Encryption.generatePassword("123456");
		String Email="无";
		String Privilege="r";
		String Sid="";
		Sid=addOrChangeOrDeleteSpotAdminModel.getSid();
		Timestamp CreateTime = new Timestamp(System.currentTimeMillis());
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CreateTime = Timestamp.valueOf(sdf1.format(CreateTime));
		
		param=new LinkedList<Object>();
		param.add(Aid);
		param.add(spotAccount);
		param.add(MD5password);
		param.add(Email);
		param.add(Privilege);
		if(Sid!=""&&Sid!=null&&!(Sid.isEmpty()))
		{
			param.add(Sid);
		}
		else {
			param.add(Arid);
		}
		param.add(CreateTime);
		List<Object>param2=new LinkedList<Object>();
		param2.add(Sid);
		param2.add(spotAccount);
		try {
			if (spotAccount1!=null&&spotAccount1.equals("2")) {
				adminDao.update(sql4, param2);
			}
			else
			if(Sid!=""&&Sid!=null&&!(Sid.isEmpty()))
			{   System.out.println("1.");
				adminDao.update(sql, param);
				System.out.println("1");
			}
			else {
				System.out.println("2.");
				adminDao.update(sql2, param);
				System.out.println("2");
			}
			
			if(Sid!=""&&Sid!=null&&!(Sid.isEmpty()))
			{
				System.out.println("3");
				List<Object>param1=new LinkedList<Object>();
				param1.add("1");
				param1.add(Sid);
				spotDao.update(sql1, param1);
				System.out.println("3.");
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	//用来判断管理员的Account是否存在     参数：Account 
	@Override
	public int judgeAccount(
			AreaAdminAddOrChangeOrDeleteSpotAdminModel addOrChangeOrDeleteSpotAdminModel) {
		// TODO Auto-generated method stub
		String Account= addOrChangeOrDeleteSpotAdminModel.getAccount();
		String sql = "select count(*) from tb_admin where Account= \""
				+ Account + "\"";
		int number = -1;
		try {
			number = adminDao.queryAccountNumber(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return number;
	}

}
