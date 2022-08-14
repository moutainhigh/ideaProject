package hry.klg.remote;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.pagehelper.Page;

import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.klg.prizedraw.dao.KlPrizedrawSelectionDao;
import hry.klg.prizedraw.dao.KlgPrizedrawCustomerDao;
import hry.klg.prizedraw.model.KlPrizedrawSelection;
import hry.klg.prizedraw.model.KlgPrizedrawCustomer;
import hry.klg.prizedraw.model.KlgPrizedrawLssue;
import hry.klg.prizedraw.service.KlgPrizedrawCustomerService;
import hry.klg.prizedraw.service.KlgPrizedrawLssueService;
import hry.klg.remote.model.KlPrizedrawSelectionRemote;
import hry.klg.remote.model.RemoteKlgPrizedrawCustomer;
import hry.manage.remote.model.base.FrontPage;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import net.sf.json.JSONObject;

public class RemoteKlgLuckDrawServiceImpl implements RemoteKlgLuckDrawService {
	
	@Resource
	private KlPrizedrawSelectionDao klPrizedrawSelectionDao;
	
	@Resource
	private KlgPrizedrawCustomerDao klgPrizedrawCustomerDao;
	
	@Resource
	private KlgPrizedrawLssueService klgPrizedrawLssueService;
	
	@Resource
	private KlgPrizedrawCustomerService klgPrizedrawCustomerService;
	
	@Resource
	private RemoteKlgService remoteKlgService;
	
	@Resource
	private AppPersonInfoService appPersonInfoService;

