/**
 * Copyright:
 * @author:      HeC
 * @version:     V1.0
 * @Date:        2018-10-18 14:48:05
 */
package hry.admin.lend.service.impl;

import com.github.pagehelper.Page;
import hry.admin.lend.dao.ExLendAccountDao;
import hry.admin.lend.model.ExLendAccount;
import hry.admin.lend.service.ExLendAccountService;
import hry.admin.lend.util.LendRedis;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.lend.constant.ExchangeLendKey;
import hry.lend.constant.LendConfig;
import hry.reward.model.page.FrontPage;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ExLendAccountService </p>
 * @author:         HeC
 * @Date :          2018-10-18 14:48:05  
 */
@Service("exLendAccountService")
public class ExLendAccountServiceImpl extends BaseServiceImpl<ExLendAccount, Long> implements ExLendAccountService{

	@Resource(name="exLendAccountDao")
	@Override
	public void setDao(BaseDao<ExLendAccount, Long> dao) {
		super.dao = dao;
	}


	@Override
	public FrontPage findPageByFilter(QueryFilter filter) {
        //创建Page对象
		Page page = PageFactory.getPage(filter);
		//----------------------查询开始------------------------------
		Map<String,Object> map = new HashMap<String,Object>();
		HttpServletRequest request = filter.getRequest();
        String email = request.getParameter("email");
        if(StringUtils.isNotEmpty(email)){
            map.put("email",email);
        }

        String mobilePhone = request.getParameter("mobilePhone");
        if(StringUtils.isNotEmpty(mobilePhone)){
            map.put("mobilePhone",mobilePhone);
        }

        String coinCode = request.getParameter("coinCode");
        if(StringUtils.isNotEmpty(coinCode)){
            map.put("coinCode",coinCode);
        }

        List<ExLendAccount> list = ((ExLendAccountDao)dao).findPageBySql(map);
		try(Jedis jedis = LendRedis.JEDIS_POOL.getResource()) {
            for (ExLendAccount exLendAccount : list) {
                Double riskStr = jedis.zscore(ExchangeLendKey.getRiskKey(exLendAccount.getCoinCode()), String.valueOf(exLendAccount.getCustomerId()));
				BigDecimal risk;
				if(null!=riskStr){
					risk = new BigDecimal(riskStr).multiply(new BigDecimal(100)).setScale(LendConfig.RATIO_KEEP_DECIMAL_HUNDRED,BigDecimal.ROUND_DOWN);
				}else{
					risk = LendConfig.LEND_MAX_RISK;
				}
				exLendAccount.setRisk(risk);
            }
        }catch(Exception e){
		    e.printStackTrace();
		}
		//----------------------查询结束------------------------------

		//设置分页数据
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}
}
