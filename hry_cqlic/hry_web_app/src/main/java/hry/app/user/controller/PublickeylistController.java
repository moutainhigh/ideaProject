package hry.app.user.controller;

import com.alibaba.fastjson.JSON;
import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.core.annotation.CommonLog;
import hry.core.shiro.PasswordHelper;
import hry.manage.remote.RemoteAppTransactionManageService;
import hry.manage.remote.model.*;
import hry.redis.common.utils.RedisService;
import hry.util.SortListUtil;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/publickeylist")
@Api(value= "个人中心 --> 提币地址管理", description ="个人中心 --> 提币地址管理", tags = "个人中心 --> 提币地址管理")
public class PublickeylistController {

	/**
	 * 注册类型属性编辑器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {

		// 系统注入的只能是基本类型，如int，char，String

		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());

		/**
		 * 防止XSS攻击，并且带去左右空格功能
		 */
		binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
	}

	@Resource
	private RedisService redisService;

	@Resource
	private RemoteAppTransactionManageService remoteAppTransactionManageService;

	/**
	 * 查询虚拟货币类型
	 * @return
	 */
	@ApiOperation(value = "查询虚拟货币类型", httpMethod = "GET", notes = "查询虚拟货币类型")
	@GetMapping("/getVcoinType")
	@ResponseBody
	public ApiJsonResult<List<ExProductManage>> getVcoinType (
			@ApiParam(name = "coinCode", value = "我要提币-添加币账户地址需传币种类型", required = true) @RequestParam("coinCode") String coinCode,
			HttpServletRequest request) {
		ApiJsonResult<List<ExProductManage>> result = new ApiJsonResult<List<ExProductManage>>();
		List<ExProductManage> listProduct = remoteAppTransactionManageService.listProduct();
		if (listProduct != null && listProduct.size() > 0) {
			if (!StringUtils.isEmpty(coinCode)) {
				for (int i = 0; i < listProduct.size(); i++) {
					if (listProduct.get(i).getCoinCode().equals(coinCode)) {
						String coinCodeOld = listProduct.get(0).getCoinCode();
						listProduct.get(0).setCoinCode(coinCode);
						listProduct.get(i).setCoinCode(coinCodeOld);
					}
				}
			}
			new SortListUtil<ExProductManage>().Sort(listProduct,"getCoinCode", "asc");
			result.setSuccess(true);
			result.setObj(listProduct);
			return result;
		}
		result.setSuccess(false);
		return result;
	}

	@CommonLog(name = "查询币地址列表")
	@ApiOperation(value = "查询币地址列表", httpMethod = "GET", notes = "查询币地址列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
	})
	@GetMapping("/user/findCoinAddr")
	@ResponseBody
	@RequiresAuthentication
	public ApiJsonResult<List<ExDmCustomerPublicKeyManage>> findCoinAddr(HttpServletRequest request){
		ApiJsonResult<List<ExDmCustomerPublicKeyManage>> jsonResult = new ApiJsonResult<List<ExDmCustomerPublicKeyManage>>();
		User user = TokenUtil.getUser(request);
		if (user == null) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("before_login"));
			return jsonResult;
		}

		List<ExDmCustomerPublicKeyManage> listPublic = remoteAppTransactionManageService.listPublic(user.getCustomerId());
		String productListStr = redisService.get("cn:productinfoListall");
		if (listPublic != null && listPublic.size() > 0) {
			if (!StringUtils.isEmpty(productListStr)) {
				List<Coin> productList = JSON.parseArray(productListStr, Coin.class);
				for (ExDmCustomerPublicKeyManage ex : listPublic) {
					for (Coin coin : productList) {
						if (ex.getCurrencyType().equals(coin.getCoinCode())) {
							ex.setPicturePath(coin.getPicturePath());
						}
					}
				}
				jsonResult.setSuccess(true);
				jsonResult.setObj(listPublic);
				return jsonResult;
			}
		}
		jsonResult.setSuccess(false);
		jsonResult.setObj(listPublic);
		return jsonResult;
	}

	/**
	 * 添加币地址
	 * @param request
	 * @return
	 */
	@CommonLog(name = "添加币地址")
	@ApiOperation(value = "添加币地址", httpMethod = "POST", notes = "添加币地址")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
	})
	@PostMapping("/user/addCoinAddr")
	@ResponseBody
	@RequiresAuthentication
	public ApiJsonResult<ExDmCustomerPublicKeyManage> addCoinAddr (
			@ApiParam(name = "currencyType", value = "虚拟货币类型", required = true) @RequestParam("currencyType") String currencyType,
			@ApiParam(name = "publicKey", value = "钱包地址", required = true) @RequestParam("publicKey") String publicKey,
			@ApiParam(name = "remark", value = "备注", required = true) @RequestParam("remark") String remark,
			HttpServletRequest request) {
		ApiJsonResult<ExDmCustomerPublicKeyManage> jsonresult = new ApiJsonResult<ExDmCustomerPublicKeyManage>();
		User user = TokenUtil.getUser(request);
		if (user != null) {
			try {
				// 封装币地址信息
				ExDmCustomerPublicKeyManage exDmCustomerPublicKeyManage = new ExDmCustomerPublicKeyManage();
				exDmCustomerPublicKeyManage.setCurrencyType(currencyType);
				exDmCustomerPublicKeyManage.setPublicKey(publicKey);
				exDmCustomerPublicKeyManage.setRemark(remark);

				//判断币账户是否存在
				List<ExDmCustomerPublicKeyManage> listPublic = remoteAppTransactionManageService.listPublic(user.getCustomerId());
				if (listPublic != null && listPublic.size() > 0) {
					for (ExDmCustomerPublicKeyManage manage : listPublic) {
						String listpublicKey = manage.getPublicKey();
						String listcurrencyType = manage.getCurrencyType();
						if (currencyType.equals(listcurrencyType) && publicKey.equals(listpublicKey)) {
							jsonresult.setSuccess(false);
							jsonresult.setMsg(SpringUtil.diff("waltbunengchongfu"));
							return jsonresult;
						}
					}
				}
				//保存币账户
				remoteAppTransactionManageService.save(exDmCustomerPublicKeyManage, user);
				jsonresult.setSuccess(true);
				jsonresult.setObj(exDmCustomerPublicKeyManage);
				jsonresult.setMsg(SpringUtil.diff("save_success"));
				return jsonresult;
			} catch (Exception e) {
				e.printStackTrace();
				jsonresult.setSuccess(false);
				jsonresult.setMsg(SpringUtil.diff("yichang"));
				return jsonresult;
			}
		} else {
			jsonresult.setSuccess(false);
			jsonresult.setMsg(SpringUtil.diff("before_login"));
			return jsonresult;
		}
	}

	/**
	 * 删除币地址
	 * @param request
	 * @return
	 */
	@CommonLog(name = "删除币地址")
	@ApiOperation(value = "删除币地址", httpMethod = "POST", response = ApiJsonResult.class, notes = "删除币地址")
	@PostMapping("/user/delCoinAddr")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
	@ResponseBody
	@RequiresAuthentication
	public ApiJsonResult delCoinAddr(
			@ApiParam(name = "addrId", value = "币地址id", required = true) @RequestParam("addrId") String addrId,
			@ApiParam(name = "accountPwd", value = "资金密码", required = true) @RequestParam("accountPwd") String accountPwd,
			HttpServletRequest request){
		User user = TokenUtil.getUser(request);
		ApiJsonResult jsonResult = new ApiJsonResult();
		if (user == null) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("before_login"));
			return jsonResult;
		}

		if (StringUtils.isEmpty(user.getAccountPassWord())) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("please_set_account_pwd"));
			return jsonResult;
		}
		//验证交易密码
		PasswordHelper passwordHelper = new PasswordHelper();
		String encryAccountPw = passwordHelper.encryString(accountPwd, user.getSalt());
		if(!encryAccountPw.equals(user.getAccountPassWord())) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("zijinmimabuzhengque"));
			return jsonResult;
		}

		try {
			RemoteResult remoteResult = remoteAppTransactionManageService.deletePublieKey(Long.valueOf(addrId),user.getCustomerId().toString());
			if(remoteResult.getSuccess()){
				jsonResult.setSuccess(true);
				return jsonResult;
			}else {
				jsonResult.setSuccess(false);
				jsonResult.setMsg(remoteResult.getMsg());
				return jsonResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			return jsonResult;
		}
	}
}
