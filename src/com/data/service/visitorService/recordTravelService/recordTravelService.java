package com.data.service.visitorService.recordTravelService;

import com.data.model.tb_travelsModel;
import com.data.model.tb_visitorModel;

public interface recordTravelService {
	public int Publish(tb_travelsModel tb_travelsmodel);
	public String GetVid(tb_visitorModel tb_visitormodel);
}
