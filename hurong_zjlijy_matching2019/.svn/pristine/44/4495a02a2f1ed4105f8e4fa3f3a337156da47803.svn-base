package hry.mall.integral.remote;

import hry.bean.JsonResult;
import hry.mall.integral.model.RechargeConfig;
import hry.mall.integral.service.RechargeConfigService;
import hry.mall.platform.model.MallConfig;
import hry.mall.platform.service.MallConfigService;

import javax.annotation.Resource;
import java.util.List;

public class RemoteRechargeConfigServiceImpl implements RemoteRechargeConfigService {
   @Resource
   public RechargeConfigService rechargeConfigService;
   @Resource
   public MallConfigService mallConfigService;
	@Override
	public JsonResult rechargeList() {
		// TODO Auto-generated method stub
		List<RechargeConfig> rlist=rechargeConfigService.findAll();
		return new JsonResult().setSuccess(true).setObj(rlist);
	}
	@Override
	public JsonResult mallConfig() {
		// TODO Auto-generated method stub
		List<MallConfig> list=mallConfigService.findAll();
		MallConfig config=null;
		if(null!=list && list.size()>0){
			 config=list.get(0);
		} 
		return new JsonResult().setSuccess(true).setObj(config);
	}

}
