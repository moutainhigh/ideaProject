/**
 * Copyright:
 * @author:      tianpengyu
 * @version:     V1.0
 * @Date:        2018-06-27 18:01:50
 */
package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import hry.admin.exchange.model.ExDmTransfColdDetail;
import hry.admin.exchange.service.ExDmTransfColdDetailService;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.StringUtil;
import hry.util.http.HttpConnectionUtil;
import hry.util.properties.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * <p> ExDmTransfColdDetailService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-27 18:01:50
 */
@Service("exDmTransfColdDetailService")
public class ExDmTransfColdDetailServiceImpl extends BaseServiceImpl<ExDmTransfColdDetail, Long> implements ExDmTransfColdDetailService{

	@Resource(name="exDmTransfColdDetailDao")
	@Override
	public void setDao(BaseDao<ExDmTransfColdDetail, Long> dao) {
		super.dao = dao;
	}

	@Override
	public JsonResult toColdAccount(Map<String, String> params) {
		JsonResult jr = new JsonResult();
		String result = null;
		String type = params.get("type");
		String amount = params.get("amount");
		if (StringUtil.isNull(type) && StringUtil.isNull(amount)) {
			String param = StringUtil.string2params(params);
			String url = PropertiesUtils.APP.getProperty("app.coinip");
			url = url + "/coin/toColdAccount";
			try {
				result = HttpConnectionUtil.getSend(url, param);
			} catch (Exception e) {
				jr.setMsg("后台异常");
			}
			if (StringUtils.isNotEmpty(result)) {
				jr = JSON.parseObject(result, JsonResult.class);
				if (jr.getSuccess()) {
					Map<String, String> data = (Map<String, String>) jr.getObj();
					String fromAddress = "";
					String toAddress = "";
					String txHash = "";
					if (data.get("fromAddress") != null) {
						fromAddress = data.get("fromAddress").toString();
					}
					if (data.get("toAddress") != null) {
						toAddress = data.get("toAddress").toString();
					}
					if (data.get("txHash") != null) {
						txHash = data.get("txHash").toString();
					}
					String money = amount;
					ExDmTransfColdDetail coldDetail = new ExDmTransfColdDetail();
					coldDetail.setCoinCode(type);
					coldDetail.setFromAddress(fromAddress);
					coldDetail.setToAddress(toAddress);
					coldDetail.setAmount(new BigDecimal(money));
					coldDetail.setTx(txHash);
					coldDetail.setOperator("admin");
					Serializable ss = this.save(coldDetail);
					if (ss == null) {
						jr.setMsg("明细保存失败！");
						jr.setSuccess(false);
					}
				}
			}
		}
		return jr;
	}

}
