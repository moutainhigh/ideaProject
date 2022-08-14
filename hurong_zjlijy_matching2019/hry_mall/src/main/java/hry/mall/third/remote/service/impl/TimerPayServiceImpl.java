package hry.mall.third.remote.service.impl;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.mall.integral.remote.RemoteRollService;
import hry.mall.platform.model.ThirdpayRecord;
//import hry.mall.platform.remote.model.ThirdpayRecord;   todo 需测试
//import hry.mall.platform.remote.service.ThirdpayRecordService;  todo 需测试
import hry.mall.platform.service.ThirdpayRecordService;
import hry.mall.third.remote.service.TimerPayService;
import hry.mall.third.remote.service.WeixingPayService;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther zhouming
 * @date 2019/3/20 10:09
 * @Version 1.0
 */
@Service("timerPayService")
public class TimerPayServiceImpl implements TimerPayService {
    @Resource
    private WeixingPayService weixingPayService;
    @Resource
    private ThirdpayRecordService thirdpayRecordService;

    @Override
    public void payOrderQuery() {
        System.out.println("微星订单查询start-----------------");
        int pageSize = 100;  //分页执行数据
        QueryFilter filter = new QueryFilter(ThirdpayRecord.class);
        filter.addFilter("thirdPayConfig=","weixing");
        filter.addFilter("status=","1");
        List<ThirdpayRecord> recordList = thirdpayRecordService.find(filter);
        int count = recordList.size();
        int rate = count/pageSize;
        filter.setPageSize(pageSize);
        for (int i = 0; i <= rate; i++) {
            filter.setPage(i);
            PageResult pageResult = thirdpayRecordService.findPageResult(filter);
            List<ThirdpayRecord> records = pageResult.getRows();
            for (int k = 0; k < records.size(); k++) {
                ThirdpayRecord record = records.get(k);
                String sporderid = record.getRecordNumber();
                JsonResult jsonResult = weixingPayService.getPayOrderQuery(sporderid);
                if (jsonResult.getSuccess()) {
                    // 第三方返回状态码 1:充值成功 2:充值中 9:充值失败
                    if ("1".equals(jsonResult.getObj())) {
                        record.setStatus(2); // 充值成功
                    } else if ("9".equals(jsonResult.getObj())) {
                        record.setStatus(3); // 充值失败
                    } else if ("2".equals(jsonResult.getObj())) {
                    } else {
                        record.setStatus(3); // 充值失败
                    }
                } else {
                   record.setStatus(3); // 充值失败
                }
                record.setCode(jsonResult.getObj().toString()); // 第三方返回状态码
                record.setCodeMsg(jsonResult.getMsg()); // 第三方返回状态说明
                thirdpayRecordService.update(record);
                // 修改积分明细及用户积分表
                RemoteRollService remoteRollService = SpringUtil.getBean("remoteRollService");
                hry.mall.integral.remote.model.ThirdpayRecord record1 = new hry.mall.integral.remote.model.ThirdpayRecord();
                BeanUtilsBean.getInstance().getConvertUtils().register(new SqlDateConverter(null),java.sql.Date.class);
                BeanUtils.copyProperties(record, record1);
                remoteRollService.updateIntegralBus(record1);
            }
        }
    }
}
