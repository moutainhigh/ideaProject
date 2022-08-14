package hry.social.manage.remote.shake;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.manage.remote.model.User;
import hry.redis.common.utils.RedisService;
import hry.social.friend.dao.SocialFriendRelationDao;
import hry.social.manage.remote.api.shake.RemoteShakeService;
import hry.social.manage.remote.model.base.RemoteResult;
import hry.social.manage.remote.model.shake.*;
import hry.social.mq.producer.service.MessageProducer;
import hry.social.shake.dao.*;
import hry.util.PageFactory;
import hry.util.ShakeSiteUtils;
import org.nutz.lang.Lang;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.*;

public class RemoteShakeServiceImpl implements RemoteShakeService {
    @Resource
    private RedisService redisService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private SocialShakeSiteDao socialShakeSiteDao;
    @Resource
    private SocialShopOfflineDao socialShopOfflineDao;
    @Resource
    private SocialShakeFriendDao socialShakeFriendDao;
    @Resource
    private SocialShakeFansDao socialShakeFansDao;
    @Resource
    private SocialShakeProductDao socialShakeProductDao;
    @Resource
    private SocialShakeShopDao socialShakeShopDao;

    @Resource
    private SocialFriendRelationDao socialFriendRelationDao;

    public static String SHAKE_KEY = "SHAKE:SITE:";

