package com.data.service.adminService.areaAdminFunction.areaAdminReplyAdviceService;

import net.sf.json.JSONArray;

import com.data.model.AreaAdminReplyAdviceModel;

public interface AreaAdminReplyAdviceService {
	public int getSoftwareAdviceNumber(AreaAdminReplyAdviceModel areaAdminReplyAdviceModel);
    public JSONArray getSoftwareAdvices(AreaAdminReplyAdviceModel areaAdminReplyAdviceModel);
    public AreaAdminReplyAdviceModel getVisitorEmail(AreaAdminReplyAdviceModel areaAdminReplyAdviceModel);
    public AreaAdminReplyAdviceModel getAid(AreaAdminReplyAdviceModel areaAdminReplyAdviceModel);
    public boolean changeAdviceStatus(AreaAdminReplyAdviceModel areaAdminReplyAdviceModel);
    public AreaAdminReplyAdviceModel getAdviceStatus(AreaAdminReplyAdviceModel areaAdminReplyAdviceModel);
    public boolean replyAdvice(AreaAdminReplyAdviceModel areaAdminReplyAdviceModel);
}

