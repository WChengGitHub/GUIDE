package com.data.service.visitorService.checkAndReplyAdviceService;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import sun.management.counter.Variability;

import com.data.dao.QueryDao;
import com.data.dao.singleForm.checkAdvicePage.CheckAdviceDao;
import com.data.model.tb_adminModel;
import com.data.model.tb_adviceModel;
import com.data.model.tb_replyModel;
import com.data.model.tb_visitorModel;

public class CheckAndReplyAdviceImp implements CheckAndReplyAdvice {
	private CheckAdviceDao checkAdviceDao;
	private QueryDao queryDao;
	private List<Object> param;
	private List<Object> adviceRecordList;
	private List<Object> list;

	public CheckAdviceDao getCheckAdviceDao() {
		return checkAdviceDao;
	}

	public void setCheckAdviceDao(CheckAdviceDao checkAdviceDao) {
		this.checkAdviceDao = checkAdviceDao;
	}

	public QueryDao getQueryDao() {
		return queryDao;
	}

	public void setQueryDao(QueryDao queryDao) {
		this.queryDao = queryDao;
	}

	@Override
	public int queryAdviceNumber(String Privilege) {
		String sql = "";
		if (Privilege.equals("c")) {
			sql = "select  count(*) from tb_advice where Status=\"0\"";
		} else {

			sql = "select  count(*) from tb_advice where  Status=\"p\" and type=\""
					+ Privilege + "\"";
			// 字符串拼接得加\"\"
		}
		int amount = 0;
		amount = checkAdviceDao.queryRecordNumber(sql);
		/*
		 * try { amount = checkAdviceDao.queryRecordNumber(sql); } catch
		 * (Exception e) { // TODO: handle exception
		 * System.out.println("数据库发生异常，请检查sql语句是否正确"); }
		 */

		return amount;
	}