    /**
     * 抖人
     *
     * @param customerId
     * @param nickName
     * @param headPortrait
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public RemoteResult shakePerson(Long customerId, String nickName, String headPortrait, BigDecimal longitude, BigDecimal latitude) {
        RemoteResult remoteResult = new RemoteResult();
        Map<String,Object> map = new HashMap();
        try {
            // 随机获取
            SocialShakeSite socialShakeSite = shakeRandom(customerId);
            // 存储位置
            shakeCache(customerId, nickName, headPortrait, longitude, latitude);
            // 存储记录
            SocialShakeFriend ssf = new SocialShakeFriend();
            ssf.setShakeId(customerId);
            int status = 0;
            if (null != socialShakeSite) {
                status = 1;

                double lng1 = longitude.doubleValue(); // lng1 -- 点1经度
                double lat1 = latitude.doubleValue(); // lat1 -- 点1纬度
                double lng2 = socialShakeSite.getLongitude().doubleValue(); // lng2 -- 点2经度
                double lat2 = socialShakeSite.getLatitude().doubleValue(); // lat2 -- 点2纬度

                Double distance = ShakeSiteUtils.getLatLngDistance(lng1, lat1, lng2, lat2);
                BigDecimal dm = new BigDecimal(distance).setScale(0, BigDecimal.ROUND_HALF_UP);
                BigDecimal dkm = new BigDecimal(distance).divide(new BigDecimal(1000), 1, BigDecimal.ROUND_HALF_UP);
                String dmStr = dm.toPlainString();
                String dkmStr = dkm.toPlainString();
                socialShakeSite.setDm(dmStr);
                socialShakeSite.setDkm(dkmStr);
                map.put("shakeSite", socialShakeSite);

                ssf.setCustomerId(socialShakeSite.getShakeId());// 存储抖人记录
            }
            ssf.setStates(status);

            try {
                socialShakeFriendDao.insertSelective(ssf);
            } catch (DuplicateKeyException e) {
                System.err.println("ERR----------不能重复添加抖人记录信息----------ERR : " + ssf);
                ssf.setModified(new Date());
                socialShakeFriendDao.updateRecord(ssf);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("ERR----------添加抖人记录信息----------ERR");
            }

            map.put("status", status);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
            remoteResult.setObj(map);
        }
        remoteResult.setSuccess(true);
        remoteResult.setObj(map);
        return remoteResult;
    }

    /**
     * 抖粉
     *
     * @param user
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public RemoteResult shakeFans(User user, BigDecimal longitude, BigDecimal latitude) {
        RemoteResult remoteResult = new RemoteResult();
        SocialShakeSite socialShakeSite = null;
        Map<String,Object> map = new HashMap();
        try {
            // 存储位置
            String nickName = user.getNickName();
            Long customerId = user.getCustomerId();
            String headPortrait = user.getHeadPortrait();
            shakeCache(customerId, nickName, headPortrait, longitude, latitude);
            // 存储记录
            SocialShakeFans ssf = new SocialShakeFans();
            ssf.setShakeId(customerId);
            // 随机获取粉丝
            String shopCommendCode = user.getCommendCode();
            String shopInvitationCode = user.getCommendCode();
            if (!StringUtils.isEmpty(shopInvitationCode)) {
                System.out.println("抖粉 ====>> 掌柜的邀请码 : " + shopInvitationCode);
                socialShakeSite = shakeFansRandom(customerId, shopInvitationCode);
            } else if (!StringUtils.isEmpty(shopCommendCode)) {
                System.out.println("抖粉 ====>> 粉丝存储的掌柜的邀请码 : " + shopCommendCode);
                socialShakeSite = shakeFansRandom(customerId, shopCommendCode);
            } else {
                System.out.println("抖粉 ====>> 不是粉丝走抖人逻辑 : " + shopCommendCode);
                socialShakeSite = shakeRandom(customerId);
            }
            System.out.println("抖粉 ====>> 粉丝抖到的用户 : " + socialShakeSite);
            int status = 0;
            if (null != socialShakeSite) {
                status = 1;

                double lng1 = longitude.doubleValue(); // lng1 -- 点1经度
                double lat1 = latitude.doubleValue(); // lat1 -- 点1纬度
                double lng2 = socialShakeSite.getLongitude().doubleValue(); // lng2 -- 点2经度
                double lat2 = socialShakeSite.getLatitude().doubleValue(); // lat2 -- 点2纬度

                Double distance = ShakeSiteUtils.getLatLngDistance(lng1, lat1, lng2, lat2);
                BigDecimal dm = new BigDecimal(distance).setScale(0, BigDecimal.ROUND_HALF_UP);
                BigDecimal dkm = new BigDecimal(distance).divide(new BigDecimal(1000), 1, BigDecimal.ROUND_HALF_UP);
                String dmStr = dm.toPlainString();
                String dkmStr = dkm.toPlainString();
                socialShakeSite.setDm(dmStr);
                socialShakeSite.setDkm(dkmStr);
                map.put("shakeSite", socialShakeSite);
                ssf.setFansId(socialShakeSite.getShakeId());// 存储抖粉记录
            }
            ssf.setStates(status);

            try {
                socialShakeFansDao.insertSelective(ssf);
            } catch (DuplicateKeyException e) {
                System.err.println("ERR----------不能重复添加抖粉记录信息----------ERR : " + ssf);
                ssf.setModified(new Date());
                socialShakeFansDao.updateRecord(ssf);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("ERR----------添加抖粉记录信息----------ERR");
            }

            map.put("status", status);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
            remoteResult.setObj(map);
        }
        remoteResult.setSuccess(true);
        remoteResult.setObj(map);
        return remoteResult;
    }

    /**
     * 抖购
     *
     * @param customerId
     * @param nickName
     * @param headPortrait
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public RemoteResult shakeProduct(Long customerId, String nickName, String headPortrait, BigDecimal longitude, BigDecimal latitude, SocialShakeProduct socialShakeProduct) {
        RemoteResult remoteResult = new RemoteResult();
        Map<String,Object> map = new HashMap();
        try {
            // 存储位置
            shakeCache(customerId, nickName, headPortrait, longitude, latitude);
            // 存储记录
            try {
                socialShakeProductDao.insertSelective(socialShakeProduct);
            } catch (DuplicateKeyException e) {
                System.err.println("ERR----------不能重复添加抖购记录信息----------ERR : " + socialShakeProduct);
                socialShakeProduct.setModified(new Date());
                socialShakeProductDao.updateRecord(socialShakeProduct);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("ERR----------添加抖购记录信息----------ERR");
            }
            remoteResult.setSuccess(true);
            remoteResult.setObj(map);
            return remoteResult;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        remoteResult.setSuccess(true);
        remoteResult.setObj(map);
        return remoteResult;
    }

    /**
     * 抖店
     *
     * @param customerId
     * @param nickName
     * @param headPortrait
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public RemoteResult shakeShop(Long customerId, String nickName, String headPortrait, BigDecimal longitude, BigDecimal latitude) {
        RemoteResult remoteResult = new RemoteResult();
        Map<String,Object> map = new HashMap();
        try {
            // 存储位置
            shakeCache(customerId, nickName, headPortrait, longitude, latitude);
            // 存储记录
            SocialShakeShop sss = new SocialShakeShop();
            sss.setShakeId(customerId);

            SocialShopOffline ssol = socialShopOfflineDao.randomShop(longitude, latitude);
            int status = 0;
            if (null != ssol) {
                status = 1;

                double lng1 = longitude.doubleValue(); // lng1 -- 点1经度
                double lat1 = latitude.doubleValue(); // lat1 -- 点1纬度
                double lng2 = ssol.getLongitude().doubleValue(); // lng2 -- 点2经度
                double lat2 = ssol.getLatitude().doubleValue(); // lat2 -- 点2纬度

                Double distance = ShakeSiteUtils.getLatLngDistance(lng1, lat1, lng2, lat2);
                BigDecimal dm = new BigDecimal(distance).setScale(0, BigDecimal.ROUND_HALF_UP);
                BigDecimal dkm = new BigDecimal(distance).divide(new BigDecimal(1000), 1, BigDecimal.ROUND_HALF_UP);
                String dmStr = dm.toPlainString();
                String dkmStr = dkm.toPlainString();
                ssol.setDm(dmStr);
                ssol.setDkm(dkmStr);
                List<String> pics = socialShopOfflineDao.shopPic(ssol.getId());
                if (null != pics && pics.size() > 0) {
                    ssol.setPics(pics);
                }
                sss.setShopId(ssol.getId());
                sss.setShopName(ssol.getShopName());
                sss.setShopLogo(ssol.getShopLogo());
                sss.setShopLink(ssol.getShopLink());

                map.put("shopOffline", ssol);
            }
            sss.setStates(status);

            try {
                socialShakeShopDao.insertSelective(sss);
            } catch (DuplicateKeyException e) {
                System.err.println("ERR----------不能重复添加抖店记录信息----------ERR : " + sss);
                sss.setModified(new Date());
                socialShakeShopDao.updateRecord(sss);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("ERR----------添加抖店记录信息----------ERR");
            }

            map.put("status", status);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
            remoteResult.setObj(map);
        }
        remoteResult.setSuccess(true);
        remoteResult.setObj(map);
        return remoteResult;
    }

    /**
     * 抖人记录
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage shakePersonPage(Map<String,String> params) {
        Page page = PageFactory.getPage(params);
        // 查询方法
        List<SocialShakeFriend> list = socialShakeFriendDao.findPageList(params);
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 抖粉记录
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage shakeFansPage(Map<String,String> params) {
        Page page = PageFactory.getPage(params);
        // 查询方法
        List<SocialShakeFans> list = socialShakeFansDao.findPageList(params);
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 抖购记录
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage shakeProductPage(Map<String,String> params) {
        Page page = PageFactory.getPage(params);
        // 查询方法
        List<SocialShakeProduct> list = socialShakeProductDao.findPageList(params);
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 抖店记录
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage shakeShopPage(Map<String,String> params) {
        Page page = PageFactory.getPage(params);
        // 查询方法
        List<SocialShakeShop> list = socialShakeShopDao.findPageList(params);
        for (SocialShakeShop sss : list) {
            List<String> pics = socialShopOfflineDao.shopPic(sss.getShopId());
            if (null != pics && pics.size() > 0) {
                sss.setPics(pics);
            }
        }
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 查询店铺详情
     *
     * @param shopId
     * @return
     */
    @Override
    public SocialShakeShop getShopInfo(Long shopId) {
        return socialShakeShopDao.getShopInfo(shopId);
    }

