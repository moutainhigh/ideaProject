/**
 * Copyright:
 * @author:      liuchenghui
 * @version:     V1.0
 * @Date:        2018-06-29 16:05:59
 */
package hry.admin.lock.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.exchange.model.ExDigitalmoneyAccount;
import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.service.ExDigitalmoneyAccountService;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.lock.controller.LockRedisRunnable;
import hry.admin.lock.dao.ExDmLockRecordDao;
import hry.admin.lock.model.ExDmLockRecord;
import hry.admin.lock.model.ExDmUnlockHistory;
import hry.admin.lock.service.ExDmLockRecordService;
import hry.admin.lock.service.ExDmLockReleaseTimeService;
import hry.admin.lock.service.ExDmUnlockHistoryService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.thread.ThreadPool;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.UserRedisUtils;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> ExDmLockRecordService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-29 16:05:59
 */
@Service("exDmLockRecordService")
public class ExDmLockRecordServiceImpl extends BaseServiceImpl<ExDmLockRecord, Long> implements ExDmLockRecordService{

	@Resource
	private MessageProducer messageProducer;

	@Resource
	private ExDmUnlockHistoryService exDmUnlockHistoryService;

	@Resource
	private AppPersonInfoService appPersonInfoService;

	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;

	@Resource
	private ExDmTransactionService exDmTransactionService;

	@Resource
	private ExDmLockReleaseTimeService exDmLockReleaseTimeService;

