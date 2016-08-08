package com.data.service.visitorService.checkAdviceService;

import java.util.List;

import com.data.model.tb_adviceModel;
import com.data.model.tb_visitorModel;

public interface CheckAdvice {
     public int queryAdviceNumber();
     public List<Object> queryAdviceRecord();
     public Object queryTitleAndAdvice(String ADid);
     public void updateAdviceStatus(String ADid,String Status);
     public String queryVisitorEmail(String Vid);
     /*public long getAdviceNumber();
     public List<Object> getAdviceRecord();
     public tb_adviceModel getAdvice(tb_adviceModel adviceModel);
     public void chanceAdviceStatus(tb_adviceModel adviceModel);
     public tb_visitorModel getAdviceEmail(tb_adviceModel adviceModel);*/
}