    /**
     * 热销推荐商品
     *
     * @return
     */
    @Override
    public List<SocialShopProduct> recommendShop() {
        return socialShopOfflineDao.recommendShop();
    }

    /**
     * 热销推荐商品最近一条商品ID
     *
     * @param customerId
     * @return
     */
    @Override
    public Long lastProduct(Long customerId) {
        Long lastId = socialShakeProductDao.lastProduct(customerId);
        if (lastId == null) {
            lastId = 0L;
        }
        return lastId;
    }

    /**
     * 查询用户是否有线下店铺
     *
     * @param inviteUserId
     * @return
     */
    @Override
    public int hasOffShop(Long inviteUserId) {
        return socialShopOfflineDao.hasOffShop(inviteUserId);
    }

    /**
     * 随机获取抖一抖用户信息，
     * 先从缓存取，缓存没有去数据库取
     *
     * @return
     */
    private SocialShakeSite shakeRandom(Long customerId) {
        Set<String> keys = redisService.keys(SHAKE_KEY);
        List<SocialShakeSite> sssList = new ArrayList<>();
        if (null != keys && keys.size() > 0) {
            List<String> keyList = new ArrayList<>(keys);
            //查询自己和好友ID集合
            List<String> palIds = socialFriendRelationDao.getFriendIds(customerId);
            //key值时间排序
            for (String key : keyList) {
                String shakeId = key.replace(SHAKE_KEY, "");
                // 缓存中去掉自己和好友
                boolean contains = palIds.contains(shakeId);
                if (contains) {
                    continue;
                }
                String jsonStr = redisService.get(key);
                if (!StringUtils.isEmpty(jsonStr)) {
                    try {
                        SocialShakeSite socialShakeSite = JSON.parseObject(jsonStr, SocialShakeSite.class);
                        sssList.add(socialShakeSite);
                    } catch (Exception e) {
                        System.out.println("SocialShakeSite 反序列化失败");
                    }
                }
            }
        }
        // 随机匹配一个抖一抖用户
        SocialShakeSite randomShake = null;
        if (sssList.size() < 1) {
            // 缓存中没有抖一抖缓存，去数据库随机取一条数据
            randomShake = socialShakeSiteDao.randomShake(customerId);
        } else {
            int size = sssList.size();
            Random random = new Random();
            int randIndex = random.nextInt(size);
            randomShake = sssList.get(randIndex);
        }
        return randomShake;
    }

