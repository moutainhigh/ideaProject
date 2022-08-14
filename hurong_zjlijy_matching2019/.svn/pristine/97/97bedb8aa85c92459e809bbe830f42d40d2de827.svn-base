package hry.cm.remote;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.pagehelper.Page;

import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.cm.miner.model.CmMiner;
import hry.cm.miner.service.CmMinerService;
import hry.cm.remote.model.CmMinerRemote;
import hry.util.PageFactory;
import hry.util.QueryFilter;

public class RemoteCmMinerServiceImpl implements RemoteCmMinerService {
	
	@Resource
	private CmMinerService cmMinerService;

	@Override
	public FrontPage findMinminglist(Map<String, String> params) {
		// TODO Auto-generated method stub
		Page page = PageFactory.getPage(params);
		QueryFilter filter = new QueryFilter(CmMiner.class);
		filter.addFilter("status=", 1);
		List<CmMiner> list = cmMinerService.find(filter);
		List<CmMinerRemote> beanList = ObjectUtil.beanList(list, CmMinerRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}

}
