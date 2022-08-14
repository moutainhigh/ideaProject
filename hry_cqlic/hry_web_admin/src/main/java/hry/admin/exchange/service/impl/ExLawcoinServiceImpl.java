/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-22 09:57:59 
 */
package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import hry.admin.exchange.model.ExLawcoin;
import hry.admin.exchange.service.ExLawcoinService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.redis.common.utils.RedisService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> ExLawcoinService </p>
 * @author:         tianpengyu
 * @Date :          2018-08-22 09:57:59  
 */
@Service("exLawcoinService")
public class ExLawcoinServiceImpl extends BaseServiceImpl<ExLawcoin, Long> implements ExLawcoinService{
	
	@Resource(name="exLawcoinDao")
	@Override
	public void setDao(BaseDao<ExLawcoin, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private RedisService redisService;

	public void initRedis(){
		List<ExLawcoin> list = super.findAll();
		String[][] str = new String[list.size()][4];
		for(int i=0;i <list.size(); i++){
			str[i][0] = list.get(i).getName();
			str[i][1] = list.get(i).getCoinSymbol();
			str[i][2] = list.get(i).getCoinCode();
			str[i][3] = list.get(i).getCoinDecimal() + "";
		}
		redisService.save("otc:exLawcoin", JSON.toJSONString(str));
	}
}
