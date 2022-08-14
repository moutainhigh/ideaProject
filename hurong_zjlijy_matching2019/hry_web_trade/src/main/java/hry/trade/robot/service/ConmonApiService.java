package hry.trade.robot.service;

import hry.trade.robot.model.ExRobot;

public interface ConmonApiService {
	public void  getCurrentPriceByApi();
	public void  getKkcoinCurrentPriceByApi(ExRobot exRobot);
	public void  getOkcoinCurrentPriceByApi(ExRobot exRobot);
	public void  getBittrexCurrentPriceByApi(ExRobot exRobot);
	public void  getOkexCurrentPriceByApi(ExRobot exRobot);
	public void getzbCurrentPriceByApi(ExRobot exRobot);
	public void getHryCurrentPriceByApi(ExRobot exRobot);
}
