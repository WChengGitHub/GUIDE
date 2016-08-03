package com.data.service.visitorService.checkAdviceService;

import java.util.LinkedList;
import java.util.List;

import com.data.dao.singleForm.checkAdvicePage.CheckAdviceDao;

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

}