	@Resource(name="exDmLockRecordDao")
	@Override
	public void setDao(BaseDao<ExDmLockRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql (QueryFilter filter) {
		//创建PageResult对象
		Page<ExDmLockRecord> page = PageFactory.getPage(filter);

		// ----------------------查询开始------------------------------
		// 邮箱
		String email = filter.getRequest().getParameter("email");
		// 手机号
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		// 币种代码
		String coinCode = filter.getRequest().getParameter("coinCode");
		// 解锁状态
		String unlockState = filter.getRequest().getParameter("unlockState");

		Map<String, Object> map = new HashMap<String, Object>();

		// 邮箱
		if (!StringUtils.isEmpty(email)) {
			map.put("email", "%" + email + "%");
		}
		// 手机号
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		// 币种代码
		if (!StringUtils.isEmpty(coinCode)) {
			map.put("coinCode", coinCode);
		}
		// 解锁状态
		if (!StringUtils.isEmpty(unlockState)) {
			map.put("unlockState", unlockState);
		}

		((ExDmLockRecordDao) dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	/**
	 * 手动解锁
	 * @param id
	 * @param unlocknum
	 * @return
	 */
	@Override
	public JsonResult unlockByManual (Long id, BigDecimal unlocknum) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(false);
		try {
			// 获取锁仓记录
			ExDmLockRecord lockRecord = this.get(id);
			// 获取剩余释放量
			BigDecimal remainingRelease = lockRecord.getRemainingRelease();
			// 修改记录
			lockRecord.setUnlockType(2);
			lockRecord.setUnlockState(2);
			lockRecord.setAmountReleased(unlocknum.add(lockRecord.getAmountReleased()));
			BigDecimal remainNum = remainingRelease.subtract(unlocknum);
			lockRecord.setRemainingRelease(remainNum);
			// 剩余冻结数量等于0时，修改锁仓状态为已完成
			if (remainNum.compareTo(BigDecimal.ZERO) == 0) {
				lockRecord.setUnlockState(3);
			}
			lockRecord.setModified(new Date());
			// 更新记录
			this.update(lockRecord);

			// 更改释放时间表记录状态
			exDmLockReleaseTimeService.updReleaseTimeForManual(lockRecord.getId());

			// 写入释放记录
			BaseManageUser currentUser = ContextUtil.getCurrentUser();
			ExDmUnlockHistory history = new ExDmUnlockHistory();
			history.setAccountId(lockRecord.getAccountId());
			history.setCoinCode(lockRecord.getCoinCode());
			history.setCustomerId(lockRecord.getCustomerId());
			// 1、自动 2、手动
			history.setOptType(2);
			history.setRecordId(lockRecord.getId());
			history.setTransactionMoney(unlocknum);
			history.setTransactionNum(lockRecord.getTransactionNum());
			history.setCreated(new Date());
			history.setModified(new Date());
			history.setOptUser(currentUser.getUsername());
			exDmUnlockHistoryService.save(history);

			// 发送mq消息进行账户增减
			String unlockTransactionNum = NumConstant.Ex_Dm_Transaction;
			// 热账户增加
			Accountadd accountaddhot = new Accountadd();
			accountaddhot.setAccountId(lockRecord.getAccountId());
			accountaddhot.setMoney(unlocknum);
			accountaddhot.setMonteyType(1);
			accountaddhot.setAcccountType(1);
			accountaddhot.setRemarks(56);
			accountaddhot.setTransactionNum(IdGenerate.transactionNum(unlockTransactionNum));

			// 冷账户减少
			Accountadd accountaddcold = new Accountadd();
			accountaddcold.setAccountId(lockRecord.getAccountId());
			accountaddcold.setMoney(unlocknum.multiply(new BigDecimal(-1)));
			accountaddcold.setMonteyType(2);
			accountaddcold.setAcccountType(1);
			accountaddcold.setRemarks(56);
			accountaddcold.setTransactionNum(IdGenerate.transactionNum(unlockTransactionNum));

			List<Accountadd> listLock = new ArrayList<Accountadd>();
			listLock.add(accountaddhot);
			listLock.add(accountaddcold);
			messageProducer.toAccount(JSON.toJSONString(listLock));

			jsonResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
		}
		return jsonResult;
	}

	/**
	 * 导入excel数据，并锁仓入库
	 * @param file
	 * @return
	 */
	@Override
	public JsonResult importExcel(MultipartFile file) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(false);
		List<Map<String, Object>> importExcel = new ArrayList<>();
		try {
			if (file.isEmpty()) {
				jsonResult.setSuccess(false).setMsg("文件不存在");
			}
			importExcel = importExcelData(file);
			if (importExcel != null && importExcel.size() > 0) {
				for (Map<String, Object> map : importExcel) {
					if(importExcel.size() > 500){
						Integer totalCount = importExcel.size();
						Integer requestCount = totalCount / 500;
						for (int i = 0; i <= requestCount; i++) {
							Integer fromIndex = i * 500;
							int toIndex = Math.min(totalCount, (i + 1) * 500);
							List<Map<String, Object>> subList = importExcel.subList(fromIndex, toIndex);
							for (Map<String, Object> map2 : subList) {
								// 调用锁仓管理操作
								lockHander(map2);
							}
							if (toIndex == totalCount) {
								break;
							}
						}
					} else {
						// 调用锁仓管理操作
						lockHander(map);
					}
				}
				jsonResult.setSuccess(true);
			}
			System.out.println("已解析到：" + importExcel.size() + "条数据");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setMsg("文件解析错误");
		}

		return jsonResult;
	}

	/**
	 * 封装，调用锁仓方法
	 * @param map
	 */
	private void lockHander(Map<String, Object> map){
		try {
			// 根据手机号或邮箱查询用户信息
			QueryFilter personQF = new QueryFilter(AppPersonInfo.class);
			String email = map.get("email") == null ? "" : map.get("email").toString();
			if (!StringUtils.isEmpty(email)) {
                personQF.addFilter("email=", email);
            }
			String mobilePhone = map.get("mobilePhone") == null ? "" : map.get("mobilePhone").toString();
			if (!StringUtils.isEmpty(mobilePhone)) {
                personQF.addFilter("mobilePhone=", mobilePhone);
            }
			String coinCode = map.get("coinCode") == null ? "" : map.get("coinCode").toString();
			String hotMoney = map.get("hotMoney") == null ? "0" : map.get("hotMoney").toString();

			AppPersonInfo appPersonInfo = appPersonInfoService.get(personQF);
			if(appPersonInfo != null){
                // 获取该用户的币帐户信息
				// 从redis中获取本人的资金账户信息
				RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
				UserRedis userRedis = redisUtil.get(appPersonInfo.getCustomerId().toString());
				if (userRedis != null) {
					ExDigitalmoneyAccountRedis account = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
					if (account != null) {
						//创建充币订单
						ExDmTransaction exDmTransaction = new ExDmTransaction();
						exDmTransaction.setAccountId(account.getId());
						exDmTransaction.setCoinCode(account.getCoinCode());
						exDmTransaction.setCreated(new Date());
						exDmTransaction.setCurrencyType("zh_CN");
						exDmTransaction.setWebsite("cn");
						exDmTransaction.setCustomerId(account.getCustomerId());
						exDmTransaction.setCustomerName(account.getUserName());
						exDmTransaction.setFee(new BigDecimal(0));
						exDmTransaction.setTransactionMoney(new BigDecimal(hotMoney));
						exDmTransaction.setStatus(1);
						exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
						exDmTransaction.setTransactionType(1);
						exDmTransaction.setUserId(ContextUtil.getCurrentUserId());
						exDmTransaction.setStatus(Integer.valueOf(2));// 充值成功
						exDmTransaction.setRemark("手动充" + exDmTransaction.getTransactionMoney() + "个币");
						exDmTransaction.setOptType(4);
						exDmTransactionService.save(exDmTransaction);

						// 发送mq通知缓存
						Accountadd accountadd = new Accountadd();
						accountadd.setAccountId(exDmTransaction.getAccountId());
						accountadd.setMoney(exDmTransaction.getTransactionMoney());
						accountadd.setMonteyType(1);
						accountadd.setAcccountType(1);
						accountadd.setRemarks(23);
						accountadd.setTransactionNum(exDmTransaction.getTransactionNum());
						List<Accountadd> list = new ArrayList<Accountadd>();
						list.add(accountadd);
						messageProducer.toAccount(JSON.toJSONString(list));

						map.put("customerId", account.getCustomerId());
						map.put("accountId", account.getId());
						map.put("coinCode", account.getCoinCode());
						map.put("transactionNum", exDmTransaction.getTransactionNum());
						ThreadPool.exe(new LockRedisRunnable(map));
					} else {
						System.out.println("该用户币账户不存在");
					}
				} else {
					System.out.println("该用户币账户不存在");
				}
            } else{
                System.out.println("未找到的用户：  "+map.get("mobilePhone")+"#######"+map.get("coinCode"));
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取excel中的数据
	 * @param file
	 * @return
	 */
	private List<Map<String, Object>> importExcelData(MultipartFile file){
		int sheetCount = 0;
		int sheetLastRowNumber = 0;
		try {
			Workbook workbook = getWorkBook(file);// 创建工作簿对象
			sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			// 遍历每个Sheet
			for (int s = 0; s < sheetCount; s++) {
				// 得到工作表对象
				Sheet sheet = workbook.getSheetAt(s);
				// 获得最后一行的序号
				sheetLastRowNumber = sheet.getLastRowNum();

				for (int j = 1; j <= sheetLastRowNumber; j++) {
					Row row = sheet.getRow(j);
					if(row != null) {
						Map<String, Object> quaMap = new HashMap<String, Object>();
						List<String> fieldList = toListByString("email,mobilePhone,hotMoney,coinCode");
						for (int k = 0; k < fieldList.size(); k++) {
							Cell cell = row.getCell(k);
							if (cell == null) {
								quaMap.put(fieldList.get(k), "");
							} else {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								quaMap.put(fieldList.get(k), cell
										.getStringCellValue().trim());
							}
						}
						list.add(quaMap);
					}
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取工作薄对象
	 * @param formFile
	 * @return
	 */
	private Workbook getWorkBook(MultipartFile formFile) {
		// 获得文件名
		String fileName = formFile.getOriginalFilename();
		// 创建Workbook工作簿对象，表示整个excel
		Workbook workbook = null;
		try {
			// 获得excel文件的io流
			//mulfile.transferTo(formFile);
			InputStream is = formFile.getInputStream();
			// 根据文件后缀名不同（xls和xlsx）获得不同的workbook实现类对象
			if (fileName.endsWith("xls")) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith("xlsx")) {
				// 2007
				workbook = new XSSFWorkbook(is);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return workbook;
	}

	/**
	 * 将string转成list
	 * @param stringList
	 * @return
	 */
	private List<String> toListByString(String stringList) {
		List<String> list = new ArrayList<String>();
		if (!StringUtils.isEmpty(stringList)) {
			String[] strArray = stringList.split(",");
			for (int i = 0; i < strArray.length; i++) {
				list.add(strArray[i]);
			}
		}
		return list;
	}

	/**
	 * 获取今天释放的记录
	 * @return
	 */
	@Override
	public List<ExDmLockRecord> getRecordBycurdate() {
		Map<String, Object> map = new HashMap<String, Object>();
		return ((ExDmLockRecordDao) dao).getRecordBycurdate(map);
	}
}
