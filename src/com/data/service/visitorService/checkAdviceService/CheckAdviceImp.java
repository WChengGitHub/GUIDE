package com.data.service.visitorService.checkAdviceService;

import java.util.LinkedList;
import java.util.List;

import com.data.dao.singleForm.checkAdvicePage.CheckAdviceDao;
import com.data.model.tb_adviceModel;
import com.data.model.tb_visitorModel;

public class CheckAdviceImp implements CheckAdvice{
    private CheckAdviceDao checkAdviceDao;
    private List<Object>param;
    private List<Object>adviceRecordList;
	public CheckAdviceDao getCheckAdviceDao() {
		return checkAdviceDao;
	}
	public void setCheckAdviceDao(CheckAdviceDao checkAdviceDao) {
		this.checkAdviceDao = checkAdviceDao;
	}
	@Override
	public int queryAdviceNumber() {
		String sql="select  count(*) from tb_advice where status=\"0\"";
		return checkAdviceDao.query(sql);
	}
	@Override
	public List<Object> queryAdviceRecord() {
		String sql="select ADid,Title,Atime,type from tb_advice where status=?";
		param=new LinkedList<Object>();
		param.add("0");
		adviceRecordList=new LinkedList<Object>();
		adviceRecordList=checkAdviceDao.query(sql, param);
		// TODO Auto-generated method stub
		if(adviceRecordList==null)
		{
			System.out.println("建议表记录提取失败");
		return null;
		}
		else {
			return adviceRecordList;
		}
	}
	@Override
	public Object queryTitleAndAdvice(String ADid) {
        String sql="select Title ,Advice,Vid from tb_advice where ADid="+ADid;
        tb_adviceModel tbAdviceModel=(tb_adviceModel) checkAdviceDao.query1(sql);
		// TODO Auto-generated method stub
		return tbAdviceModel;
	}
	@Override
	public void updateAdviceStatus(String ADid, String Status) {
		String sql = "update tb_advice set Status=? where ADid=?";
		param = new LinkedList<Object>();
		param.add(Status);
		param.add(ADid);
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
	   String sql="select Email from tb_visitor where Vid="+Vid;
	   tb_visitorModel visitorModel=new tb_visitorModel();
	   try{
	    visitorModel=(tb_visitorModel) checkAdviceDao.query2(sql);
	   }
	   catch(Exception e)
	   {
		   System.out.println("CheckAdviceImp:提取游客名字失败");
	   }
	   
	   return visitorModel.getEmail();
	}

}