	@Override
	public List<Object> queryAdviceRecord(String Privilege) {
		String sql = "";
		param = new LinkedList<Object>();
		if (Privilege.equals("c")) {
			sql = "select ADid,Title,Atime,type from tb_advice where status=?";
			param.add("0");
		} else {
			sql = "select ADid,Title,Atime,type from tb_advice where type=? and status=?";
			param.add(Privilege);
			param.add("p");
		}
		adviceRecordList = new LinkedList<Object>();
		try {
			adviceRecordList = checkAdviceDao.query(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库发生异常，请检查sql语句是否正确");
		}

		// TODO Auto-generated method stub
		if (adviceRecordList == null) {
			System.out.println("建议表没有该记录");
			return null;
		} else {
			return adviceRecordList;
		}
	}

	@Override
	public Object queryTitleAndAdvice(String ADid) {
		String sql = "select Title ,Advice,Vid from tb_advice where ADid="
				+ ADid;
		tb_adviceModel tbAdviceModel;

		try {
			tbAdviceModel = (tb_adviceModel) checkAdviceDao.queryAdvice(sql);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库发生异常，请检查sql语句是否正确");
			return null;
		}
		if (tbAdviceModel == null) {
			System.out.println("数据库没有该条记录");
			return null;
		}
		// TODO Auto-generated method stub
		return tbAdviceModel;
	}

	@Override
	public void updateAdviceStatus(String ADid, String Status) {
		String sql = "";
		String status;
		String type;
		param = new LinkedList<Object>();
		if (Status.equals("f")) {

			sql = "update tb_advice set Status=? where ADid=?";
			param.add(Status);
			param.add(ADid);
		} else {
			status = "p";
			type = Status;
			sql = "update tb_advice set Status=?,type=? where ADid=?";
			param.add(status);
			param.add(type);
			param.add(ADid);
		}

		try {
			checkAdviceDao.update(sql, param);
		} catch (Exception e) {
			System.out.println("CheckAdviceImp:数据库更新失败");
			// TODO: handle exception
		}

		// TODO Auto-generated method stub

	}

	@Override
	public String queryVisitorEmail(String Vid) {
		// TODO Auto-generated method stub
		String sql = "select Email from tb_visitor where Vid=" + Vid;
		tb_visitorModel visitorModel = new tb_visitorModel();
		try {
			visitorModel = (tb_visitorModel) checkAdviceDao.queryEmail(sql);
		} catch (Exception e) {
			System.out.println("数据库发生异常，请检查sql语句是否正确");
			return null;
		}
		if (visitorModel == null) {
			System.out.println("数据库没有该记录");
			return null;
		}
		return visitorModel.getEmail();
	}

	// 使用queryDao实现的
	/*
	 * @Override public long getAdviceNumber() { // TODO Auto-generated method
	 * stub String sql = "select  count(*) from tb_advice where status=?"; param
	 * = new LinkedList<Object>(); param.add("0"); Map map = new HashMap(); try
	 * { list = queryDao.query(sql, param); } catch (Exception e) {
	 * System.out.println("数据库出现异常，请检查sql语句是否正确"); }
	 * 
	 * if (list == null) { System.out.println("数据库没有该记录"); return 0; } else {
	 * 
	 * map = (Map) list.get(0); System.out.println(map); long number = (Long)
	 * map.get("count(*)"); System.out.println("number:" + number); return
	 * number; } }
	 * 
	 * @Override public List<Object> getAdviceRecord() { String sql =
	 * "select ADid,Title,Atime,type from tb_advice where status=?"; param = new
	 * LinkedList<Object>(); param.add("0"); try { list = queryDao.query(sql,
	 * param); } catch (Exception e) {
	 * System.out.println("数据库出现异常，请检查sql语句是否正确"); } if (list == null) {
	 * System.out.println("数据库没有该记录"); return null; } else { return list; } //
	 * TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public tb_adviceModel getAdvice(tb_adviceModel adviceModel) {
	 * String sql = "select Title ,Advice,Vid from tb_advice where ADid=?";
	 * param=new LinkedList<Object>(); param.add(adviceModel.getADid());
	 * list=queryDao.query(sql, param); Map map=new HashMap(); try { list =
	 * queryDao.query(sql, param); } catch (Exception e) {
	 * System.out.println("数据库出现异常，请检查sql语句是否正确"); }
	 * 
	 * if (list == null) { System.out.println("数据库没有该记录"); return null; } else {
	 * 
	 * map = (Map) list.get(0); tb_adviceModel adviceModel1=new
	 * tb_adviceModel(); adviceModel1.setTitle((String) map.get("Title"));
	 * adviceModel1.setAdvice((String) map.get("Advice"));
	 * adviceModel1.setVid((String) map.get("Vid")); //System.out.println(map);
	 * //long number = (Long) map.get("count(*)");
	 * //System.out.println("number:" + number); return adviceModel1; }
	 * 
	 * }
	 * 
	 * @Override public void chanceAdviceStatus(tb_adviceModel adviceModel) { //
	 * TODO Auto-generated method stub String sql =
	 * "update tb_advice set Status=? where ADid=?"; param = new
	 * LinkedList<Object>(); String Status=adviceModel.getStatus();
	 * if(Status.equals("s")) Status="2"; else if (Status.equals("a")) {
	 * Status="3"; } param.add(Status); param.add(adviceModel.getADid()); try {
	 * queryDao.update(sql, param); } catch (Exception e) { // TODO: handle
	 * exception System.out.println("数据库更新失败，请检查sql语句是否正确"); }
	 * 
	 * }
	 * 
	 * @Override public tb_visitorModel getAdviceEmail(tb_adviceModel
	 * adviceModel) { // TODO Auto-generated method stub String sql =
	 * "select Email from tb_visitor where Vid=?"; param=new
	 * LinkedList<Object>(); param.add(adviceModel.getVid()); Map map=new
	 * HashMap(); tb_visitorModel visitorModel = new tb_visitorModel(); try {
	 * list = queryDao.query(sql, param); } catch (Exception e) {
	 * System.out.println("数据库出现异常，请检查sql语句是否正确"); }
	 * 
	 * if (list == null) { System.out.println("数据库没有该记录"); return null; } else {
	 * 
	 * map = (Map) list.get(0); visitorModel.setEmail((String)
	 * map.get("Email")); return visitorModel; }
	 * 
	 * }
	 */

	@Override
	public void reply(tb_replyModel replyModel,tb_adminModel adminModel) {
		Calendar cal1 = Calendar.getInstance();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
		//System.out.println(sdf.format(cal1.getTime()));
		int randomNumber = (int) (Math.random() * 10);
		//System.out.println(randomNumber);// test
		String Rid = (sdf.format(cal1.getTime()) + randomNumber);
		
		Timestamp Time= new Timestamp(System.currentTimeMillis());
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Time= Timestamp.valueOf(sdf1.format(Time));
		String Aid=getAid(adminModel.getAccount());
		String Title=replyModel.getTitle();
		String Reply=replyModel.getReply();
		String ADid=replyModel.getADid();
		// TODO Auto-generated method stub
		String sql = "insert into tb_reply values(?,?,?,?,?,?)";
		param = new LinkedList<Object>();
		param.add(Rid);
		param.add(Title);
		param.add(Reply);
		param.add(ADid);
		param.add(Time);
		param.add(Aid);
		try {
			checkAdviceDao.update(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("tb_reply插入数据失败,请检查sql语句是否正确");
			return;
		}
		param=new LinkedList<Object>();
		param.add("1");
		param.add(ADid);
		sql = "update tb_advice set Status=? where ADid=?";
		try {
			checkAdviceDao.update(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("tb_advice更新数据失败,请检查sql语句是否正确");
			return;
		}
	}

	@Override
	public String getAid(String Account) {
		// TODO Auto-generated method stub
		String sql = "select Aid from tb_admin where Account=\"" + Account
				+ "\"";
		String Aid;
        tb_adminModel adminModel=new tb_adminModel();
		try {
			adminModel= (tb_adminModel) checkAdviceDao.queryAid(sql);
			Aid=adminModel.getAid();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库发生异常，请检查sql语句是否正确");
			return null;
		}
		if (Aid == null) {
			System.out.println("数据库没有该条记录");
			return null;
		}
		return Aid;
	}

}
