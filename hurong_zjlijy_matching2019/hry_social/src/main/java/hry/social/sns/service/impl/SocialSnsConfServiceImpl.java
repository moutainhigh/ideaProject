/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:19:16
 */
package hry.social.sns.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsConf;
import hry.social.sns.service.SocialSnsConfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsConfService </p>
 *
 * @author: lixin
 * @Date :          2019-05-13 10:19:16
 */
@Service("socialSnsConfService")
public class SocialSnsConfServiceImpl extends BaseServiceImpl<SocialSnsConf,Long> implements SocialSnsConfService {

    @Resource(name = "socialSnsConfDao")
    @Override
    public void setDao(BaseDao<SocialSnsConf,Long> dao) {
        super.dao = dao;
    }


}
