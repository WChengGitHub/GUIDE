package com.data.service.visitorService.checkAdviceService;

import com.data.dao.singleForm.checkAdvicePage.CheckAdviceDao;

public class CheckAdviceImp implements CheckAdvice{
    private CheckAdviceDao checkAdviceDao;
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

}
