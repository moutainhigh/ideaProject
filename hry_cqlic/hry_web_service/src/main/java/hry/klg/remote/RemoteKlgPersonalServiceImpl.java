package hry.klg.remote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.pagehelper.Page;

import hry.klg.level.model.vo.KlgTeamlevelVo;
import hry.klg.level.service.KlgTeamlevelService;
import hry.klg.remote.model.KlgRecommendRemote;
import hry.manage.remote.model.base.FrontPage;
import hry.util.PageFactory;

public class RemoteKlgPersonalServiceImpl implements RemoteKlgPersonalService {

	@Resource
    private KlgTeamlevelService klgTeamlevelService;
	
	@Override
	public FrontPage findRecommenderList(Map<String, String> params) {
		// TODO Auto-generated method stub
		Long customerId = Long.valueOf(params.get("customerId"));// 用户id
		List<KlgRecommendRemote> listRemote = new ArrayList<>();//记录返回数据
		KlgRecommendRemote klgRecommendRemote = null;
		KlgTeamlevelVo klgTeamlevelNumVo = null;
		KlgTeamlevelVo klgTeamlevelSumVo = null;
		//查询十代推荐信息
		for(int i=1;i<=10;i++){
			klgRecommendRemote = new KlgRecommendRemote();
			//查询星级用户数非星级用户数
			klgTeamlevelNumVo = klgTeamlevelService.getStarVipCount(customerId, i);
			if(klgTeamlevelNumVo==null){
				break;
			}
			if(klgTeamlevelNumVo.getStartNum()<=0&&klgTeamlevelNumVo.getNoStartNum()<=0){
				//说明下面没有推荐人
				break;
			}
			//统计用户排单总数和本周排单数
			klgTeamlevelSumVo = klgTeamlevelService.getBuyMoneyByDate(customerId, i);
			if(klgTeamlevelSumVo!=null){
				klgRecommendRemote.setBuySum(klgTeamlevelSumVo.getBuySum()==null?new BigDecimal(0):klgTeamlevelSumVo.getBuySum());
				klgRecommendRemote.setWeekBuySum(klgTeamlevelSumVo.getWeekBuySum().compareTo(new BigDecimal(0))==0?new BigDecimal(0):klgTeamlevelSumVo.getWeekBuySum());
			}
			
			klgRecommendRemote.setLevel(i);
			klgRecommendRemote.setStartNum(klgTeamlevelNumVo.getStartNum());
			klgRecommendRemote.setNoStartNum(klgTeamlevelNumVo.getNoStartNum());
			listRemote.add(klgRecommendRemote);
		}
		
		Page page = PageFactory.getPage(params);
		return new FrontPage(listRemote, page.getTotal(), page.getPages(), page.getPageSize());
	}

}
