/**
 * Copyright:   
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 14:47:26 
 */
package hry.admin.lend.service.impl;

import com.github.pagehelper.Page;
import hry.admin.lend.dao.ExLendAccountDao;
import hry.admin.lend.dao.ExLendConfigDao;
import hry.admin.lend.model.ExLendAccount;
import hry.admin.lend.model.ExLendConfig;
import hry.admin.lend.service.ExLendConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.reward.model.page.FrontPage;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ExLendConfigService </p>
 * @author:         HeC
 * @Date :          2018-10-18 14:47:26  
 */
@Service("exLendConfigService")
public class ExLendConfigServiceImpl extends BaseServiceImpl<ExLendConfig, Long> implements ExLendConfigService{
	
	@Resource(name="exLendConfigDao")
	@Override
	public void setDao(BaseDao<ExLendConfig, Long> dao) {
		super.dao = dao;
	}


	@Override
	public List<Map<String, String>> getCoinCodes() {
		List<String> coinCodes = ((ExLendConfigDao)dao).getCoinCodes();
		List<Map<String,String>> list = new ArrayList<>();
		for (String coinCode : coinCodes) {
			Map<String,String> map = new HashMap<>();
			map.put("coinCode",coinCode);
			list.add(map);
		}
		return list;
	}

	@Override
	public FrontPage findList(QueryFilter filter) {
		//创建Page对象
		Page page = PageFactory.getPage(filter);
        HttpServletRequest request = filter.getRequest();
        Map<String,Object> map = new HashMap<>();
        String coin = request.getParameter("coin");
        if(StringUtils.isNotEmpty(coin)){
            map.put("priCoin",coin);
        }

        List<ExLendConfig> list = ((ExLendConfigDao)dao).findPageBySql(map);

		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}
}
