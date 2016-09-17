package com.data.service.adminService.checkAdminFunctions.checkAdvice;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.data.dao.singleForm.tb_adminDao;
import com.data.dao.singleForm.tb_admin_adviceDao;
import com.data.dao.singleForm.tb_adviceDao;
import com.data.dao.singleForm.tb_areaDao;
import com.data.dao.singleForm.tb_cityDao;
import com.data.dao.singleForm.tb_provinceDaoImp;
import com.data.dao.singleForm.tb_visitorDao;
import com.data.model.ChangeAndDelAdminModel;
import com.data.model.CheckAdviceModel;
import com.data.model.tb_adminModel;
import com.data.model.tb_visitorModel;
import com.data.service.SendEmail;
import com.sun.mail.imap.protocol.Status;

public class CheckAdviceServiceImp implements CheckAdviceService {
	private tb_adviceDao adviceDao;
	private tb_adminDao adminDao;
	private tb_visitorDao visitorDao;
	private tb_admin_adviceDao admin_adviceDao;
	private tb_provinceDaoImp provinceDaoImp;
	private tb_cityDao cityDao;
	private tb_areaDao areaDao;
	private List<Object> param;
	private List<Object> list;

	public tb_adviceDao getAdviceDao() {
		return adviceDao;
	}

	public void setAdviceDao(tb_adviceDao adviceDao) {
		this.adviceDao = adviceDao;
	}

	public tb_adminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(tb_adminDao adminDao) {
		this.adminDao = adminDao;
	}

	public tb_visitorDao getVisitorDao() {
		return visitorDao;
	}

	public void setVisitorDao(tb_visitorDao visitorDao) {
		this.visitorDao = visitorDao;
	}

	public tb_admin_adviceDao getAdmin_adviceDao() {
		return admin_adviceDao;
	}

	public void setAdmin_adviceDao(tb_admin_adviceDao admin_adviceDao) {
		this.admin_adviceDao = admin_adviceDao;
	}

	public tb_provinceDaoImp getProvinceDaoImp() {
		return provinceDaoImp;
	}

	public void setProvinceDaoImp(tb_provinceDaoImp provinceDaoImp) {
		this.provinceDaoImp = provinceDaoImp;
	}

	public tb_cityDao getCityDao() {
		return cityDao;
	}

	public void setCityDao(tb_cityDao cityDao) {
		this.cityDao = cityDao;
	}

	public tb_areaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(tb_areaDao areaDao) {
		this.areaDao = areaDao;
	}

