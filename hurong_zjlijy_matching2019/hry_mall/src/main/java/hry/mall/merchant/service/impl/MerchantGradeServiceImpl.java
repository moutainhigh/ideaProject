/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-11-26 16:41:38 
 */
package hry.mall.merchant.service.impl;

import hry.mall.merchant.model.MerchantGrade;
import hry.mall.merchant.service.MerchantGradeService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> MerchantGradeService </p>
 * @author:         zhouming
 * @Date :          2019-11-26 16:41:38  
 */
@Service("merchantGradeService")
public class MerchantGradeServiceImpl extends BaseServiceImpl<MerchantGrade, Long> implements MerchantGradeService{
	
	@Resource(name="merchantGradeDao")
	@Override
	public void setDao(BaseDao<MerchantGrade, Long> dao) {
		super.dao = dao;
	}
	

}
