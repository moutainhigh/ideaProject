/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年10月25日 下午6:50:40
 */
package hry.exchange.transaction;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Liu Shilei
 * @Date : 2016年10月25日 下午6:50:40
 */
public class CoinQuart {

	public void recordAll() {/*

		try {

			Map<String, String> map = new HashMap<String, String>();
			// 查询已实名的用户
			RemoteAppCustomerService remoteAppCustomerService = (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");
			List<AppCustomer> customerList = remoteAppCustomerService.getRealNameCustomer();

			// 查询所有产品
			QueryFilter f = new QueryFilter(ExProduct.class);
			f.addFilter("issueState=", "1");
			ExProductService exProductService = (ExProductService) ContextUtil.getBean("exProductService");
			List<ExProduct> l = exProductService.find(f);

			ExDigitalmoneyAccountService exDigitalmoneyAccountService = (ExDigitalmoneyAccountService) ContextUtil.getBean("exDigitalmoneyAccountService");

			ExDmTransactionService exDmTransactionService = (ExDmTransactionService) ContextUtil.getBean("exDmTransactionService");

			StringBuffer sf = new StringBuffer();
			for (AppCustomer customer : customerList) {

				List<ExDigitalmoneyAccount> digitalmoneyAccountList = exDigitalmoneyAccountService.getlistByCustomerId(customer.getId());

				for (ExDigitalmoneyAccount digitalmoneyAccount : digitalmoneyAccountList) {

					String txStr = "";
					
					// 国际站
					if (digitalmoneyAccount.getWebsite().equals(ContextUtil.EN)) {
						txStr = CoinInterfaceUtil.listByCustomerName(digitalmoneyAccount.getCoinCode(), customer.getUserName() + "-USD");

					} else {
						System.out.println("线程等待1500毫秒");
						Thread currentThread = Thread.currentThread();
						currentThread.sleep(1500);
						txStr = CoinInterfaceUtil.listByCustomerName(digitalmoneyAccount.getCoinCode(), customer.getUserName());
						}

					System.out.println(txStr+"===list");
					if (!"".equals(txStr) && null != txStr&&txStr.startsWith("[")) {
						@SuppressWarnings("unchecked")
						List<String> txList = (List<String>) JSON.parse(txStr);

						for (String txs : txList) {
							// 转换为map
							Map<String, Object> tx2map = StringUtil.str2map(txs);
							String json = com.alibaba.fastjson.JSON.toJSONString(tx2map);
							json = json.replaceAll(" ", "");
							hry.exchange.coin.model.Transaction tx = com.alibaba.fastjson.JSON.parseObject(json, hry.exchange.coin.model.Transaction.class);

							String name = customer.getUserName();
							String address = tx.getAddress();
							QueryFilter queryFilter = new QueryFilter(ExDigitalmoneyAccount.class);
							queryFilter.addFilter("publicKey=", address);
							queryFilter.addFilter("userName=", name);
							ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(queryFilter);
							if (null != exDigitalmoneyAccount) {
								// 站点 中国站/国际站
								String webSite = exDigitalmoneyAccount.getWebsite();
								// 用户持币code
								String userCode = exDigitalmoneyAccount.getCoinCode();
								String currencyType = exDigitalmoneyAccount.getCurrencyType();

								String category = tx.getCategory();// 交易类型
								if (category.equals("receive")) {
									// confirmations
									String confirmations = String.valueOf(tx.getConfirmations());// 确认节点数
									String amount = String.valueOf(tx.getAmount());
									String blocktime = "";
									if (!confirmations.equals("0")) {
										blocktime = tx.getBlockTime();// 区块时间
										blocktime = DateUtil.stampToDate(blocktime + "000");
									}

									String txid = tx.getTxId();// 交易单号
									String time = tx.getTime().toString();
									if (null != time && !"".equals(time)) {
										time = DateUtil.stampToDate(time + "000");
									}
									String timereceived = tx.getTimeReceived();
									if (null != timereceived && !"".equals(timereceived)) {
										timereceived = DateUtil.stampToDate(timereceived + "000");
									}
									Object feeobj = tx.getFee();
									String fee = "";
									if (null != feeobj) {
										fee = String.valueOf(feeobj);
									}
									// 记录 收入的金额

									QueryFilter filter = new QueryFilter(ExDmTransaction.class);
									filter.addFilter("orderNo=", txid);
									filter.addFilter("customerName=", customer.getUserName());
									ExDmTransaction transaction = exDmTransactionService.get(filter);
									if (null == transaction ) {
										ExDmTransaction exDmTransaction = new ExDmTransaction();
										exDmTransaction.setAccountId(exDigitalmoneyAccount.getId());
										exDmTransaction.setCoinCode(userCode);
										exDmTransaction.setCreated(new Date());
										exDmTransaction.setCurrencyType(currencyType);
										exDmTransaction.setCustomerId(customer.getId());
										exDmTransaction.setCustomerName(customer.getUserName());
										exDmTransaction.setTrueName(exDigitalmoneyAccount.getTrueName());
										exDmTransaction.setTime(time);
										exDmTransaction.setTimereceived(timereceived);
										exDmTransaction.setInAddress(address);
										exDmTransaction.setConfirmations(confirmations);
										exDmTransaction.setBlocktime(blocktime);
										exDmTransaction.setFee(new BigDecimal(0));
										exDmTransaction.setTransactionMoney(new BigDecimal(amount));
										exDmTransaction.setStatus(1);
										exDmTransaction.setOrderNo(txid);
										exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
										// 充币
										exDmTransaction.setTransactionType(1);
										exDmTransaction.setUserId(customer.getId());
										exDmTransaction.setWebsite(webSite);

										exDmTransactionService.save(exDmTransaction);

										long num = Long.valueOf(confirmations);
										if (num >= 2) {

											QueryFilter fil = new QueryFilter(ExDmTransaction.class);
											fil.addFilter("orderNo=", txid);
											fil.addFilter("customerName=", customer.getUserName());
											
											ExDmTransaction tran = exDmTransactionService.get(fil);
											ExAmineOrderService examineOrderService = (ExAmineOrderService) ContextUtil.getBean("examineOrderService");

											String s = examineOrderService.pasePutOrder(tran.getId());

										}

									}

								}

							}

						}

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	*/}

}
