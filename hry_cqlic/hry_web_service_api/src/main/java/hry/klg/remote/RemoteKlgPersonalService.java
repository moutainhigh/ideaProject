package hry.klg.remote;

import java.util.Map;

import hry.manage.remote.model.base.FrontPage;

/**
 * 个人中心
 * @author lenovo
 *
 */
public interface RemoteKlgPersonalService {
	
	/**
     * 查询推荐明细
     * @param params
     * @return
     */
    public FrontPage findRecommenderList(Map<String, String> params);

}
