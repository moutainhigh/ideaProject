package hry.core.mvc.model.page;

import java.io.Serializable;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public class PageFactory<T extends Serializable> {
	
	public static Page getPage(Map<String,String> map){
		Page page = null;
		Integer offset =  Integer.valueOf(map.get("offset"));
		Integer limit =  Integer.valueOf(map.get("limit"));
		
		if(limit==-1){
			page= PageHelper.startPage(offset/limit+1, 0);
		}else{
			page = PageHelper.startPage(offset/limit+1, limit);
		}
		return page;
	}
	
}
