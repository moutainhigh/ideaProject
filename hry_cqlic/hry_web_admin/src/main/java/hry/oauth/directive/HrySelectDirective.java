package hry.oauth.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import hry.admin.HryCache;
import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * 数据字典下拉框插件
 * <@HRSelect
 * @author CHINA_LSL
 *
 */
public class HrySelectDirective implements TemplateDirectiveModel {
	

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		
		StringBuffer templ ;
		
		Object url = params.get("url");
		if(url!=null){
			
			String itemName = params.get("itemname").toString();
			String itemValue = params.get("itemvalue").toString();
			String id = params.get("id").toString();
			
			//判断是否有回显值
			boolean render  = false;
			Object value = params.get("value");
			if(!StringUtils.isEmpty(value)){
				render = true;
			}
			
			//判断是否只读
			boolean isreadonly  = false;
			Object readonly = params.get("readonly");
			if(!StringUtils.isEmpty(readonly)){
				isreadonly = true;
			}
			
			if(isreadonly){//如果只读
				templ=  new StringBuffer("<select value=\""+value+"\"  hry=\"true\" url=\""+url+"\" disabled  itemname=\""+itemName+"\"  itemvalue=\""+itemValue+"\"  class=\"form-control\"  name=\""+params.get("name").toString()+"\"  id=\""+params.get("id").toString()+"\" >");
			}else{
				templ= new StringBuffer("<select  value=\""+value+"\"  hry=\"true\" url=\""+url+"\" itemname=\""+itemName+"\"  itemvalue=\""+itemValue+"\"  class=\"form-control\" name=\""+params.get("name").toString()+"\"  id=\""+params.get("id").toString()+"\" >");
			}
			
			templ.append("</select>");
			
		}else{
			List<AppDic> list = null;
			//获得缓存
			list = HryCache.cache_appdic.get(params.get("key").toString());
			if(list==null){
				AppDicService dicService =  SpringUtil.getBean("appDicService");
				QueryFilter filter = new QueryFilter(AppDic.class);
				filter.addFilter("pkey=", params.get("key").toString());
				list = dicService.find(filter);
				//更新缓存
				HryCache.cache_appdic.put(params.get("key").toString(),list);
			}



			//判断是否有回显值
			boolean render  = false;
			Object value = params.get("value");
			if(!StringUtils.isEmpty(value)){
				render = true;
			}
			
			//判断是否只读
			boolean isreadonly  = false;
			Object readonly = params.get("readonly");

			String tablesearchText="";
			Object tablesearch = params.get("tablesearch");
			if(!StringUtils.isEmpty(readonly)){
				isreadonly = true;
			}
			if(!StringUtils.isEmpty(tablesearch)){
				tablesearchText = "tablesearch='true'";
			}
			if(isreadonly){//如果只读
				templ=  new StringBuffer("<select class=\"form-control\" disabled name=\""+params.get("name").toString()+"\" "+tablesearchText+" id=\""+params.get("id").toString()+"\" >");
			}else{
				templ= new StringBuffer("<select class=\"form-control\" name=\""+params.get("name").toString()+"\" "+tablesearchText+" id=\""+params.get("id").toString()+"\" >");
			}

			//if(!render){
				templ.append("<option value=''>请选择</option>");
			//}
			
			for(AppDic dic : list){
				
				if(render){//如果有回显
					
					if(dic.getValue().equals(value.toString())){//回显选中
						templ.append("<option selected=\"selected\" value=\""+dic.getValue()+"\">"+dic.getName()+"</option>");
					}else{
						templ.append("<option value=\""+dic.getValue()+"\">"+dic.getName()+"</option>");
					}
					
				}else{//没有回显
					templ.append("<option value=\""+dic.getValue()+"\">"+dic.getName()+"</option>");
				}
				
			}
			templ.append("</select>");
		
		}
			
		Writer out = env.getOut();
		out.write(templ.toString());
	}

}
