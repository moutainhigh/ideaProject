package hry.cm.remote;

import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.cm.dividend.model.CmDividendRecord;
import hry.cm.dividend.service.CmDividendRecordService;
import hry.cm.remote.model.CmDividendRecordRemote;
import hry.cm.remote.model.CmMinerRemote;
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
    private CmDividendRecordService cmDividendRecordService;

    @Override
    public List<CmDividendRecordRemote> findDividendRecordList(Long customerId) {
        QueryFilter filter = new QueryFilter(CmDividendRecord.class);
        filter.addFilter("customerId=",customerId);
        filter.addFilter("status=",1);
        List<CmDividendRecord> list = cmDividendRecordService.find(filter);
        List<CmDividendRecordRemote> beanList =ObjectUtil.beanList(list, CmDividendRecordRemote.class);
        return beanList;
    }

    @Override
    public JsonResult getDividend(Long dividendId) {
        return cmDividendRecordService.handelGiveOutDividend(dividendId);
    }
}
