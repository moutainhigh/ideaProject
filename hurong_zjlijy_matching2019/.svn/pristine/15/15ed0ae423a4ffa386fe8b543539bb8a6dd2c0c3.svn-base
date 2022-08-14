/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-04 09:48:29
 */
package hry.social.friend.service.impl;

import com.github.pagehelper.Page;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.social.friend.dao.SocialFriendPictureDao;
import hry.social.friend.service.SocialFriendPictureService;
import hry.social.manage.remote.model.friend.SocialFriendPicture;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p> SocialFriendPictureService </p>
 *
 * @author: javalx
 * @Date :          2019-06-04 09:48:29
 */
@Service("socialFriendPictureService")
public class SocialFriendPictureServiceImpl extends BaseServiceImpl<SocialFriendPicture,Long> implements SocialFriendPictureService {

    @Resource(name = "socialFriendPictureDao")
    @Override
    public void setDao(BaseDao<SocialFriendPicture,Long> dao) {
        super.dao = dao;
    }

    /**
     * 批量更新
     *
     * @param ids
     * @return
     */
    @Override
    public JsonResult updateBatch(String ids) {
        JsonResult jsonResult = new JsonResult();
        try {
            if (!StringUtils.isEmpty(ids)) {
                String[] split = ids.split(",");
                int var3 = split.length;
                for (int i = 0; i < var3; i++) {
                    String s = split[i];
                    Long id = Long.valueOf(s);
                    SocialFriendPicture socialFriendPicture = dao.selectByPrimaryKey(id);
                    socialFriendPicture.setStates(1);
                    dao.updateByPrimaryKeySelective(socialFriendPicture);
                }
            }
            return jsonResult.setSuccess(true);
        } catch (Exception var6) {
            var6.printStackTrace();
            return jsonResult.setSuccess(false);
        }
    }

    /**
     * 分页查询
     *
     * @param filter
     * @return
     */
    @Override
    public PageResult findPageList(QueryFilter filter) {
        //----------------------分页查询头部外壳-----------------------
        Page<SocialFriendPicture> page = PageFactory.getPage(filter);
        //----------------------分页查询头部外壳-----------------------
        //----------------------查询开始------------------------------
        ((SocialFriendPictureDao) dao).findPageList();
        return new PageResult(page, filter.getPage(), filter.getPageSize());
    }

    /**
     * 查询朋友圈图片集
     *
     * @param customerId 用户ID
     * @return
     */
    @Override
    public List<String> findCirclePictures(Long customerId) {
        return ((SocialFriendPictureDao) dao).findCirclePictures(customerId);
    }

}
