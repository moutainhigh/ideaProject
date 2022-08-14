package hry.cm2.remote;

import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.cm2.remote.model.CmDividendRecordRemote;
import hry.cm2.dividend.model.Cm2DividendRecord;
import hry.cm2.dividend.service.Cm2DividendRecordService;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther zhouming
 * @date 2019/8/1 14:09
 * @Version 1.0
 */
public class RemoteCmDividendServiceImpl implements RemoteCmDividendService {

    @Resource
    private Cm2DividendRecordService cmDividendRecordService;

    @Override
    public List<CmDividendRecordRemote> findDividendRecordList(Long customerId) {
        QueryFilter filter = new QueryFilter(Cm2DividendRecord.class);
        filter.addFilter("customerId=",customerId);
        filter.addFilter("status=",1);
        List<Cm2DividendRecord> list = cmDividendRecordService.find(filter);
        List<CmDividendRecordRemote> beanList =ObjectUtil.beanList(list, CmDividendRecordRemote.class);
        return beanList;
    }

    @Override
    public JsonResult getDividend(Long dividendId) {
        return cmDividendRecordService.handelGiveOutDividend(dividendId);
    }
}
