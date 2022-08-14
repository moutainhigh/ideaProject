/**
 * Copyright:
 * @author:      liuchenghui
 * @version:     V1.0
 * @Date:        2018-06-29 16:05:59
 */
package hry.admin.lock.controller;


import hry.admin.lock.model.ExDmLockRecord;
import hry.admin.lock.model.ExDmUnlockHistory;
import hry.admin.lock.service.ExDmLockRecordService;
import hry.admin.lock.service.ExDmUnlockHistoryService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;

/**
 * Copyright:   互融云
 * @author:      liuchenghui
 * @version:     V1.0
 * @Date:        2018-06-29 16:05:59
 */
@Controller
@RequestMapping("/lock/exdmlockrecord")
public class ExDmLockRecordController extends BaseController<ExDmLockRecord, Long> {

	@Resource
	private ExDmUnlockHistoryService exDmUnlockHistoryService;

	@Resource(name = "exDmLockRecordService")
	@Override
	public void setService(BaseService<ExDmLockRecord, Long> service) {
		super.service = service;
	}

	@MethodName(name = "查看锁仓记录列表")
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExDmLockRecord.class, request);
		return ((ExDmLockRecordService) service).findPageBySql(filter);
	}

	@MethodName(name="手动解锁")
	@RequestMapping(value="/unlockByManual")
	@ResponseBody
	public JsonResult unlockByManual(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(false);
		String id = request.getParameter("id");
		String unlockNum = request.getParameter("unlockNum");
		if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(unlockNum)) {
			return ((ExDmLockRecordService) service).unlockByManual(new Long(id), new BigDecimal(unlockNum));
		}
		return jsonResult;
	}

	@MethodName(name = "查看释放记录列表")
	@RequestMapping(value="/unlockList")
	@ResponseBody
	public PageResult unlockList(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExDmUnlockHistory.class, request);
		return exDmUnlockHistoryService.findPageBySql(filter);
	}

	@MethodName(name = "导入excel")
	@RequestMapping("/importExcel")
	@ResponseBody
	public JsonResult importExcel(@RequestParam(value = "file" , required = true) MultipartFile file){
		return ((ExDmLockRecordService) service).importExcel(file);
	}

	@MethodName(name = "导入excel")
	@RequestMapping("/downloadExcel")
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			String downloadPath = request.getSession().getServletContext().getRealPath("/template/template.xls");
			//System.out.println(downloadPath);
			File file = new File(downloadPath);
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			long fileLength = file.length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("template.xls".getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downloadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null)
					bis.close();
				if (bos != null){
					bos.flush();
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
