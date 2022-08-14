/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 14:44:12
 */
package hry.social.force.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.social.force.dao.SocialCalculateForceDao;
import hry.social.force.service.SocialCalculateForceService;
import hry.social.manage.remote.model.force.SocialCalculateForce;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> SocialCalculateForceService </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 14:44:12
 */
@Service("socialCalculateForceService")
public class SocialCalculateForceServiceImpl extends BaseServiceImpl<SocialCalculateForce,Long> implements SocialCalculateForceService {

    @Resource(name = "socialCalculateForceDao")
    @Override
    public void setDao(BaseDao<SocialCalculateForce,Long> dao) {
        super.dao = dao;
    }


    /**
     * 查询满足条件的算力用户
     *
     * @param rewardCondition
     * @return
     */
    @Override
    public List<SocialCalculateForce> getCondePerson(BigDecimal rewardCondition) {
        return ((SocialCalculateForceDao) dao).getCondePerson(rewardCondition);
    }

    /**
     * 查询全网总算力
     *
     * @return
     */
    @Override
    public BigDecimal getAllTotal() {
        return ((SocialCalculateForceDao) dao).getAllTotal();
    }

    /**
     * 更新奖励状态
     *
     * @param id
     */
    @Override
    public void updateStates(Long id) {
        ((SocialCalculateForceDao) dao).updateStates(id);
    }

    /**
     * 查询用户实时算力
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal getRealtimeForce(Long customerId) {
        SocialCalculateForce socialCalculateForce = ((SocialCalculateForceDao) dao).getForceByCustomerId(customerId);
        BigDecimal totalForce = socialCalculateForce.getTotalForce();
        return totalForce;
    }

    /**
     * 计算算力排名,排名先按值排序，值相同的按照ID由小到大排
     *
     * @param force
     * @param customerId
     * @return
     */
    @Override
    public int getRankNum(BigDecimal force, Long customerId) {
        // 查询算力超过当前用户的人数
        int gtForce = ((SocialCalculateForceDao) dao).findGtForce(force);
        // 查询算力相等且customerId小于当前用户的人数
        int eqForce = ((SocialCalculateForceDao) dao).findEqForce(force, customerId);
        int rankNum = gtForce + eqForce;
        return rankNum;
    }

    /**
     * 查询榜首算力
     *
     * @return
     */
    @Override
    public BigDecimal getFirstForce() {
        return ((SocialCalculateForceDao) dao).getFirstForce();
    }

    /**
     * 查询任务算力
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal findTaskFroce(Long customerId) {
        BigDecimal taskFroceTotal = ((SocialCalculateForceDao) dao).taskFroceTotal(customerId);
        BigDecimal perpetualTotal = ((SocialCalculateForceDao) dao).perpetualTotal(customerId);
        return perpetualTotal.add(taskFroceTotal);
    }

    /**
     * 查询矿机算力
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal findMillFroce(Long customerId) {
        BigDecimal millFroce = ((SocialCalculateForceDao) dao).findMillFroce(customerId);
        return millFroce;
    }

    /**
     * 查询全网排名前100的用户
     *
     * @return
     */
    @Override
    public List<SocialCalculateForce> getTop() {
        List<SocialCalculateForce> top100 = ((SocialCalculateForceDao) dao).getTop();
        if (top100 == null) {
            return new ArrayList<SocialCalculateForce>();
        }
        int rankNum = 1;
        for (SocialCalculateForce scf : top100) {
            scf.setRankNum(rankNum);
            // 昵称处理
            String nickName = scf.getNickName();
            int length = nickName.length();
            String startStr = nickName.substring(0, 1);
            String endStr = "";
            if (length >= 3) {
                endStr = nickName.substring(length - 1);
            }
            String newName = startStr + "**" + endStr;
            scf.setNickName(newName);
            rankNum++;
        }
        return top100;
    }

    /**
     * 初始化算力账户
     *
     * @param customerId
     * @return
     */
    @Override
    public void initForce(Long customerId) {
        SocialCalculateForce scf = new SocialCalculateForce();
        scf.setCustomerId(customerId);
        scf.setPerpetualForce(BigDecimal.ZERO);
        scf.setTerminableForce(BigDecimal.ZERO);
        scf.setAdditionForce(BigDecimal.ZERO);
        scf.setTotalForce(BigDecimal.ZERO);
        dao.insertSelective(scf);
    }

    /**
     * 查询币账户排名
     *
     * @param coinCode
     * @param hotMoney
     * @return
     */
    @Override
    public Integer getAccountRankNum(String coinCode, BigDecimal hotMoney, Long customerId) {

        // 查询可用余额超过当前用户的人数
        int gtRankNum = ((SocialCalculateForceDao) dao).getAccountGtRankNum(coinCode,hotMoney);
        // 查询可用余额相等且customerId小于当前用户的人数
        int eqRankNum = ((SocialCalculateForceDao) dao).getAccountEqRankNum(coinCode,hotMoney, customerId);
        int rankNum = gtRankNum + eqRankNum;
        return rankNum;
    }
}
