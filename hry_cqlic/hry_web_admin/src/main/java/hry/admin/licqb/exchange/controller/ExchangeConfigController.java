/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:32:12 
 */
package hry.admin.licqb.exchange.controller;


import com.alibaba.fastjson.JSONArray;
import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.exchange.model.ExProduct;
import hry.admin.exchange.service.ExProductService;
import hry.admin.licqb.exchange.model.ExchangeImage;
import hry.admin.licqb.exchange.service.ExchangeConfigService;
import hry.admin.licqb.exchange.service.ExchangeImageService;
import hry.admin.licqb.platform.RulesConfig;
import hry.admin.test.model.Student;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.exchange.model.ExchangeConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.RedisStaticUtil;
import hry.util.StringUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.secure.spi.JaccIntegrator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright:   互融云
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:32:12 
 */
@Controller
@RequestMapping("/licqb/exchange/exchangeconfig")
public class ExchangeConfigController extends BaseController<ExchangeConfig, Long> {

	@Resource
	private ExProductService exProductService;
	@Resource
	private AppDicService appDicService;
	@Resource
	private ExchangeImageService exchangeImageService;
	@Resource
	private ExchangeConfigService exchangeConfigService;

	@Resource(name = "exchangeConfigService")
	@Override
	public void setService(BaseService<ExchangeConfig, Long> service) {
		super.service = service;
	}

	@RequestMapping(value="/addConfigFtl")
	public ModelAndView addConfigFtl(){
		ModelAndView mav = new ModelAndView("/admin/licqb/exchange/exchangeconfigadd");
		// 获取 平台币
		String platCoin = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
		// 获取上架币种资料
		List<ExProduct> list = exProductService.find(new QueryFilter(ExProduct.class).addFilter("issueState=","1"));
		mav.addObject("platCoin", platCoin);
		mav.addObject("exProductList", list);
		return mav;
	}

	@RequestMapping(value="/uploadingImage/{id}")
	public ModelAndView uploadingImage(@PathVariable Long id){
		ModelAndView mav = new ModelAndView("/admin/licqb/exchange/exchangeconfigimage");
		// 多种语种
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "sysLanguage");
		List<AppDic> keyList = appDicService.find(filter);

		// 查询保存的配置图片
		QueryFilter filter1 = new QueryFilter(ExchangeImage.class);
		filter1.addFilter("configId=", id);
		List<ExchangeImage> imageList = exchangeImageService.find(filter1);

		Map<String, ExchangeImage> imageMap = null;
		if (imageList != null && imageList.size() > 0) {
			imageMap = imageList.stream().collect(Collectors.toMap(ExchangeImage :: getLanguagetype, (k) -> k));
		}
		String langKey = "";
		for (AppDic key : keyList) {
			if (!StringUtil.isNull(langKey)) {
				langKey = key.getValue();
			} else {
				langKey = langKey + "," + key.getValue();
			}
			// 校验是否添加
			Boolean flag = true;
			if (imageMap != null) {
				ExchangeImage image = imageMap.get(key.getValue());
				if (image != null) {
					image.setLangName(key.getName());
					flag = false;
				}
			}
			if (flag) {
				ExchangeImage image = new ExchangeImage();
				image.setConfigId(id);
				image.setLangName(key.getName());
				image.setLanguagetype(key.getValue());
				exchangeImageService.save(image);
				imageList.add(image);
			}
		}
		if (imageMap != null) {
			imageList = new ArrayList<>(imageMap.values());
		}
		mav.addObject("imageList", imageList);
		mav.addObject("langKey", langKey);
		return mav;
	}


	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExchangeConfig exchangeConfig){
		// 获取 平台币
		/*String platCoin = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
		exchangeConfig.setPayCoinCode(platCoin);*/
		// 校验项目兑换币种是否重复
		QueryFilter filter = new QueryFilter(ExchangeConfig.class);
		filter.addFilter("gainCoinCode=", exchangeConfig.getGainCoinCode());
		ExchangeConfig config = exchangeConfigService.get(filter);
		return super.save(exchangeConfig);
		/*if (config == null) {
			return super.save(exchangeConfig);
		} else {
			return new JsonResult(false).setMsg("已存在兑换此币的项目");
		}*/
	}

	@RequestMapping(value="/updateImage")
	@ResponseBody
	public JsonResult updateImage(HttpServletRequest request,
								  @RequestParam(required = true, value = "imageList", defaultValue = "" ) String imageList){
		imageList = StringEscapeUtils.unescapeHtml4(imageList);
		List<ExchangeImage> images = JSONArray.parseArray(imageList, ExchangeImage.class);
		images.forEach(image ->{
			exchangeImageService.update(image);
		});
		return new JsonResult(true);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){

		ModelAndView mav = new ModelAndView("/admin/licqb/exchange/exchangeconfigmodify");
		// 获取 平台币
		String platCoin = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
		// 获取上架币种资料
		List<ExProduct> list = exProductService.find(new QueryFilter(ExProduct.class).addFilter("issueState=","1"));
		// mav.addObject("platCoin", platCoin);
		mav.addObject("exProductList", list);
		ExchangeConfig exchangeConfig = service.get(id);
		mav.addObject("model", exchangeConfig);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExchangeConfig exchangeConfig){
		return super.update(exchangeConfig);
	}

	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExchangeConfig.class,request);
		filter.setOrderby("status DESC,sort DESC");
		return super.findPage(filter);
	}

	@RequestMapping(value="/handleConfig/{id}")
	@ResponseBody
	public JsonResult handleConfig(@PathVariable Long id,
								   @RequestParam(required = true, value = "type", defaultValue = "" ) String type){
		return exchangeConfigService.handleConfig(id, type);
	}
	
	
	
	
}
