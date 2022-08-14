package hry.exchange.remote.dmtransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExProductService;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.manage.remote.RemoteWithdrawMoneyService;
import hry.manage.remote.model.ExProductManage;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;

public class RemoteWithdrawMoneyServiceImpl implements RemoteWithdrawMoneyService {
	
	@Resource
	public ExProductService exPproductService;
	
	@Resource
	public ExDmTransactionService exDmTransactionService;
	
	@Override
	public List<ExProductManage> getSelectProduct(){
		List<ExProduct> list = exPproductService.findByIssueState(1, "");
		List<ExProductManage> listmanage = new ArrayList<ExProductManage>();
		if (list != null && list.size() > 0) {
			for (ExProduct exProduct : list) {
				ExProductManage ex = new ExProductManage();
				ex.setCoinCode(exProduct.getCoinCode());
				listmanage.add(ex);
			}
		} 
		return listmanage;
	}

	@Override
	public FrontPage findFrontPage(Map<String, String> params) {
		return exDmTransactionService.findFrontPage(params);
	}

	@Override
	public RemoteResult checkPass(Map<String, String> params) {
		return exDmTransactionService.checkPass(params);
	}

	@Override
	public RemoteResult checkReject(Map<String, String> params) {
		return exDmTransactionService.checkReject(params);
	}
	
}
