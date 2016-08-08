package com.data.service.visitorService.checkAdviceService;

import java.util.List;

import com.data.model.tb_adminModel;
import com.data.model.tb_adviceModel;
import com.data.model.tb_replyModel;
import com.data.model.tb_visitorModel;

public interface CheckAdvice {
     public int queryAdviceNumber(String Privilege);
     public List<Object> queryAdviceRecord(String Privilege);
     public Object queryTitleAndAdvice(String ADid);
     public void updateAdviceStatus(String ADid,String Status);
     public String queryVisitorEmail(String Vid);
     public String getAid(String Account);
     public void reply(tb_replyModel replyModel,tb_adminModel adminModel);
     /*public long getAdviceNumber();
     public List<Object> getAdviceRecord();
     public tb_adviceModel getAdvice(tb_adviceModel adviceModel);
     public void chanceAdviceStatus(tb_adviceModel adviceModel);
     public tb_visitorModel getAdviceEmail(tb_adviceModel adviceModel);*/
	
}
