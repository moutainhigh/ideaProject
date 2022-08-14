/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 17:49:59
 */
package hry.social.manage.remote.api.apply;

import hry.manage.remote.model.AppDic;
import hry.social.manage.remote.model.apply.SocialApplyItem;
import hry.social.manage.remote.model.apply.SocialApplyType;

import java.util.List;

/**
 * <p> SocialApplyItemService </p>
 * @author: javalx
 * @Date :          2019-06-03 17:49:59 
 */
public interface RemoteApplyService {

    List<AppDic> applyList();

    List<SocialApplyItem> getApplys(String typeKey);
}
