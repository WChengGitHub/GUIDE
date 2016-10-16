package com.data.service.adminService.superAdminFunctions.superAdminReplyAdvice;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import net.sf.json.JSONArray;

import com.data.dao.singleForm.tb_adminDao;
import com.data.dao.singleForm.tb_admin_adviceDao;
import com.data.dao.singleForm.tb_adviceDao;
import com.data.dao.singleForm.tb_visitorDao;
import com.data.model.SuperAdminReplyAdviceModel;
import com.data.model.tb_adminModel;
import com.data.model.tb_adviceModel;
import com.data.model.tb_visitorModel;
import com.data.service.SendEmail;

public class SuperAdminReplyAdviceServiceImp implements
		SuperAdminReplyAdviceService {
	private tb_adminDao adminDao;
	private tb_adviceDao adviceDao;
	private tb_visitorDao visitorDao;
	private tb_admin_adviceDao admin_adviceDao;
	private List<Object> list;
	private List<Object> param;

	public tb_adminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(tb_adminDao adminDao) {
		this.adminDao = adminDao;
	}

	public tb_adviceDao getAdviceDao() {
		return adviceDao;
	}

	public void setAdviceDao(tb_adviceDao adviceDao) {
		this.adviceDao = adviceDao;
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

	@Override
	public int getSoftwareAdviceNumber(
			SuperAdminReplyAdviceModel superAdminReplyAdviceModel) {

		String Account = superAdminReplyAdviceModel.getAccount();
		String sql = "select count(*) from tb_admin_advice where Aid in(Select Aid from tb_admin where Account=\""
				+ Account + "\") and Status=\"0\"";
		int softwareAdviceNumber = -1;
		if (Account == null) {
			System.out
					.println("SuperAdminReplyAdviceServiceImp getSoftwareAdviceNumber Account为空值");
			return softwareAdviceNumber;
		}
		// TODO Auto-generated method stub
		try {
			softwareAdviceNumber = adminDao.queryRecordNumber(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return softwareAdviceNumber;
	}

	@Override
	public JSONArray getSoftwareAdvices(
			SuperAdminReplyAdviceModel superAdminReplyAdviceModel) {
		String Account = superAdminReplyAdviceModel.getAccount();
		if (Account == null || Account.length() == 0) {
			System.out
					.println("SuperAdminReplyAdviceServiceImp getSoftwareAdvices:Account为空值");
			return null;
		}
		System.out.println("Account:" + Account);
		String sql = "select ADid,Title,Atime,type,Advice,Vid from tb_advice where ADid in(select ADid from tb_admin_advice where Status=? and Aid in(select Aid from tb_admin where Account=?))";
		param = new LinkedList<Object>();
		param.add("0");
		param.add(Account);
		try {
			list = adviceDao.query(sql, param);
			JSONArray jsonArray = JSONArray.fromObject(list);
			// TODO Auto-generated method stub
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SuperAdminReplyAdviceModel getVisitorEmail(
			SuperAdminReplyAdviceModel superAdminReplyAdviceModel) {
		String Vid = superAdminReplyAdviceModel.getVid();
		String sql = "select Email from tb_visitor where Vid=" + Vid;
		tb_visitorModel visitorModel = new tb_visitorModel();
		try {
			// visitorModel = (tb_visitorModel) checkAdviceDao.queryEmail(sql);
			visitorModel = (tb_visitorModel) visitorDao.queryEmail(sql);
		} catch (Exception e) {
			e.printStackTrace();

		}
		superAdminReplyAdviceModel.setEmail(visitorModel.getEmail());

		return superAdminReplyAdviceModel;
	}

	// 把tb_admin_advice Status和tb_advice Status的值变为1，即状态为已经回复
	@Override
	public boolean changeAdviceStatus(
			SuperAdminReplyAdviceModel superAdminReplyAdviceModel) {
		String sql = "update tb_admin_advice,tb_advice set tb_admin_advice.Status=?,tb_advice.Status=? where tb_admin_advice.ADid=? and tb_advice.ADid=?and tb_admin_advice.Aid=? ";
		String ADid = superAdminReplyAdviceModel.getADid();
		String Aid = superAdminReplyAdviceModel.getAid();
		param = new LinkedList<Object>();
		param.add("1");
		param.add("1");
		param.add(ADid);
		param.add(ADid);
		param.add(Aid);
		try {
			admin_adviceDao.update(sql, param);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return false;
	}

	// 得到tb_advice Status
	@Override
	public SuperAdminReplyAdviceModel getAdviceStatus(
			SuperAdminReplyAdviceModel superAdminReplyAdviceModel) {
		String sql = "select Status from tb_advice where ADid=?";
		String ADid = superAdminReplyAdviceModel.getADid();
		param = new LinkedList<Object>();
		param.add(ADid);
		try {
			list = adviceDao.queryAdviceStatus(sql, param);
			if (list != null) {
				superAdminReplyAdviceModel.setStatus(((tb_adviceModel) list
						.get(0)).getStatus());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return superAdminReplyAdviceModel;
	}

	@Override
	public boolean replyAdvice(
			SuperAdminReplyAdviceModel superAdminReplyAdviceModel) {
		String Aid=getAid(superAdminReplyAdviceModel).getAid();
		if(Aid==null)
		{
			System.out.println("Aid为空值");
			return false;
		}
		superAdminReplyAdviceModel.setAid(Aid);
		
		String Status = getAdviceStatus(superAdminReplyAdviceModel).getStatus();
		String sql = "insert into tb_reply(Rid,Title,Reply,ADid,Time,Aid) values(?,?,?,?,?,?)";
		String Rid;
		Timestamp Time;
		boolean b=false;
		String ADid=superAdminReplyAdviceModel.getADid();
		//String Aid=superAdminReplyAdviceModel.getAid();
		String Title=superAdminReplyAdviceModel.getTitle();
		String Reply=superAdminReplyAdviceModel.getReply();
		String Email=getVisitorEmail(superAdminReplyAdviceModel).getEmail();
		//判断建议是否已经回复，状态为代表已经回复
		if (Status.equals("1"))
		{
			changeAdviceStatus(superAdminReplyAdviceModel);
			return true;
		}
		//得到Rid
		Calendar cal1 = Calendar.getInstance();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
		int randomNumber = (int) (Math.random() * 10);
		Rid = (sdf.format(cal1.getTime()) + randomNumber);
		//得到Time
		Time = new Timestamp(System.currentTimeMillis());
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Time = Timestamp.valueOf(sdf1.format(Time));
		param=new LinkedList<Object>();
		param.add(Rid);
		param.add(Title);
		param.add(Reply);
		param.add(ADid);
		param.add(Time);
		param.add(Aid);
		try {
			adviceDao.update(sql, param);
			System.out.println("插入数据成功");
			if(changeAdviceStatus(superAdminReplyAdviceModel))
			{
				System.out.println("状态更新成功");
				b=true;
				if(Email!=null)
				{
					
					SendEmail.sendEmail(Email, Title,Reply);
					System.out.println("邮件发送成功");
				}
				else {
					System.out.println("邮箱获取失败");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return b;
		}
		
		// TODO Auto-generated method stub
		return b;
	}

	@Override
	public SuperAdminReplyAdviceModel getAid(
			SuperAdminReplyAdviceModel superAdminReplyAdviceModel) {
		String sql="select Aid from tb_admin where Account=?";
		String Account=superAdminReplyAdviceModel.getAccount();
		param=new LinkedList<Object>();
		param.add(Account);
		try {
			list=adminDao.queryAids(sql, param);
			if(list!=null)
			{
			tb_adminModel adminModel=(tb_adminModel) list.get(0);
			superAdminReplyAdviceModel.setAid(adminModel.getAid());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return superAdminReplyAdviceModel;
	}

}
