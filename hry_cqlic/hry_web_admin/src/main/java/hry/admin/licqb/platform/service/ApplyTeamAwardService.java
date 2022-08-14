/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:44:07 
 */
package hry.admin.licqb.platform.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.licqb.platform.model.ApplyTeamAward;
import hry.util.QueryFilter;

import java.util.List;


/**
 * <p> ApplyTeamAwardService </p>
 * @author:         zhouming
 * @Date :          2019-08-12 17:44:07 
 */
public interface ApplyTeamAwardService  extends BaseService<ApplyTeamAward, Long>{

    // 更新释放规则并且亮灯
    public JsonResult updateReleaseRule(Long applyId, Long customerId);

    public PageResult findApplyPage(QueryFilter filter);

    public ApplyTeamAward getApplyTeamAward(Long id);
}
