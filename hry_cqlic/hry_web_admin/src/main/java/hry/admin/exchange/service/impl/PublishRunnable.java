package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.ExProduct;
import hry.admin.exchange.service.ExProductService;
import hry.util.SpringUtil;

/**
 * 发布产品
 * @author CHINA_LSL
 *
 */
public class PublishRunnable implements Runnable   {
	
	private ExProduct exProduct;
	
	private PublishRunnable(){}
	
	public PublishRunnable(ExProduct exProduct){
		this.exProduct = exProduct;
	}

	public void setExProduct(ExProduct exProduct) {
		this.exProduct = exProduct;
	}

	@Override
	public void run() {

		ExProductService exProductService = SpringUtil.getBean("exProductService");
		exProductService.syncedUser(exProduct);


	}
}
