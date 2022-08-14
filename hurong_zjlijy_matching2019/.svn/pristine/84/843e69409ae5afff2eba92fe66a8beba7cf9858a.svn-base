/**
 * Copyright:
 *
 * @author: jidn
 * @version: V1.0
 * @Date: 2019-04-03 11:06:50
 */
package hry.financail.financing.service.impl;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.financail.financing.dao.AppFinancialProductsDao;
import hry.financail.financing.model.AppFinancialProducts;
import hry.financail.financing.service.AppFinancialProductsService;
import hry.util.PageFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p> AppFinancialProductsService </p>
 *
 * @author: jidn
 * @Date :          2019-04-03 11:06:50
 */
@Service("appFinancialProductsService")
public class AppFinancialProductsServiceImpl extends BaseServiceImpl<AppFinancialProducts,String> implements AppFinancialProductsService {

    @Resource(name = "appFinancialProductsDao")
    @Override
    public void setDao(BaseDao<AppFinancialProducts,String> dao) {
        super.dao = dao;
    }

    @Resource
    private AppFinancialProductsDao appFinancialProductsDao;

    @Override
    public List<hry.remote.model.AppFinancialProducts> findFinaningProductList(Map<String,String> paramMap) {
        return appFinancialProductsDao.findFinaningProductList(paramMap);
    }

    @Override
    public FrontPage findUserFinaningProductList(Map<String,String> paramMap) {
        Page page = PageFactory.getPage(paramMap);

        List<hry.remote.model.AppFinancialProducts> userFinaningProductList = appFinancialProductsDao.findUserFinaningProductList(paramMap);
        for (hry.remote.model.AppFinancialProducts afps : userFinaningProductList) {
            Integer status = afps.getStatus();
            Integer stage = afps.getStage();
            Integer isRedeem = afps.getIsRedeem();
            long nowTime = new Date().getTime();
            Date startTime = afps.getStartTime();
            Date endTime = afps.getEndTime();
            Date preheatingTime = afps.getPreheatingTime();
            Date startingInterestTime = afps.getStartingInterestTime();
            Date returnOfPrincipalTime = afps.getReturnOfPrincipalTime();

        }


        return new FrontPage(userFinaningProductList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public hry.remote.model.AppFinancialProducts findOne(Map<String,String> paramMap) {
        return appFinancialProductsDao.findOne(paramMap);
    }

    @Override
    public hry.remote.model.AppFinancialProducts findOne_cy(Map<String,String> paramMap) {
        return appFinancialProductsDao.findOne_cy(paramMap);
    }
}
