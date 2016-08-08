package com.data.service.visitorService.checkAdviceService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.data.dao.QueryDao;
import com.data.dao.singleForm.checkAdvicePage.CheckAdviceDao;
import com.data.model.tb_adviceModel;
import com.data.model.tb_visitorModel;

public class CheckAdviceImp implements CheckAdvice {
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
	public int queryAdviceNumber() {
		String sql = "select  count(*) from tb_advice where status=\"0\"";
		int amount = 0;
		try {
			amount = checkAdviceDao.queryRecordNumber(sql);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库发生异常，请检查sql语句是否正确");
		}

		return amount;
	}

	@Override
	public List<Object> queryAdviceRecord() {
		String sql = "select ADid,Title,Atime,type from tb_advice where status=?";
		param = new LinkedList<Object>();
		param.add("0");
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
		if(tbAdviceModel==null)
		{
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

}
