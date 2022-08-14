/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-04 09:42:35
 */
package hry.social.friend.service.impl;

import com.github.pagehelper.Page;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.social.friend.dao.SocialFriendCircleDao;
import hry.social.friend.dao.SocialFriendPictureDao;
import hry.social.friend.dao.SocialFriendRewardDao;
import hry.social.friend.service.SocialFriendCircleService;
import hry.social.manage.remote.model.friend.SocialFriendCircle;
import hry.social.manage.remote.model.friend.SocialFriendPicture;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.oss.GetYunUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> SocialFriendCircleService </p>
 *
 * @author: javalx
 * @Date :          2019-06-04 09:42:35
 */
@Service("socialFriendCircleService")
public class SocialFriendCircleServiceImpl extends BaseServiceImpl<SocialFriendCircle,Long> implements SocialFriendCircleService {

    @Resource(name = "socialFriendCircleDao")
    @Override
    public void setDao(BaseDao<SocialFriendCircle,Long> dao) {
        super.dao = dao;
    }

    @Resource
    private SocialFriendPictureDao socialFriendPictureDao;

    @Resource
    private SocialFriendRewardDao socialFriendRewardDao;

    /**
     * 分页查询
     *
     * @param filter
     * @return
     */
    @Override
    public PageResult findPageList(QueryFilter filter) {
        //----------------------分页查询头部外壳------------------------------
        Page<SocialFriendCircle> page = PageFactory.getPage(filter);
        //----------------------分页查询头部外壳------------------------------
        //----------------------查询开始------------------------------
        String content = filter.getRequest().getParameter("content_like");
        String customerId = filter.getRequest().getParameter("customerId");
        String nickName = filter.getRequest().getParameter("nickName_like");
        String mobilePhone = filter.getRequest().getParameter("mobilePhone_like");
        String issuedTimeGT = filter.getRequest().getParameter("issuedTime_GT");
        String issuedTimeLT = filter.getRequest().getParameter("issuedTime_LT");

       /* if(!StringUtils.isEmpty(issuedTimeGT)&&!StringUtils.isEmpty(issuedTimeLT)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date parse = sdf.parse(issuedTimeGT);
                Date parse1 = sdf.parse(issuedTimeLT);
                if(parse.getTime()>parse1.getTime()){
                    return new PageResult().set
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }*/

        Map<String,String> map = new HashMap();
        if (!StringUtils.isEmpty(content)) {
//            map.put("content", content);
            map.put("content", "%" + content + "%");
        }
        if (!StringUtils.isEmpty(nickName)) {
            map.put("nickName", "%" + nickName + "%");
        }
        if (!StringUtils.isEmpty(mobilePhone)) {
            map.put("mobilePhone", "%" + mobilePhone + "%");
        }
        if (!StringUtils.isEmpty(issuedTimeGT)) {
            map.put("issuedTimeGT", issuedTimeGT);
        }
        if (!StringUtils.isEmpty(customerId)) {
            map.put("customerId", customerId);
        }
        if (!StringUtils.isEmpty(issuedTimeLT)) {
            map.put("issuedTimeLT", issuedTimeLT);
        }
        ((SocialFriendCircleDao) dao).findPageList(map);
        return new PageResult(page, filter.getPage(), filter.getPageSize());
    }

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    @Override
    public SocialFriendCircle see(Long id) {
        SocialFriendCircle sfc = ((SocialFriendCircleDao) dao).see(id);

//        String likeCustomerIds = sfc.getLikeCustomerIds();
//        if (!StringUtils.isEmpty(likeCustomerIds)) {
//            String[] split = likeCustomerIds.split(",");
//            int length = split.length;
//            sfc.setLikeNum(String.valueOf(length));
//        } else {
//            sfc.setLikeNum("0");
//        }
//        String picture = sfc.getPicture();
//        if (!StringUtils.isEmpty(picture)) {
//            String[] pictures = picture.split("\\|");
//            List<String> imageUrls = new ArrayList<>();
//            for (int i = 0; i < pictures.length; i++) {
//                String imageUrl = GetYunUtil.getUrl(pictures[i], false);
//                imageUrls.add(imageUrl);
//            }
//            sfc.setImageUrls(imageUrls);
//        }
        Long sfcId = sfc.getId();
        Integer integer = socialFriendRewardDao.sumRewards(sfcId);
        sfc.setLikeNum(String.valueOf(integer));
        List<SocialFriendPicture> pictures = socialFriendPictureDao.findPictures(sfcId);
        if (pictures != null && pictures.size() > 0) {
            for (SocialFriendPicture picture : pictures) {
                picture.setPath(GetYunUtil.getUrl(picture.getPath(), false));
            }
        }
        sfc.setPictures(pictures);
        return sfc;
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
                    SocialFriendCircle socialFriendCircle = dao.selectByPrimaryKey(Long.valueOf(s));
                    socialFriendCircle.setIssued(2);
                    dao.updateByPrimaryKeySelective(socialFriendCircle);
                }
            }
            return jsonResult.setSuccess(true);
        } catch (Exception var6) {
            var6.printStackTrace();
            return jsonResult.setSuccess(false);
        }
    }

}
