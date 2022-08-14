/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 19:54:58
 */
package hry.social.manage.remote.barrage;

import hry.social.barrage.dao.SocialBarrageDao;
import hry.social.manage.remote.api.barrage.RemoteBarrageService;
import hry.social.manage.remote.model.barrage.SocialBarrage;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> RemoteBarrageServiceImpl </p>
 * @author: javalx
 * @Date :          2019-06-03 19:54:58 
 */
public class RemoteBarrageServiceImpl implements RemoteBarrageService {

    @Resource
    SocialBarrageDao socialBarrageDao;
    /*
     * 获取弹幕列表
     * */
    @Override
    public List<SocialBarrage> barrageList() {
        List<SocialBarrage> barrages = socialBarrageDao.selectAll();
        return barrages;
    }
}
