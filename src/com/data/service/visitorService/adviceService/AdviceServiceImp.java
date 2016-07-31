package com.data.service.visitorService.adviceService;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import com.data.dao.singleForm.pageOne.AdviceDao;
import com.data.model.tb_visitorModel;

public class AdviceServiceImp implements AdviceService {
	private AdviceDao adviceDao;
	private List<Object> listVisitor;
	private List<Object> param;

	public AdviceDao getAdviceDao() {
		return adviceDao;
	}

	public void setAdviceDao(AdviceDao adviceDao) {
		this.adviceDao = adviceDao;
	}
    //这个方法是用来得到游客id，visitor代表游客的名字
	@Override
	public String getVid(String visitor) {
		// TODO Auto-generated method stub
		String vid;
		String sql = "select Vid from tb_visitor where Visitor=?";
		param = new LinkedList<Object>();
		param.add(visitor);
		listVisitor = adviceDao.query(sql, param);
		if (listVisitor == null) {
			System.out.println("addviceServiceImp中的vid提取失败");
			return null;
		} else {
			tb_visitorModel visitorModel = (tb_visitorModel) listVisitor.get(0);
			//System.out.println("vid:" + visitorModel.getVid());
			return visitorModel.getVid();
		}
	}
     //type代表建议类型
	@Override
	public void addAdviceService(String title, String advice, String type,
			String visitor) {
		// TODO Auto-generated method stub
		String sql = "insert into tb_advice values(?,?,?,?,?,?,?)";
		Calendar cal1 = Calendar.getInstance();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
		//System.out.println(sdf.format(cal1.getTime()));
		int randomNumber = (int) (Math.random() * 10);
		//System.out.println(randomNumber);// test
		String aDid = (sdf.format(cal1.getTime()) + randomNumber);
        //生成随机建议id 
		//System.out.println("aDid:" + aDid);
		Timestamp atime = new Timestamp(System.currentTimeMillis());
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		atime = Timestamp.valueOf(sdf1.format(atime));
		String status = "0";
		String vid = getVid(visitor);
		//System.out.println("atime:" + atime);
        //得到建议的时间
		param = new LinkedList<Object>();
		param.add(aDid);
		param.add(title);
		param.add(advice);
		param.add(type);
		param.add(atime);
		param.add(status);
		param.add(vid);
		try {
			adviceDao.addAdvice(sql, param);

		} catch (Exception e) {
			System.out.println("AdviceServiceImp:failed to submit advice");
		}
	}

}
