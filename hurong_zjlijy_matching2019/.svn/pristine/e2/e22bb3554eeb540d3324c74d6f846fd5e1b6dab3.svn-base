package hry.mall.platform.remote;

import hry.bean.JsonResult;
import hry.mall.platform.remote.model.AddressVo;
import java.util.List;
import java.util.Map;

public interface RemoteAddressService {
public 	List<AddressVo>  findByCustomerId(Long customerId);
public  JsonResult  addAddress(AddressVo addressVo);
public  JsonResult  modifyAddress(AddressVo addressVo);
public  JsonResult  deleteAddress(Long id);

public  JsonResult  getAddress(Long id);

public  JsonResult getFeedback();

public  JsonResult saveFeedback(Map<String, String> map);

public JsonResult mallCoinList();

/**
 * 混合支付查询数字币列表方法
 * @param map
 * @return
 */
public  JsonResult  blendCoinList();

public JsonResult rmbList();
}
