package hry.cm2.remote;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.cm2.miner.model.Cm2Miner;
import hry.cm2.miner.service.Cm2MinerService;
import hry.cm2.remote.model.CmMinerRemote;
import hry.util.PageFactory;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class RemoteCmMinerServiceImpl implements RemoteCmMinerService {
	
	@Resource
	private Cm2MinerService cmMinerService;

	@Override
	public FrontPage findMinminglist(Map<String, String> params) {
		// TODO Auto-generated method stub
		Page page = PageFactory.getPage(params);
		QueryFilter filter = new QueryFilter(Cm2Miner.class);
		filter.addFilter("status=", 1);
		List<Cm2Miner> list = cmMinerService.find(filter);
		List<CmMinerRemote> beanList = ObjectUtil.beanList(list, CmMinerRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}

}
