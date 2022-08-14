/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-03-22 18:27:36 
 */
package hry.mall.integral.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.model.IntegralCoinchange;
import hry.mall.integral.service.IntegralCoinchangeService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p> IntegralCoinchangeService </p>
 * @author:         luyue
 * @Date :          2019-03-22 18:27:36  
 */
@Service("integralCoinchangeService")
public class IntegralCoinchangeServiceImpl extends BaseServiceImpl<IntegralCoinchange, Long> implements IntegralCoinchangeService{
	
	@Resource(name="integralCoinchangeDao")
	@Override
	public void setDao(BaseDao<IntegralCoinchange, Long> dao) {
		super.dao = dao;
	}

	@Override
	public String createNumber() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        QueryFilter filter = new QueryFilter(IntegralCoinchange.class);
        filter.addFilter("number=", "%DH"+newDate+"%");
        filter.setOrderby("id desc");
        List<IntegralCoinchange> list=this.find(filter);
        IntegralCoinchange change=null;
        if(null!=list && list.size()>0){
        	change=list.get(0);
        }
        String rnumber = new String(""); //订单编号
        if(null!=change){
        	StringBuffer number = new StringBuffer("");
			String proNum = change.getNumber();
			String ss = proNum.substring(proNum.length()-3);
			int num = new Integer(ss);
			num+=1;
			if(num<10){
				number = number.append("00").append(num);
			}else if(num>=10&&num<100){
				number = number.append("0").append(num);
			}else {
				number = number.append(num);
			}
			rnumber=newDate+number.toString();
        	
        }else{
        	rnumber=newDate+"001";
        }
		return rnumber;
	}
	

}
