package hry.web.remote;

import hry.manage.remote.RemoteMailConfigService;
import hry.manage.remote.model.MailConfigAndTemplate;
import hry.web.mail.dao.MailConfigDao;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 邮箱模板接口
 * @author syj
 *
 * 2017年7月19日
 */
public class RemoteMailConfigServiceImpl implements RemoteMailConfigService {


	@Resource
	private MailConfigDao mailConfigDao;
	
	


	@Override
	public List<MailConfigAndTemplate> findMailConfigAndTemplateList(Map<String, String> params) {
		List<MailConfigAndTemplate> list=mailConfigDao.findMailConfigAndTemplateList(params);
		return list;
	}



	
}
