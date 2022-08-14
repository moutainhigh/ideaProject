/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-05 14:54:53 
 */
package hry.web.mail.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.bean.PageResult;
import hry.util.QueryFilter;
import hry.web.app.model.vo.MessageListVo;
import hry.web.mail.dao.MailTemplateDao;
import hry.web.mail.model.MailTemplate;
import hry.web.mail.service.MailTemplateService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> MailTemplateService </p>
 * @author:         sunyujie
 * @Date :          2018-06-05 14:54:53  
 */
@Service("mailTemplateService")
public class MailTemplateServiceImpl extends BaseServiceImpl<MailTemplate, Long> implements MailTemplateService{
	
	@Resource(name="mailTemplateDao")
	@Override
	public void setDao(BaseDao<MailTemplate, Long> dao) {
		super.dao = dao;
	}


	@Override
	public void updateTemplateStauts(MailTemplate mailTemplate) {
		MailTemplateDao mailTemplateDao=(MailTemplateDao)dao;
		mailTemplateDao.updateTemplateStauts(mailTemplate);
	}

	@Override
	public PageResult queryByPage(QueryFilter filter, MailTemplate mailTemplate) {
		PageResult pageResult = new PageResult();
		Page<MailTemplate> page = null;
		if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
			// pageSize = -1 时
			// pageHelper传pageSize参数传0查询全部
			page = PageHelper.startPage(filter.getPage(), 0);
		} else {
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		// 查询数据
		((MailTemplateDao)dao).queryByPage(mailTemplate);

		pageResult.setRows(page.getResult());
		for (MailTemplate mailTemplate1:page.getResult()) {
			mailTemplate1.setTempContent(StringEscapeUtils.unescapeHtml4( mailTemplate1.getTempContent()));
		}
		// 设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());

		return pageResult ;
	}
}
