package hry.manage.remote;


import hry.manage.remote.model.MailConfigAndTemplate;


import java.util.List;
import java.util.Map;

/**
 * 邮箱模板接口
 * @author syj
 *
 * 2018年6月12日
 */
public interface RemoteMailConfigService {


	
	/**
	 * 获取所有工单类别
	 * @param params
	 * @return
	 * 2018年5月4日
	 * syj
	 */
	public List<MailConfigAndTemplate> findMailConfigAndTemplateList(Map<String, String> params);

}
