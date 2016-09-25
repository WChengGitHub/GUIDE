package com.data.service.adminService.superAdminFunctions.superAdminReplyAdvice;

import net.sf.json.JSONArray;

import com.data.model.SuperAdminReplyAdviceModel;

public interface SuperAdminReplyAdviceService {
     public int getSoftwareAdviceNumber(SuperAdminReplyAdviceModel superAdminReplyAdviceModel);
     public JSONArray getSoftwareAdvices(SuperAdminReplyAdviceModel superAdminReplyAdviceModel);
     public SuperAdminReplyAdviceModel getVisitorEmail(SuperAdminReplyAdviceModel superAdminReplyAdviceModel);
     public SuperAdminReplyAdviceModel getAid(SuperAdminReplyAdviceModel superAdminReplyAdviceModel);
     public boolean changeAdviceStatus(SuperAdminReplyAdviceModel superAdminReplyAdviceModel);
     public SuperAdminReplyAdviceModel getAdviceStatus(SuperAdminReplyAdviceModel superAdminReplyAdviceModel);
     public boolean replyAdvice(SuperAdminReplyAdviceModel superAdminReplyAdviceModel);
}