    /**
     * 抖一抖位置缓存,发送QM消息存储,
     * 先存入redis中,然后存储到数据库中
     *
     * @param customerId
     * @param nickName
     * @param headPortrait
     * @param longitude
     * @param latitude
     * @return
     */
    private SocialShakeSite shakeCache(Long customerId, String nickName, String headPortrait, BigDecimal longitude, BigDecimal latitude) {
        SocialShakeSite shakeSite = null;
        try {
            shakeSite = new SocialShakeSite();
            shakeSite.setShakeId(customerId);
            shakeSite.setLongitude(longitude);
            shakeSite.setLatitude(latitude);
            shakeSite.setNickName(nickName);
            shakeSite.setHeadPortrait(headPortrait);

            String scvalue = JSON.toJSONString(shakeSite);
            messageProducer.toShakeSite(scvalue);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SocialShakeSite 存储失败");
        }
        return shakeSite;
    }

    /**
     * 随机获取粉丝用户信息，
     * 去数据库取
     *
     * @return
     */
    private SocialShakeSite shakeFansRandom(Long shakeId, String shopCode) {
        SocialShakeSite randomShake = null;
        // 随机查询所有粉丝中的一位的抖一抖位置信息
        randomShake = socialShakeSiteDao.randomShakeFans(shakeId, shopCode);
        return randomShake;
    }

}
