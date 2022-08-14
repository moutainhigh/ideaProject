/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      shangxl
 * @version:     V1.0
 * @Date:        2017-11-13 19:17:02
 */
package hry.web.ExDmTransfColdDetail.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.util.PageFactory;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.StringUtil;
import hry.util.http.HttpConnectionUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.security.Check;
import hry.manage.remote.model.ExDmTransfColdDetailManage;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;
import hry.web.ExDmTransfColdDetail.dao.ExDmTransfColdDetailDao;
import hry.web.ExDmTransfColdDetail.model.ExDmTransfColdDetail;
import hry.web.ExDmTransfColdDetail.service.ExDmTransfColdDetailService;

/**
 * <p> ExDmTransfColdDetailService </p>
 * @author:         shangxl
 * @Date :          2017-11-13 19:17:02
 */
@Service("exDmTransfColdDetailService")
public class ExDmTransfColdDetailServiceImpl extends BaseServiceImpl<ExDmTransfColdDetail, Long> implements ExDmTransfColdDetailService{

	@Resource(name="exDmTransfColdDetailDao")
	@Override
	public void setDao(BaseDao<ExDmTransfColdDetail, Long> dao) {
		super.dao = dao;
	}

	@Override
	public FrontPage listTokenAssets(Map<String, String> params) {
		Page page = PageFactory.getPage(params);
		String coinType = params.get("coinType");
		List<Object> list = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(coinType)) {
			String url = PropertiesUtils.APP.getProperty("app.coinip");
			url = url + "/coin/listTokenAddressAssets";
			Map<String, String> map = new HashMap<String, String>();
			map.put("coinType", coinType);
			String param = StringUtil.string2params(map);
			String result = HttpConnectionUtil.postSend(url, param);
			if (StringUtils.isNotEmpty(result)) {
				JsonResult data = JSON.parseObject(result, JsonResult.class);
				if (data.getSuccess() && data.getObj() != null && StringUtils.isNotEmpty(data.getObj().toString())) {
					list = JSON.parseArray(data.getObj().toString(), Object.class);
				}
			}
		}
		return new FrontPage(list, Long.valueOf(list.size()), page.getPages(), page.getPageSize());
	}

	@Override
	public RemoteResult rechargeTxFee2TokenAddress(Map<String, String> params) {
		RemoteResult result = new RemoteResult();
		String address = params.get("address");
		String amount = params.get("amount");
		String url = PropertiesUtils.APP.getProperty("app.coinip");
		url = url + "/coin/rechargeTxFee2TokenAddress";
		String args[] = { address, amount };
		String authcode = Check.authCode(args);
		params.put("authcode", authcode);
		String param = StringUtil.string2params(params);
		String data = HttpConnectionUtil.postSend(url, param);
		if (StringUtils.isNotEmpty(data)) {
			result = JSON.parseObject(data, RemoteResult.class);
			if (result.getSuccess()) {
				result.setMsg("充值成功");
			}
		} else {
			result.setMsg("网络错误");
		}
		return result;

		//15010391050
	}

	@Override
	public RemoteResult tokenCollect(Map<String, String> params) {
		RemoteResult result = new RemoteResult();
		String url = PropertiesUtils.APP.getProperty("app.coinip");
		url = url + "/coin/tokenCollect";
		String param = StringUtil.string2params(params);
		String data = HttpConnectionUtil.postSend(url, param);
		result = JSON.parseObject(data, RemoteResult.class);
		if (result.getSuccess()) {
			result.setMsg("归集成功");
		}
		return result;
	}

	@Override
	public RemoteResult toColdAccount(Map<String, String> params) {
		RemoteResult jr = new RemoteResult();
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
				jr = JSON.parseObject(result, RemoteResult.class);
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

	@Override
	public FrontPage transfColdRecordList(Map<String, String> params) {
		Page page = PageFactory.getPage(params);
		// 查询方法
		List<ExDmTransfColdDetail> list = ((ExDmTransfColdDetailDao) dao).findFrontPageBySql(params);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}

	@Override
	public ExDmTransfColdDetailManage transfColdDetail(Long id) {
		ExDmTransfColdDetailManage manage = ((ExDmTransfColdDetailDao) dao).selectByPId(id);
		return manage;
	}

	@Override
	public FrontPage listWalletBalance(Map<String, String> params) {
		Page page = PageFactory.getPage(params);
		String url = PropertiesUtils.APP.getProperty("app.coinip");
		url = url + "/coin/listWalletBalance";
		String result = HttpConnectionUtil.getSend(url, null);
		List<Object> list = new ArrayList<>();
		if (StringUtils.isNotEmpty(result)) {
			list = JSON.parseArray(result, Object.class);
		}
		return new FrontPage(list, Long.valueOf(list.size()), page.getPages(), page.getPageSize());
	}

}
