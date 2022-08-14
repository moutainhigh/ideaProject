/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年6月27日 下午6:46:25
 */
package hry.exchange.remote.account;

import java.util.List;

import javax.annotation.Resource;

import hry.change.remote.account.RemoteProductReviewService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.StringUtil;
import hry.exchange.product.model.ProductReview;
import hry.exchange.product.service.ProductReviewService;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年6月27日 下午6:46:25 
 */
public class RemoteProductReviewServiceImpl implements RemoteProductReviewService{

	@Resource(name="productReviewService")
	public ProductReviewService productReviewService;
	
	@Override
	public List<ProductReview> findReviewByCoincode(RemoteQueryFilter rFilter) {
		
		QueryFilter filter = rFilter.getQueryFilter();
		
		List<ProductReview> list = productReviewService.find(filter);
		if(list.size()>0){
			for(ProductReview productReview : list){
				String customerName = productReview.getCustomerName();
				String newCustomerName = StringUtil.regularString(customerName);
				productReview.setCustomerName(newCustomerName);
			}
		}
		
		return list;
	}

	@Override
	public String giveReviewpraise(Long id, int a ,String s) {
		
		ProductReview review = productReviewService.get(id);
		if("like".equals(s)){
			if(-1 ==a || 1==a){
				Integer praise = review.getPraise();
				review.setPraise(praise+a);
				productReviewService.update(review);
				return "OK";
			}
		}
		if("hate".equals(s)){
				if(-1 ==a || 1==a){
					Integer criticize = review.getCriticize();
					review.setCriticize(criticize+a);
					productReviewService.update(review);
					return "OK";
				}
			}
		return "NO";
	}

	@Override
	public JsonResult saveProductReview(ProductReview productReview) {
		
		JsonResult jsonResult = new JsonResult(); 
		productReviewService.save(productReview);
		jsonResult.setSuccess(true);
		return jsonResult;
	}

}
