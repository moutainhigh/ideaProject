package hry.admin.exchange.service;


import hry.admin.exchange.model.ExRobot;
import hry.bean.JsonResult;

public interface ConmonApiService {
	public String  getCurrentPriceByApi(ExRobot exRobot);
	public JsonResult getHryCurrentPriceByApi(ExRobot exRobot);
}