	@Override
	public FrontPage findSelectionList(Map<String, String> params) {
		// TODO Auto-generated method stub
		//查询上一期期号
		QueryFilter filterIssue = new QueryFilter(KlgPrizedrawLssue.class);
		filterIssue.addFilter("status=", 3);
		filterIssue.setOrderby("issueNumber desc");
		KlgPrizedrawLssue klgPrizedrawLssue = klgPrizedrawLssueService.get(filterIssue);
		List<KlPrizedrawSelection> list = new ArrayList<>();
		Page page = PageFactory.getPage(params);
		if(klgPrizedrawLssue!=null){
			params.put("issueNumber", String.valueOf(klgPrizedrawLssue.getIssueNumber()));
			params.put("orderdesc", "1");
			list = klPrizedrawSelectionDao.findPageBySql(params);
		}
		List<KlPrizedrawSelectionRemote> beanList = ObjectUtil.beanList(list, KlPrizedrawSelectionRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}

	@Override
	public JsonResult getLastIssue(Map<String, String> params) {
		// TODO Auto-generated method stub
		//查询上一期开奖信息
		QueryFilter filterIssueLast = new QueryFilter(KlgPrizedrawLssue.class);
		filterIssueLast.addFilter("status=", 3);
		filterIssueLast.setOrderby("issueNumber desc");
		KlgPrizedrawLssue klgPrizedrawLssueLast = klgPrizedrawLssueService.get(filterIssueLast);
		if(klgPrizedrawLssueLast!=null){
			if(klgPrizedrawLssueLast.getLotteryNumber()==null||klgPrizedrawLssueLast.getLotteryNumber().equals("")){
				klgPrizedrawLssueLast.setPrimeNumber(null);
			}
		}else{
			klgPrizedrawLssueLast = new KlgPrizedrawLssue();
		}
		
		//查询本期开奖信息
		KlgPrizedrawLssue klgPrizedrawLssue = klgPrizedrawLssueService.getIssue();
				
		Map<String, Object> resulrMap = new HashMap<>();
		resulrMap.put("lastIssue", klgPrizedrawLssueLast);//上一期
		resulrMap.put("currentIssue", klgPrizedrawLssue);//本期
		//JSONObject json = JSONObject.fromObject(resulrMap);
		return new JsonResult().setSuccess(true).setObj(resulrMap);
	}

	@Override
	public FrontPage findMyLuckDrawList(Map<String, String> params) {
		// TODO Auto-generated method stub
		//查询本期开奖信息
		QueryFilter filterThisIssue = new QueryFilter(KlgPrizedrawLssue.class);
		filterThisIssue.addFilter("status=", 2);
		KlgPrizedrawLssue klgPrizedrawLssue = klgPrizedrawLssueService.get(filterThisIssue);
		if(klgPrizedrawLssue!=null){
			params.put("lastIssueNumber", String.valueOf(klgPrizedrawLssue.getIssueNumber()));
		}
		
		Page page = PageFactory.getPage(params);
		List<KlgPrizedrawCustomer> list = klgPrizedrawCustomerService.findPageBySql(params);
		List<RemoteKlgPrizedrawCustomer> beanList = ObjectUtil.beanList(list, RemoteKlgPrizedrawCustomer.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}

	@Override
	public FrontPage findMyprizeList(Map<String, String> params) {
		// TODO Auto-generated method stub
		Page page = PageFactory.getPage(params);
		List<KlPrizedrawSelection> list = klPrizedrawSelectionDao.findPageBySql(params);
		List<KlPrizedrawSelectionRemote> beanList = ObjectUtil.beanList(list, KlPrizedrawSelectionRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());

	}

	@Override
	public FrontPage findMyThisLuckDrawList(Map<String, String> params) {
		// TODO Auto-generated method stub
		//查询本期开奖信息
		KlgPrizedrawLssue klgPrizedrawLssue = klgPrizedrawLssueService.getIssue();
		params.put("issueNumber", String.valueOf(klgPrizedrawLssue.getIssueNumber()));
		Page page = PageFactory.getPage(params);
		List<KlgPrizedrawCustomer> list = klgPrizedrawCustomerService.findPageBySql(params);
		List<RemoteKlgPrizedrawCustomer> beanList = ObjectUtil.beanList(list, RemoteKlgPrizedrawCustomer.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());

	}

	@Override
	public JsonResult getMySurplusLuckdrawCount(Map<String, String> params) {
		// TODO Auto-generated method stub
		Long customerId = Long.valueOf(params.get("customerId"));
		int count = 0;
		
		Map<String, String> map = new HashMap<>();
		map.put("customerId", customerId.toString());
		
		//查询用户本周排单次数
		int countBuyOrder = klgPrizedrawCustomerService.getCustomerBuyOrderCount(map);
		if(countBuyOrder>0){
			//查询用户等级，根据等级获取抽奖次数限制
			int countLevel = klgPrizedrawCustomerService.getCustomerDrawCount(map);
			
			//查询用户本周已经抽奖次数 
			int countIssueDraw = klgPrizedrawCustomerService.getCustomerIssueDrawCount(map);
			if(countIssueDraw<countLevel){
				count = countLevel - countIssueDraw;
			}
		}
		return new JsonResult().setSuccess(true).setObj(count);
	}

	@Override
	public JsonResult addLuckDrawNum(Map<String, String> params) {
		// TODO Auto-generated method stub
		Long customerId = Long.valueOf(params.get("customerId"));
		String number = String.valueOf(params.get("number"));
		if(number==null||number.equals("")){
			return new JsonResult().setSuccess(false).setMsg("抽奖号码不能为空");
		}
		String regex = "^\\d{8,8}$";
		boolean matches = number.matches(regex);
		if(!matches){
			return new JsonResult().setSuccess(false).setMsg("抽奖号码只能为7位数字");
		}
		
		JsonResult jsonR = this.getMySurplusLuckdrawCount(params);
		if(Integer.valueOf(String.valueOf(jsonR.getObj()))<=0){
			return new JsonResult().setSuccess(false).setMsg("抽奖次数用完");
		}
		//查询本期抽奖信息
		//KlgPrizedrawLssue klgPrizedrawLssue = klgPrizedrawLssueService.getIssue();
		//查询本期开奖信息
		QueryFilter filterThisIssue = new QueryFilter(KlgPrizedrawLssue.class);
		filterThisIssue.addFilter("status=", 2);
		KlgPrizedrawLssue klgPrizedrawLssue = klgPrizedrawLssueService.get(filterThisIssue);
		
		KlgPrizedrawCustomer klgPrizedrawCustomer = new KlgPrizedrawCustomer();
		klgPrizedrawCustomer.setCustomerId(customerId);
		klgPrizedrawCustomer.setIssueNumber(klgPrizedrawLssue.getIssueNumber());
		klgPrizedrawCustomer.setPrizedrawNumber(number);
		klgPrizedrawCustomer.setStartDate(klgPrizedrawLssue.getEndDate());
		klgPrizedrawCustomer.setStatus(1);
		klgPrizedrawCustomerService.save(klgPrizedrawCustomer);
		return new JsonResult().setSuccess(true).setMsg("添加成功");
	}

	@Override
	public void appFirstBuyCustomer(Long customerId) {
		try {
			// TODO Auto-generated method stub
			//查询大奖是否开启
			String luckDrawOpen = (String) remoteKlgService.getPlatformRule1sConfig("luckDrawOpen");
			if(luckDrawOpen.equals("0")){
				return;
			}
			//查询用户信息
			QueryFilter filterPerson = new QueryFilter(AppPersonInfo.class);
			filterPerson.addFilter("customerId=", customerId);
			AppPersonInfo appPersonInfo = appPersonInfoService.get(filterPerson);
			if(appPersonInfo==null){
				return;
			}
			if(appPersonInfo.getMobilePhone()==null||appPersonInfo.getMobilePhone().equals("")){
				return;
			}
			//查询本期开奖信息
			QueryFilter filterThisIssue = new QueryFilter(KlgPrizedrawLssue.class);
			filterThisIssue.addFilter("status=", 2);
			KlgPrizedrawLssue klgPrizedrawLssue = klgPrizedrawLssueService.get(filterThisIssue);
			if(klgPrizedrawLssue==null){
				return;
			}
			//查询今天星期几
			Date today = new Date();
	        Calendar c=Calendar.getInstance();
	        c.setTime(today);
	        int weekday=c.get(Calendar.DAY_OF_WEEK);
	        switch (weekday) {
				case 1: //星期日
					if(klgPrizedrawLssue.getSundayNumber()!=null&&!klgPrizedrawLssue.getSundayNumber().equals("")){
						return;
					}else{
						klgPrizedrawLssue.setSundayNumber(appPersonInfo.getMobilePhone());
						klgPrizedrawLssue.setSundayDate(new Date());
						klgPrizedrawLssueService.update(klgPrizedrawLssue);
					}
					break;
				case 2: //星期一
					if(klgPrizedrawLssue.getMondayNumber()!=null&&!klgPrizedrawLssue.getMondayNumber().equals("")){
						return;
					}else{
						klgPrizedrawLssue.setMondayNumber(appPersonInfo.getMobilePhone());
						klgPrizedrawLssue.setMondayDate(new Date());
						klgPrizedrawLssueService.update(klgPrizedrawLssue);
					}
					break;
				case 3: //星期二
					if(klgPrizedrawLssue.getTuesdayNumber()!=null&&!klgPrizedrawLssue.getTuesdayNumber().equals("")){
						return;
					}else{
						klgPrizedrawLssue.setTuesdayNumber(appPersonInfo.getMobilePhone());
						klgPrizedrawLssue.setTuesdayDate(new Date());
						klgPrizedrawLssueService.update(klgPrizedrawLssue);
					}
					break;
				case 4: //星期三
					if(klgPrizedrawLssue.getWednesdayNumber()!=null&&!klgPrizedrawLssue.getWednesdayNumber().equals("")){
						return;
					}else{
						klgPrizedrawLssue.setWednesdayNumber(appPersonInfo.getMobilePhone());
						klgPrizedrawLssue.setWednesdayDate(new Date());
						klgPrizedrawLssueService.update(klgPrizedrawLssue);
					}
					break;
				case 5: //星期四
					if(klgPrizedrawLssue.getThursdayNumber()!=null&&!klgPrizedrawLssue.getThursdayNumber().equals("")){
						return;
					}else{
						klgPrizedrawLssue.setThursdayNumber(appPersonInfo.getMobilePhone());
						klgPrizedrawLssue.setThursdayDate(new Date());
						klgPrizedrawLssueService.update(klgPrizedrawLssue);
					}
					break;
				case 6: //星期五
					if(klgPrizedrawLssue.getFridayNumber()!=null&&!klgPrizedrawLssue.getFridayNumber().equals("")){
						return;
					}else{
						klgPrizedrawLssue.setFridayNumber(appPersonInfo.getMobilePhone());
						klgPrizedrawLssue.setFridayDate(new Date());
						klgPrizedrawLssueService.update(klgPrizedrawLssue);
					}
					break;
				case 7: //星期六
					if(klgPrizedrawLssue.getSaturdayNumber()!=null&&!klgPrizedrawLssue.getSaturdayNumber().equals("")){
						return;
					}else{
						klgPrizedrawLssue.setSaturdayNumber(appPersonInfo.getMobilePhone());
						klgPrizedrawLssue.setSaturdayDate(new Date());
						klgPrizedrawLssueService.update(klgPrizedrawLssue);
					}
					break;
		
				default:
					break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
