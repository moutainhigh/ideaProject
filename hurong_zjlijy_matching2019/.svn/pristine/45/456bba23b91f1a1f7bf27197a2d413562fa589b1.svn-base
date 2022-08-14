package hry.cm4.remote;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.cm4.miner.model.Cm4Miner;
import hry.cm4.miner.service.Cm4MinerService;
import hry.cm4.remote.model.CmMinerRemote;
import hry.util.PageFactory;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class RemoteCmMinerServiceImpl implements RemoteCmMinerService {
	
	@Resource
	private Cm4MinerService cmMinerService;

	@Override
	public FrontPage findMinminglist(Map<String, String> params) {
		// TODO Auto-generated method stub
		Page page = PageFactory.getPage(params);
		QueryFilter filter = new QueryFilter(Cm4Miner.class);
		filter.addFilter("status=", 1);
		List<Cm4Miner> list = cmMinerService.find(filter);
		List<CmMinerRemote> beanList = ObjectUtil.beanList(list, CmMinerRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}

}
