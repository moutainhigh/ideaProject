package hry.licqb.remote;

import hry.bean.FrontPage;
import hry.bean.JsonResult;

import java.util.List;
import java.util.Map;

/**
 * @author zhouming
 * @date 2019/8/29 10:38
 * @Version 1.0
 */
public interface RemoteRecordService {

    /**
     * 统计我的社区相关信息
     * */
    public JsonResult findMyTeam(Long customerId);

    /**
     * 统计推荐信息 一级 二级 三级以上
     * */

    public JsonResult findRecommendUser(Long customerId);

    /**
     * 获取直推用户信息
     * */
    public FrontPage findOneCommendUserInfo(Map<String, String> map);

    /**
     * 统计指定用户下等级用户数量
     * */
    public JsonResult findCountUserLevelNum(Long customerId);

    /**
     * 申请社区共建奖励 (查看是否达到申请条件，申请、审核状态)
     * */
    public JsonResult applyTeamAward(Long customerId);

    /**
     * 保存申请社区信息
     * */
    public JsonResult saveApplyTeamAward(Map<String, String> map);

    /**
     * 获取项目获利 前十用户
     * */
    public JsonResult findEarningsTopTen();

    /**
     * 获取用户等级相关信息
     * */
    public JsonResult myLevelInfo(Long customerId);

    /**
     * 李超项目 提币校验
     * */
    public JsonResult tiBiCheck(Long customerId,String coinCode);

    /*
    * lc_deal_record 分表
    * */
    public JsonResult separateTable();
}
