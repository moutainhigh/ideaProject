package hry.manage.remote;

import java.math.BigDecimal;

import hry.manage.remote.model.ApiExApiApply;
import hry.manage.remote.model.Entrust;

public interface RemoteApiService {
	public ApiExApiApply getExApiApply(String accesskey, String ip);
	public ApiExApiApply getExApiApply(String accesskey);

	//根据委托单号获取委托单
	public Entrust selectExEntrust(String entrustNum);

}
