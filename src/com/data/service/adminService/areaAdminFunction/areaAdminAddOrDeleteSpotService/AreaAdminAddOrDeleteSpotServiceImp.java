package com.data.service.adminService.areaAdminFunction.areaAdminAddOrDeleteSpotService;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import com.data.dao.singleForm.tb_adminDao;
import com.data.dao.singleForm.tb_spotDao;
import com.data.model.AreaAdminAddOrDeleteSpotModel;

public class AreaAdminAddOrDeleteSpotServiceImp implements AreaAdminAddOrDeleteSpotService{
	private tb_spotDao spotDao;
	private tb_adminDao adminDao;
	private List<Object> list;
	private List<Object> param;
	
	public tb_spotDao getSpotDao() {
		return spotDao;
	}

	public void setSpotDao(tb_spotDao spotDao) {
		this.spotDao = spotDao;
	}

	public tb_adminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(tb_adminDao adminDao) {
		this.adminDao = adminDao;
	}
//用来显示已有的景点信息，需要传入景区管理员的Account,返回一个list集合，每个集合元素包括景点id,景点名字，景点管理员名字，景点创建时间
	@Override
	public List<Object> getSpotInformations(
			AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel) {
		// TODO Auto-generated method stub
		String sql="select Sid,Spot,CreateTime from tb_spot where Arid in(select Arid from tb_admin where Account=?) and del=?";
		String Account=areaAdminAddOrDeleteSpotModel.getAccount();
		param=new LinkedList<Object>();
		param.add(Account);
		param.add(0);
		List<AreaAdminAddOrDeleteSpotModel>list1=new LinkedList<AreaAdminAddOrDeleteSpotModel>();
        try {
			list=spotDao.query1(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        if(list!=null||list.size()!=0)
        {
        	int length=list.size();
        	for(int i=0;i<length;i++)
        	{
        		list1.add((AreaAdminAddOrDeleteSpotModel) list.get(i));
        		String sidString=list1.get(i).getSid();
        		//System.out.println("aidString:"+sidString);
        		String sql1="select Account from tb_admin where Sid=\""+sidString+"\" and Del=0";
        		try {
        			list1.get(i).setSpotAdmin((String) adminDao.queryAccount(sql1));
				} catch (Exception e) {
					// TODO: handle exception
				}
        	}
        	
        }
		return list;
	}
//删除景点：需要传入景点管理员名字和景点ID，删除的思路：删除景点，如果该景点有管理员，那么顺便把管理员也删除了
	@Override
	public boolean deleteSpot(
			AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel) {
		// TODO Auto-generated method stub
		String Account=areaAdminAddOrDeleteSpotModel.getAccount();
		String Sid=areaAdminAddOrDeleteSpotModel.getSid();
		String sql1="update tb_spot set Del=?, Status=? where Sid=?";
		param=new LinkedList<Object>();
		try {
			param.add("1");
			param.add("0");
			param.add(Sid);
			spotDao.update(sql1, param);
			if(!Account.isEmpty())
			{
				String sql2="update tb_admin set Del=? where Sid=?";
				param=new LinkedList<Object>();
				param.add("1");
				param.add(Sid);
				adminDao.update(sql2, param);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
//添加景点：需要传入景区管理员的Account,景点名字，景点经纬度，CreateTimem和景点ID自动生成,Raidus默认为0，Arid有Account查询的到，Description，voice 默认值为：景点信息，景点介绍
	@Override
	public boolean addSpot(
			AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel) {
		// TODO Auto-generated method stub
		String sql="insert into tb_spot(Sid,Spot,Longitude,Latitude,Radius,Arid,Description,Voice,CreateTime) values(?,?,?,?,?,?,?,?,?)";
		
		String Spot=areaAdminAddOrDeleteSpotModel.getSpot();
		String Longitude=areaAdminAddOrDeleteSpotModel.getLongitude();
		String Latitude=areaAdminAddOrDeleteSpotModel.getLatitude();
		String Account=areaAdminAddOrDeleteSpotModel.getAccount();
		String sql1="select Arid from tb_admin where Account=\""+Account+"\"";
		String Arid=(String) adminDao.queryArid(sql1);
		if(Arid.isEmpty())
		{
			System.out.println("Arid为空值 Arid:"+Arid);
			return false;
		}
		Calendar cal1 = Calendar.getInstance();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
		int randomNumber = (int) (Math.random() * 10);
		String Sid = (sdf.format(cal1.getTime()) + randomNumber);
		
		
		Timestamp CreateTime = new Timestamp(System.currentTimeMillis());
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CreateTime = Timestamp.valueOf(sdf1.format(CreateTime));
		
		param=new LinkedList<Object>();
		param.add(Sid);
		param.add(Spot);
		param.add(Longitude);
		param.add(Latitude);
		param.add(0);
		param.add(Arid);
		param.add("景点信息 ");
		param.add("景点介绍");
		param.add(CreateTime);
		try {
			spotDao.update(sql, param);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	}
}
