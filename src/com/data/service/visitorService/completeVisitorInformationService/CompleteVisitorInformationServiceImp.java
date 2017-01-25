package com.data.service.visitorService.completeVisitorInformationService;

import java.util.LinkedList;
import java.util.List;

import com.data.dao.singleForm.tb_visitorDao;
import com.data.model.CompleteVisitorInformationModel;
import com.data.model.tb_visitorModel;

public class CompleteVisitorInformationServiceImp implements
		CompleteVisitorInformationService {
	List<Object> list;
	List<Object> param;
	private tb_visitorDao visitorDao;

	public tb_visitorDao getVisitorDao() {
		return visitorDao;
	}

	public void setVisitorDao(tb_visitorDao visitorDao) {
		this.visitorDao = visitorDao;
	}

	// 根据游客的用户名得到游客的性别
	/*
	 * @Override public CompleteVisitorInformationModel getGender(
	 * CompleteVisitorInformationModel completeVisitorInformationModel) { String
	 * sql = "select Gender from tb_visitor where Visitor=?"; String Visitor =
	 * completeVisitorInformationModel.getVisitor(); tb_visitorModel
	 * visitorModel; param=new LinkedList<Object>();
	 * CompleteVisitorInformationModel completeVisitorInformationModel1 = new
	 * CompleteVisitorInformationModel(); param.add(Visitor); try { list =
	 * visitorDao.queryVisitorGender(sql, param); } catch (Exception e) {
	 * e.printStackTrace(); // TODO: handle exception } if (list != null) {
	 * visitorModel = (tb_visitorModel) list.get(0);
	 * completeVisitorInformationModel1 .setGender(visitorModel.getGender()); }
	 * // TODO Auto-generated method stub return
	 * completeVisitorInformationModel1; }
	 */
	// 根据游客的用户名得到游客的信息
	@Override
	public CompleteVisitorInformationModel getVisitorInformation(
			CompleteVisitorInformationModel completeVisitorInformationModel) {
		String sql = "select Gender from tb_visitor where Visitor=?";
		String Visitor = completeVisitorInformationModel.getVisitor();
		tb_visitorModel visitorModel;
		param = new LinkedList<Object>();
		CompleteVisitorInformationModel completeVisitorInformationModel1 = new CompleteVisitorInformationModel();
		param.add(Visitor);
		try {
			list = visitorDao.queryVisitorInformation(sql, param);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		if (list != null&&list.size()!=0) {
			visitorModel = (tb_visitorModel) list.get(0);
			completeVisitorInformationModel1
					.setGender(visitorModel.getGender());
		}
		// TODO Auto-generated method stub
		return completeVisitorInformationModel1;
	}

	// 从数据库中获取用户名的数量,如果为0,则代表用户名不存在
	@Override
	public int judgeVisitor(
			CompleteVisitorInformationModel completeVisitorInformationModel) {
		String Visitor = completeVisitorInformationModel.getVisitor();
		String sql = "select count(*) from tb_visitor where Visitor= \""
				+ Visitor + "\"";
		int number = -1;
		try {
			number = visitorDao.queryVisitorNumber(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return number;
	}

	@Override
	public boolean updateVisitorInformation(
			CompleteVisitorInformationModel completeVisitorInformationModel) {
		// TODO Auto-generated method stub
		String sql = "update tb_visitor set Visitor=?,Gender=? where Vid=?";
		String Visitor=completeVisitorInformationModel.getVisitor();
		getVid(completeVisitorInformationModel);
		String newVisitor=completeVisitorInformationModel.getNewVisitor();
		String Gender=completeVisitorInformationModel.getGender();
		int i = Integer.parseInt(Gender);
		String Vid=completeVisitorInformationModel.getVid();
		param=new LinkedList<Object>();
		System.out.println("Visitor:"+Visitor);
		if(newVisitor!=null&&!newVisitor.isEmpty())
		{
			param.add(newVisitor);
		}
		else {
			param.add(Visitor);
		}
		param.add(i);
		param.add(Vid);
		try {
			visitorDao.update1(sql, param);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public CompleteVisitorInformationModel getVid(
			CompleteVisitorInformationModel completeVisitorInformationModel) {
		String Visitor = completeVisitorInformationModel.getVisitor();
		String sql = "select Vid from tb_visitor where Visitor=?";
		param = new LinkedList<Object>();
		param.add(Visitor);
		try {
			list = visitorDao.queryVid(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (list != null && list.size() != 0) {
			completeVisitorInformationModel.setVid(((tb_visitorModel) list
					.get(0)).getVid());
		}
		// TODO Auto-generated method stub
		return completeVisitorInformationModel;
	}
}