	@Override
	public int getAdviceNumber(CheckAdviceModel checkAdviceModel) {
		int amount = 0;
		String sql = "";
		String Privilege = "";
		Privilege = checkAdviceModel.getPrivilege();
		sql = "select  count(*) from tb_advice where Status=\"0\"";
		try {
			amount = adviceDao.queryRecordNumber(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return amount;
	}

	@Override
	public List<Object> getAdvices() {
		String sql = "select ADid,Title,Atime,type,Advice,Vid from tb_advice where Status=?";
		param = new LinkedList<Object>();
		param.add("0");

		try {

			list = adviceDao.query(sql, param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (list == null) {
			System.out.println("建议表没有记录");
			return null;
		} else {
			return list;
		}
	}

	@Override
	public List<Object> getSuperAdminAids() {
		// TODO Auto-generated method stub
		String sql = "select Aid from tb_admin where Privilege=?";
		param = new LinkedList<Object>();
		param.add("a");
		try {
			list = adminDao.queryAids(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		if (list != null)
			return list;
		else {
			System.out.println("数据库上没有记录");
			return null;
		}

	}

	/* 这个是用来给管理员分配建议的函数 */
	@Override
	public boolean setAdminAdvice(CheckAdviceModel checkAdviceModel) {
		String Privilege = checkAdviceModel.getPrivilege();
		String Status=checkAdviceModel.getStatus();
		String ADid = checkAdviceModel.getADid();
		System.out.println("ADid:"+ADid);
		tb_adminModel adminModel;
		String sql = "insert into tb_admin_advice values(?,?,?)";
		List<Object> Aids=null;
		if (Privilege!=null&&Privilege.equals("a"))
		{
			Aids = getSuperAdminAids();
		}
		else if(Privilege!=null&&Privilege.equals("s")){
			Aids=getSpotAdminAids(checkAdviceModel);
		}
		else if(Status!=null&&Status.equals("f"))
		{
			changeAdviceStatus(checkAdviceModel);
		}
		 //System.out.println("Aids.size():"+Aids.size());
		if (Aids == null)
			return false;
		try {
			for (int i = 0; i < Aids.size(); i++) {
				adminModel = (tb_adminModel) Aids.get(i);
				param = new LinkedList<Object>();
				param.add(ADid);
				param.add(adminModel.getAid());
				param.add("0");
				// System.out.println("adminModel.getAid():"+adminModel.getAid());
				// System.out.println(param.get(0)+" "+param.get(1));
				admin_adviceDao.update(sql, param);

			}
			if(changeAdviceStatus(checkAdviceModel))
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object> getProvince() {
		String sql = "select * from tb_province";

		param = new LinkedList<Object>();
		try {
			list = provinceDaoImp.query(sql, param);

		} catch (Exception e) {
			// TODO: handle exception
			System.out
					.println("ChangeAndDelAdminService:getProvinceModel:查询出现错误");
		}
		if (list == null) {
			System.out.println("数据库没有改纪录");
		}
		return list;
	}

	@Override
	public List<Object> getCity(CheckAdviceModel checkAdviceModel) {
		String sql = "select Cid,City from tb_city where Pid=?";
		param = new LinkedList<Object>();
		param.add(checkAdviceModel.getPid());
		try {
			list = cityDao.query(sql, param);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ChangeAndDelAdminService:getCity:查询出现错误");
		}
		if (list == null) {
			System.out.println("数据库没有改纪录");
		}
		return list;
	}

	@Override
	public List<Object> getArea(CheckAdviceModel checkAdviceModel) {
		// TODO Auto-generated method stub
		String sql = "select Arid,Area from tb_area where Cid=?";
		param = new LinkedList<Object>();
		param.add(checkAdviceModel.getCid());
		try {
			list = areaDao.query(sql, param);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ChangeAndDelAdminService:getArea:查询出现错误");
		}
		if (list == null) {
			System.out.println("数据库没有改纪录");
		}
		return list;
	}

	@Override
	public boolean changeAdviceStatus(CheckAdviceModel checkAdviceModel) {
		String sql = "";
		String ADid = checkAdviceModel.getADid();
		String Status =checkAdviceModel.getStatus();
		String type = checkAdviceModel.getType();
		param = new LinkedList<Object>();
		System.out.println("Status:"+Status+"type:"+type);
		if (Status.equals("f")) {
            
			sql = "update tb_advice set Status=? where ADid=?";
			param.add(Status);
			param.add(ADid);
		} else {
			sql = "update tb_advice set Status=?,type=? where ADid=?";
			param.add(Status);
			param.add(type);
			param.add(ADid);
		}

		try {
			// checkAdviceDao.update(sql, param);
			adviceDao.update(sql, param);
			if (Status.equals("f")) {
	           SendEmail.sendEmail(getVisitorEmail(checkAdviceModel),"Hello","world!");
			}
		} catch (Exception e) {
			System.out.println("数据库更新失败");
			// TODO: handle exception
		}

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object> getSpotAdminAids(CheckAdviceModel checkAdviceModel) {
		// TODO Auto-generated method stub
		String Pid=checkAdviceModel.getPid();
		String Cid=checkAdviceModel.getCid();
		String Arid=checkAdviceModel.getArid();
		String sql="";
		param=new LinkedList<Object>();
		if(Arid!=null && Arid.length()!=0)
		{
			 sql="select Aid from tb_admin where Arid=?";
			 param.add(Arid);
		}
		else if(Cid!=null&&Cid.length()!=0)
		{
			sql="select Aid from tb_admin where Arid in(select Arid from tb_area where Cid=?)";
			param.add(Cid);
		}
		else if(Pid!=null&&Pid.length()!=0)
		{
			sql="select Aid from tb_admin where Arid in(select Arid from tb_area where Cid in(select Cid from tb_city where Pid=?))";
			param.add(Pid);
		}
		try {
			list=adminDao.queryAids(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Aid提取失败");
		}
		if(list==null)
		{
			System.out.println("数据库Aid的记录");
		}
		return list;
	}

	@Override
	public String getVisitorEmail(CheckAdviceModel checkAdviceModel) {
		String Vid=checkAdviceModel.getVid();
		String sql = "select Email from tb_visitor where Vid=" + Vid;
		tb_visitorModel visitorModel = new tb_visitorModel();
		try {
			//visitorModel = (tb_visitorModel) checkAdviceDao.queryEmail(sql);
			visitorModel=(tb_visitorModel) visitorDao.queryEmail(sql);
		} catch (Exception e) {
			System.out.println("提取邮件失败");
			return null;
		}
		if (visitorModel == null) {
			System.out.println("数据库没有关于邮件的记录");
			return null;
		}
		return visitorModel.getEmail();
	}

}
