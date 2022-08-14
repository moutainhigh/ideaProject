package hry.app.otc;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.manage.remote.model.AppBankCardManage;
import hry.util.AWSUtil;
import hry.util.AzureUtil;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;

import hry.bean.JsonResult;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.util.properties.PropertiesUtils;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.otc.remote.RemoteAdvertisementService;
import hry.redis.common.utils.RedisService;
import hry.util.OssUtil;
import hry.util.common.SpringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/otc/user/personalAsset")
@RequiresAuthentication
@Api(value = "App操作类", description = "OTC - 银行卡相关信息", tags = "OTC - 银行卡相关信息")
public class PersonalAssetMobileController {

	@Resource
	private RedisService redisService;

	@Resource
	private RemoteManageService remoteManageService;

	@Resource
	private RemoteAdvertisementService remoteAdvertisementService;

	@RequestMapping(value="/personalAsset", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "跳转银行卡页面", httpMethod = "POST", response = JsonResult.class, notes = "")
	public ApiJsonResult index(HttpServletRequest request){
		User user = TokenUtil.getUser(request);
		if(user != null){
			Map<String, Object> map = new HashMap<String, Object>();
			JSONArray parseArray = JSON.parseArray(redisService.get("DIC:"+"bank"));
			for(int k=0;k<parseArray.size();k++){
				JSONObject jo = JSON.parseObject(parseArray.get(k).toString());
				map.put(jo.getString("remark1"), jo.getString("itemName"));
			}
			map.put("dicMap", map);
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/personalAssetlist", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "银行卡页面 - 个人消息", httpMethod = "POST", response = JsonResult.class, notes = "page: 页码, type: 类型")
	public ApiJsonResult personalAssetlist(HttpServletRequest request,
										   @ApiParam(name = "page", value = "当前页数", required = true) @RequestParam String page,
										   @ApiParam(name = "type", value = "类型(1银行转账,2支付宝,3微信支付)", required = true) @RequestParam String type) {
		User user = TokenUtil.getUser(request);
		String offset = Integer.toString((Integer.valueOf(request.getParameter("page"))-1)*10);
		String limit = "10";
		if(user != null){
			// 分页查询信息
			Map<String, String> params = HttpServletRequestUtils.getParams(request);
			params.put("offset", offset);
			params.put("limit", limit);
//				Map<String,String> params = new HashMap<String,String>();
			params.put("customerId", user.getCustomerId().toString());
			params.put("type", type);
			params.put("isDelete", 0+"");//未删除的
			FrontPage obj = remoteManageService.getPersonalAssetById(params);
            List<AppBankCardManage> rows = obj.getRows();
            rows.forEach(l -> {
                l.setThingUrl(OssUtil.getUrl(l.getThingUrl()));
            });
            return new ApiJsonResult().setSuccess(true).setObj(obj);
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/add", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "银行卡页面 - 新增", httpMethod = "POST", response = JsonResult.class, notes = "bankName：传空即可, account：银行卡号,surname：姓, truename：名, "
			+ "subBranch：开户支行, type：类型（1-银行卡  2-支付宝  3-微信）, thingUrl：二维码, bankAddress：开户行所在地, bankProvince：省, cardBank：开户银行")
	public ApiJsonResult add(HttpServletRequest request,
							 @ApiParam(name = "bankName", value = "传空即可", required = true) @RequestParam(required = false) String bankName,
							 @ApiParam(name = "account", value = "银行卡号", required = true) @RequestParam(required = false) String account,
							 @ApiParam(name = "surname", value = "姓", required = true) @RequestParam(required = false) String surname,
							 @ApiParam(name = "truename", value = "名", required = true) @RequestParam(required = false) String truename,
							 @ApiParam(name = "subBranch", value = "开户支行", required = true) @RequestParam(required = false) String subBranch,
							 @ApiParam(name = "type", value = "类型(1银行转账,2支付宝,3微信支付)", required = true) @RequestParam(required = false) String type,
							 @ApiParam(name = "thingUrl", value = "二维码", required = true) @RequestParam(required = false) String thingUrl,
							 @ApiParam(name = "bankAddress", value = "开户行所在地", required = true) @RequestParam(required = false) String bankAddress,
							 @ApiParam(name = "bankProvince", value = "省", required = true) @RequestParam(required = false) String bankProvince,
							 @ApiParam(name = "cardBank", value = "开户银行", required = true) @RequestParam(required = false) String cardBank){
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(StringUtil.isNotEmpty(thingUrl)){
				if(thingUrl.endsWith(",")){
					thingUrl = thingUrl.substring(0, thingUrl.length()-1);
				}
			}
			if(type != null && "1".equals(type) && (!(surname+truename).equals(user.getSurname() + user.getTruename()))){
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("yinhangkachiyouzheyuxingmingbufu"));
			}
			String customerId = user.getCustomerId().toString();
			remoteAdvertisementService.addPersonalAsset(type,bankName,account,surname,truename,subBranch,thingUrl,customerId,bankAddress,bankProvince,cardBank);
			return new ApiJsonResult().setSuccess(true).setMsg(SpringUtil.diff("tianjiachenggong"));
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/deletePersonalAsset", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "银行卡页面 - 删除", httpMethod = "POST", response = JsonResult.class, notes = "id：银行卡Id")
	public ApiJsonResult deletePersonalAsset(HttpServletRequest request, @ApiParam(name = "id", value = "银行卡Id", required = true) @RequestParam String id){
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(id == null || "".equals(id)){
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("idweikong"));
			}
			JsonResult jsonResult = remoteAdvertisementService.deletePersonalAsset(Long.valueOf(id), user.getCustomerId());
			return new ApiJsonResult().setSuccess(jsonResult.getSuccess()).setMsg(SpringUtil.diff(jsonResult.getMsg()));
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/addpicture", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "图片上传", httpMethod = "POST", response = JsonResult.class, notes = "")
	public ApiJsonResult addpicture(@ApiParam(name = "file", value = "file文件域", required = true) @RequestParam(value = "file", required = false) MultipartFile file,
								 HttpServletRequest request) {
		if (file == null || file.isEmpty()) {
			return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingshangchuantupian"));
		}
		String path = request.getSession().getServletContext().getRealPath("/hryfile/personalAsset/");
		if (!path.isEmpty()) {
			path = path.replace("\\front", "").replace("\\ROOT", "").replace("\\root", "");
			path = path.replace("/front", "").replace("/ROOT", "").replace("/root", "");
		}

		String fileName = file.getOriginalFilename();

		String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
				: null;
		String turefileName = System.currentTimeMillis() + "." + type;

		System.out.println(path);
		File targetFile = new File(path, turefileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			if("true".equals(PropertiesUtils.APP.getProperty("app.oss.open"))){
				OssUtil.upload(file.getInputStream(),"hryfile/personalAsset/" + turefileName,false);
			}else if("false".equals(PropertiesUtils.APP.getProperty("app.oss.open"))){
				file.transferTo(targetFile);// 保存到一个目标文件中。
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiJsonResult().setCode("/hryfile/personalAsset/" + turefileName).setSuccess(true);
	}

	@RequestMapping(value="/setDefault", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "设置成默认", httpMethod = "POST", response = JsonResult.class, notes = "id：银行卡Id, type：类型（1-银行卡  2-支付宝  3-微信）")
	public ApiJsonResult setDefault(HttpServletRequest request, @RequestParam String id, @RequestParam String type){
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(id == null || "".equals(id)){
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("idweikong"));
			}
			JsonResult jsonResult = remoteAdvertisementService.setDefault(Long.valueOf(id), type, user.getCustomerId());
			if(jsonResult.getSuccess()){
				return new ApiJsonResult().setSuccess(true).setMsg(SpringUtil.diff("shezhichenggong"));
			}else{
				return new ApiJsonResult().setSuccess(true).setMsg(SpringUtil.diff("shezhiyichang"));
			}
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}
}
class Bank implements Serializable{
	private String value;

	private String name;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
