package hry.social.manage.remote.sns;


import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.redis.common.utils.RedisService;
import hry.social.manage.remote.api.sns.RemoteSnsService;
import hry.social.manage.remote.model.base.RemoteResult;
import hry.social.manage.remote.model.sns.*;
import hry.social.mq.producer.service.MessageProducer;
import hry.social.sns.dao.*;
import hry.util.APPDateUtil;
import hry.util.FileUploadUtils;
import hry.util.PageFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteSnsServiceImpl implements RemoteSnsService {

    @Resource
    private RedisService redisService;

    @Resource
    private MessageProducer messageProducer;

    @Resource
    private SocialSnsPictureDao socialSnsPictureDao;

    @Resource
    private SocialSnsVideoDao socialSnsVideoDao;

    @Resource
    private SocialSnsPostsDao socialSnsPostsDao;

    @Resource
    private SocialSnsPraiseDao socialSnsPraiseDao;

    @Resource
    private SocialSnsRewardDao socialSnsRewardDao;

    @Resource
    private SocialSnsCommentDao socialSnsCommentDao;

    @Resource
    private SocialSnsFavoriteDao socialSnsFavoriteDao;

    @Resource
    private SocialSnsShareDao socialSnsShareDao;

    @Resource
    private SocialSnsSubjectDao socialSnsSubjectDao;

    @Resource
    private SocialSnsCommunityDao socialSnsCommunityDao;

    @Resource
    private SocialSnsRemindDao socialSnsRemindDao;

    @Resource
    private SocialSnsTopicDao socialSnsTopicDao;

    /**
     * 分页查询用户关注的帖子
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage concernedPostPage(Map<String,String> params) {
        String customerId = params.get("customerId");
        Page page = PageFactory.getPage(params);
        // 查询方法
        List<SocialSnsPosts> list = socialSnsPostsDao.concernedPostPage(params);
        List<SocialSnsPosts> socialSnsPosts = postStatusDeal(customerId, list);
        return new FrontPage(socialSnsPosts, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 发现频道的帖子分页查询
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage discoverPost(Map<String,String> params) {
        String customerId = params.get("customerId");
        Page page = PageFactory.getPage(params);
        // 查询方法
        List<SocialSnsPosts> list = socialSnsPostsDao.discoverPost(params);
        for (SocialSnsPosts ssp : list) {
            Long postsId = ssp.getId();
            // 查询帖子图片
            String pic = socialSnsPictureDao.getFirstPics(postsId);
            // 查询帖子点赞数
            int countPraise = socialSnsPraiseDao.countPraise(postsId);
            // 查询帖子是否被当前用户点赞
            int hasPraise = socialSnsPraiseDao.hasPraise(postsId, customerId);
            List<String> pics = new ArrayList<String>();
            pics.add(pic);
            ssp.setPics(pics);
            ssp.setCountPraise(countPraise);
            ssp.setHasPraise(hasPraise);
        }
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 最近(新)的帖子分页查询
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage recentPost(Map<String,String> params) {
        String customerId = params.get("customerId");
        Page page = PageFactory.getPage(params);
        // 查询方法
        List<SocialSnsPosts> list = socialSnsPostsDao.recentPost(params);
        List<SocialSnsPosts> socialSnsPosts = postStatusDeal(customerId, list);
        return new FrontPage(socialSnsPosts, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 社区&话题分类信息查询
     *
     * @param language
     * @return
     */
    @Override
    public List<SocialSnsSubject> subjectInfo(String language) {
        return socialSnsSubjectDao.subjectInfo(language);
    }

    /**
     * @param language
     * @return
     */
    @Override
    public List<SocialSnsCommunity> communityInfo(String language) {
        language = StringUtils.isEmpty(language) ? "zh_CN" : language;
        SocialSnsCommunity ssc = new SocialSnsCommunity();
        if ("zh_CN".equals(language)) {
            ssc.setName("全部");
        } else {
            ssc.setName("All");
        }
        ssc.setCommKey("All");
        Map<String,String> snsConfig = redisService.getMap("sns_config");
        String csn = snsConfig.get("community_show_num");
        List<SocialSnsCommunity> list = socialSnsCommunityDao.communityInfo(language, csn);
        list.add(0, ssc);
        return list;
    }

    /**
     * 添加帖子提交
     *
     * @param customerId   发帖人ID
     * @param content      内容
     * @param title        标题
     * @param communityKey 社区KEY
     * @param topicId      话题ID
     * @param warns        @的用户的ID
     * @param price        收费帖子价格
     * @param device       设备
     * @param site         位置
     * @param fileMap      上传文件集合
     * @return
     */
    @Override
    public RemoteResult addPost(Long customerId, String content, String title, String communityKey, Long topicId, Long[] warns, BigDecimal price, String device, String site, Map<String,MultipartFile> fileMap) {
        RemoteResult remoteResult = new RemoteResult();

        if (StringUtils.isEmpty(title)) {
            title = content.substring(0, 48);
        }
        SocialSnsPosts ssp = new SocialSnsPosts();
        ssp.setCustomerId(customerId);
        ssp.setTitle(title);
        ssp.setContent(content);
        if (StringUtils.isEmpty(communityKey)) {
            ssp.setCommunityKey(communityKey);
        }
        if (StringUtils.isEmpty(topicId)) {
            ssp.setTopicId(topicId);
        }
        if (StringUtils.isEmpty(price)) {
            ssp.setPrice(price);
            ssp.setIsPaid(1);
        }
        if (StringUtils.isEmpty(device)) {
            ssp.setDevice(device);
        }
        if (StringUtils.isEmpty(site)) {
            ssp.setSite(site);
        }
        int hasWarn = 0;
        if (warns != null && warns.length > 0) {
            ssp.setHasWarn(1);
            hasWarn = 1;
        }
        ssp.setHasWarn(hasWarn);
        if (fileMap != null && fileMap.size() > 0) {
            Boolean checkFile = FileUploadUtils.checkFile(fileMap);
            if (!checkFile) {
                remoteResult.setMsg("picture_error");
                return remoteResult;
            }
            List<Map<String,String>> list = FileUploadUtils.uploadFile(fileMap);
            if (list == null) {
                remoteResult.setMsg("picture_error");
                return remoteResult;
            }
            Boolean hasPic = FileUploadUtils.hasPic(fileMap);
            Boolean hasVideo = FileUploadUtils.hasVideo(fileMap);
            if (hasPic) {
                ssp.setHasPicture(1);
            }
            if (hasVideo) {
                ssp.setHasVideo(1);
            }
            socialSnsPostsDao.insertSelective(ssp);
            Long postId = ssp.getId();
            for (Map<String,String> map : list) {
                String path = map.get("path");
                String type = map.get("type");
                if ("1".equals(type)) {
                    SocialSnsPicture socialSnsPicture = new SocialSnsPicture();
                    socialSnsPicture.setCustomerId(customerId);
                    socialSnsPicture.setPostsId(postId);
                    socialSnsPicture.setPath(path);
                    socialSnsPicture.setStates(1);
                    socialSnsPictureDao.insertSelective(socialSnsPicture);
                }
                if ("2".equals(type)) {
                    SocialSnsVideo socialSnsVideo = new SocialSnsVideo();
                    socialSnsVideo.setCustomerId(customerId);
                    socialSnsVideo.setPostsId(postId);
                    socialSnsVideo.setPath(path);
                    socialSnsVideo.setStates(1);
                    socialSnsVideoDao.insertSelective(socialSnsVideo);
                }
            }
            if (warns != null && warns.length > 0) {
                for (Long warnId : warns) {
                    SocialSnsRemind ssr = new SocialSnsRemind();
                    ssr.setCustomerId(customerId);
                    ssr.setPostsId(postId);
                    ssr.setRemindId(warnId);
                    ssr.setStates(1);
                    socialSnsRemindDao.insertSelective(ssr);
                }
            }
        }
        remoteResult.setSuccess(true);
        return remoteResult;
    }

    /**
     * 话题信息查询
     *
     * @param type
     * @param customerId
     * @return
     */
    @Override
    public List<SocialSnsTopic> topicInfo(String type, Long customerId) {
        Map<String,Object> map = new HashMap<>();
        if ("1".equals(type)) {
            map.put("customerId", customerId);
        }
        return socialSnsTopicDao.topicInfo(map);
    }

    /**
     * 话题信息添加
     *
     * @param customerId
     * @param name
     * @return
     */
    @Override
    public RemoteResult addTopic(Long customerId, String name, String typeKey) {
        RemoteResult remoteResult = new RemoteResult();
        try {
            int sortNum = socialSnsTopicDao.getSortNum();
            SocialSnsTopic sst = new SocialSnsTopic();
            sst.setCustomerId(customerId);
            sst.setName(name);
            sst.setSortNum(sortNum++);
            sst.setSubjKey(typeKey);
            socialSnsTopicDao.insertSelective(sst);
            remoteResult.setSuccess(true);
        } catch (DuplicateKeyException e) {
            remoteResult.setMsg("has_exist");
        }
        return remoteResult;
    }

    /**
     * 付费配置信息
     *
     * @return
     */
    @Override
    public RemoteResult payConfig() {
        RemoteResult remoteResult = new RemoteResult();
        try {
            Map<String,String> snsConfig = redisService.getMap("sns_pay_config");
            String payAmountList = snsConfig.get("pay_amount_list");
            String payAmountMax = snsConfig.get("pay_amount_max");
            Map<String,String> snsCof = new HashMap<>();
            snsCof.put("payAmountList", payAmountList);
            snsCof.put("payAmountMax", payAmountMax);
            remoteResult.setSuccess(true);
            remoteResult.setObj(snsCof);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return remoteResult;
    }

    /**
     * 付费帖子购买
     *
     * @param postsId
     * @return
     */
    @Override
    public RemoteResult payPost(Long postsId, Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        SocialSnsPosts socialSnsPosts = socialSnsPostsDao.selectByPrimaryKey(postsId);
        Integer isPaid = socialSnsPosts.getIsPaid();
        if (isPaid.intValue() == 0) {
            remoteResult.setMsg("non_paid_posts");
            return remoteResult;
        }
        // TODO 付费帖子购买
        remoteResult.setSuccess(true);
        return remoteResult;
    }

    /**
     * 帖子打赏
     *
     * @param postsId
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult rewardPost(Long postsId, Long customerId) {
        return null;
    }

    /**
     * 帖子收藏
     *
     * @param postsId
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult favoritePost(Long postsId, Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        String cutId = customerId.toString();
        String lockId = "SYNC_FAVORITE:" + postsId.toString() + ":" + cutId;
        boolean isLocked = false;
        try {
            boolean lock = redisService.lock(lockId);
            if (lock) {
                isLocked = true;
                int hasFavorite = socialSnsFavoriteDao.hasFavorite(postsId, cutId);
                switch (hasFavorite) {
                    case 0:
                        SocialSnsPosts socialSnsPosts = socialSnsPostsDao.selectByPrimaryKey(postsId);
                        String firstPics = socialSnsPictureDao.getFirstPics(postsId);
                        String title = socialSnsPosts.getTitle();
                        Long customId = socialSnsPosts.getCustomerId();
                        SocialSnsFavorite socialSnsFavorite = new SocialSnsFavorite();
                        socialSnsFavorite.setPostsId(postsId);
                        socialSnsFavorite.setCustomerId(customId);
                        socialSnsFavorite.setFavoriteId(customerId);
                        socialSnsFavorite.setPicture(firstPics);
                        socialSnsFavorite.setTitle(title);
                        socialSnsFavorite.setStates(1);
                        socialSnsFavoriteDao.insertSelective(socialSnsFavorite);
                        break;
                    case 1:
                        socialSnsFavoriteDao.deleteFavorite(postsId, customerId);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (isLocked) {
                redisService.unLock(lockId);
            }
        }
        remoteResult.setSuccess(true);
        return remoteResult;
    }

    /**
     * 帖子点赞
     *
     * @param postsId
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult praisePost(Long postsId, Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        String cutId = customerId.toString();
        String lockId = "SYNC_PRAISE:" + postsId.toString() + ":" + cutId;
        boolean isLocked = false;
        try {
            boolean lock = redisService.lock(lockId);
            if (lock) {
                isLocked = true;
                int hasPraise = socialSnsPraiseDao.hasPraise(postsId, cutId);
                switch (hasPraise) {
                    case 0:
                        SocialSnsPraise socialSnsPraise = new SocialSnsPraise();
                        socialSnsPraise.setPostsId(postsId);
                        socialSnsPraise.setPraiseId(customerId);
                        socialSnsPraiseDao.insertSelective(socialSnsPraise);
                        break;
                    case 1:
                        socialSnsPraiseDao.deletePraise(postsId, customerId);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (isLocked) {
                redisService.unLock(lockId);
            }
        }
        remoteResult.setSuccess(true);
        return remoteResult;
    }

    /**
     * 帖子分享
     *
     * @param postsId
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult sharePost(Long postsId, Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        SocialSnsShare sss = new SocialSnsShare();
        sss.setPostsId(postsId);
        sss.setShareId(customerId);
        socialSnsShareDao.insertSelective(sss);
        remoteResult.setSuccess(true);
        return remoteResult;
    }

    /**
     * 帖子评论
     *
     * @param postsId
     * @param commId
     * @param customerId
     * @param comment
     * @return
     */
    @Override
    public RemoteResult commentPost(Long postsId, Long commId, Long customerId, String comment) {
        RemoteResult remoteResult = new RemoteResult();
        SocialSnsComment ssc = new SocialSnsComment();
        ssc.setPostsId(postsId);
        if (!StringUtils.isEmpty(commId)) {
            ssc.setParentId(commId);
        }
        ssc.setCommentId(customerId);
        ssc.setComments(comment);
        ssc.setStates(1);
        socialSnsCommentDao.insertSelective(ssc);
        remoteResult.setSuccess(true);
        return remoteResult;
    }

    /**
     * 帖子状态处理,
     * 统计处理、
     * 个人对帖子操作记录处理(点赞、打赏、评论等)
     *
     * @param customerId
     * @param list
     */
    private List<SocialSnsPosts> postStatusDeal(String customerId, List<SocialSnsPosts> list) {
        for (SocialSnsPosts ssp : list) {
            Long postsId = ssp.getId();
            // 查询帖子图片
            List<String> pics = socialSnsPictureDao.getPics(postsId);
            // 查询帖子视频
            List<String> videos = socialSnsVideoDao.getVideos(postsId);
            // 查询帖子点赞数
            int countPraise = socialSnsPraiseDao.countPraise(postsId);
            // 查询帖子打赏数
            BigDecimal countReward = socialSnsRewardDao.countReward(postsId);
            // 查询帖子评论数
            int countComment = socialSnsCommentDao.countComment(postsId);
            // 查询帖子收藏数
            int countFavorite = socialSnsFavoriteDao.countFavorite(postsId);
            // 查询帖子分享数
            int countShare = socialSnsShareDao.countShare(postsId);

            // 查询帖子是否被当前用户点赞
            int hasPraise = socialSnsPraiseDao.hasPraise(postsId, customerId);
            // 查询帖子是否被当前用户打赏
            int hasReward = socialSnsRewardDao.hasReward(postsId, customerId);
            // 查询帖子是否被当前用户评论
            int hasComment = socialSnsCommentDao.hasComment(postsId, customerId);
            // 查询帖子是否被当前用户收藏
            int hasFavorite = socialSnsFavoriteDao.hasFavorite(postsId, customerId);
            // 查询帖子是否被当前用户分享
            int hasShare = socialSnsShareDao.hasShare(postsId, customerId);

            ssp.setPics(pics);
            ssp.setVideos(videos);
            ssp.setCountPraise(countPraise);
            ssp.setCountReward(countReward);
            ssp.setCountComment(countComment);
            ssp.setCountFavorite(countFavorite);
            ssp.setCountShare(countShare);

            ssp.setHasPraise(hasPraise);
            ssp.setHasReward(hasReward);
            ssp.setHasComment(hasComment);
            ssp.setHasFavorite(hasFavorite);
            ssp.setHasShare(hasShare);

            //发布时间处理
            String releaseTime = APPDateUtil.appDate(ssp.getCreated());
            ssp.setReleaseTime(releaseTime);
        }
        return list;
    }

}
