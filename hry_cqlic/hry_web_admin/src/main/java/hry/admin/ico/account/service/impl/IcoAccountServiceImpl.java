/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 12:02:43 
 */
package hry.admin.ico.account.service.impl;

import com.github.pagehelper.Page;
import hry.admin.ico.account.dao.IcoAccountDao;
import hry.admin.ico.account.model.IcoAccount;
import hry.admin.ico.account.model.IcoAccountAtioPo;
import hry.admin.ico.account.service.IcoAccountService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> IcoAccountService </p>
 * @author:         lzy
 * @Date :          2019-01-14 12:02:43  
 */
@Service("icoAccountService")
public class IcoAccountServiceImpl extends BaseServiceImpl<IcoAccount, Long> implements IcoAccountService{
	//锁仓比率 40%
	private final double RATIO=0.4;
	@Resource(name="icoAccountDao")
	@Override
	public void setDao(BaseDao<IcoAccount, Long> dao) {
		super.dao = dao;
	}


	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		Page<IcoAccountAtioPo> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------
		//----------------------查询开始------------------------------
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String levelId = filter.getRequest().getParameter("level");
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(levelId)){
			map.put("levelId",levelId);
		}
		((IcoAccountDao)dao).getAccountAtioBylevelSort(map);
		List<IcoAccountAtioPo> list=page.getResult();
		if(list!=null&&list.size()>0){
			for (IcoAccountAtioPo atioPo:list) {
				BigDecimal accountAtio= new BigDecimal(atioPo.getAccountAtio());//自身占比；
				BigDecimal additionRatio=(new BigDecimal(atioPo.getAdditionRatio()).add(new BigDecimal(100))).divide(new BigDecimal(100),8,BigDecimal.ROUND_DOWN);//等级加成比率
				accountAtio=accountAtio.multiply(additionRatio).multiply(new BigDecimal(RATIO));
				atioPo.setAccountAtio(accountAtio.setScale(6,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
			}
		}
		//----------------------分页查询底部外壳------------------------------
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
}
