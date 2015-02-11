package com.focusx.exp.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.attilax.biz.orgGroup.grpX;
import com.attilax.biz.orgGroup.isSuperAdminEx;
import com.attilax.io.filex;
import com.focusx.eq.EqX;
import com.focusx.exp.service.IExpService;
import com.focusx.programme.action.ProgrammeAction;
import com.focusx.util.JxlUtils;
import com.focusx.util.TxtUtil;
import com.focusx.util.Utils;

public class ExpAction {

	protected static Logger logger = Logger.getLogger(ProgrammeAction.class);

	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	private IExpService expService;

	/**
	 * 1导出Excel播放流水
	 * 
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("all")
	public String expExcelPlayWater() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
	
		List expList = playWaterData();
		if(  expList.size()==0  )
			System.out.println("dbug");

		String xlsName = "播放流水" + sdf.format(new Date()) + ".xls";// 导出报表名称
		String[] tableHeader = { "流水id","分公司", "区域","门店","门店id","设备描述","设备id","节目单描述","节目单ID","分屏区","素材描述","素材id" ,
				"播放时间"};// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			JxlUtils.export(tableHeader, xlsName, footer, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
			filex.saveLog(e, "c:\\e");
		} 
		return null;
	}

	/**
	 * 2导出Excel播放统计
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expExcelPlayCount() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = playCountData();
		String xlsName = "播放统计(按门店)" + sdf.format(new Date()) + ".xls";// 导出报表名称
		String[] tableHeader = {"分公司","区域","门店", "门店id","素材描述","素材id","播放次数","总播放时长","统计时间" };// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			JxlUtils.export(tableHeader, xlsName, footer, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 3导出Excel下载流水
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expExcelDownWater() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = downWaterData();

		String xlsName = "下载流水" + sdf.format(new Date()) + ".xls";// 导出报表名称
		String[] tableHeader = { "id","分公司","区域", "门店",
				"门店id", "设备描述","设备id","节目单描述", "节目单id" , "素材描述", "素材id" , "下载完成时间","素材文件名称","远程文件状态" };// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			JxlUtils.export(tableHeader, xlsName, footer, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 4导出Excel下载管理
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expExcelDownMana() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = downManaData();

		String xlsName = "下载管理" + sdf.format(new Date()) + ".xls";// 导出报表名称
		String[] tableHeader = { "id","分公司","区域", "门店", "门店id","设备描述","设备id","发布描述","发布编号id","节目单描述","节目单id","素材描述","素材id","下载状态",
				"设备id", "操作者", "操作时间","创建时间" };// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			JxlUtils.export(tableHeader, xlsName, footer, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 5导出Excel下载统计
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expExcelDownCount() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = downCountData();

		String xlsName = "下载统计" + sdf.format(new Date()) + ".xls";// 导出报表名称
		String[] tableHeader = { "时间", "应该下载数量", "实际下载数量","分公司","区域","门店", "门店id",
				"设备id", "设备描述" };// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			JxlUtils.export(tableHeader, xlsName, footer, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 6导出Excel 发布
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expExcelPublish() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = publishData();

		String xlsName = "发布" + sdf.format(new Date()) + ".xls";// 导出报表名称
		String[] tableHeader = { "id", "分公司", "区域","门店","设备名称 ","节目单", "播放类型",
				"插播开始时间 ", "起始时间", "结束时间","发布人","发布时间","描述" };// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			JxlUtils.export(tableHeader, xlsName, footer, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 7导出Excel 门店
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expExcelStore() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = storeData();

		String xlsName = "门店" + sdf.format(new Date()) + ".xls";// 导出报表名称
		String[] tableHeader = { "分店ID", "分公司", "区域","分店名称","地址","创建时间"};// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			JxlUtils.export(tableHeader, xlsName, footer, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	} 


	/**
	 * 8导出Excel播放统计(按区域)
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expExcelPlayCountByMtrlArea() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = playCounByMtrlAreatData();
		String xlsName = "播放统计(按区域)" + sdf.format(new Date()) + ".xls";// 导出报表名称
		String[] tableHeader = {"分公司","区域","门店数量", "素材描述","素材id","播放次数","总播放时长","统计时间" };// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			JxlUtils.export(tableHeader, xlsName, footer, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	


	/**
	 * 9导出Excel下载统计-分公司
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expExcelDownCountByMtarlBranch() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = downCountByMtarlBranchData();

		String xlsName = "下载统计(按分公司)" + sdf.format(new Date()) + ".xls";// 导出报表名称
		String[] tableHeader = { "分公司", "素材描述","素材id", "应下载数量","实际下载数量" };// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			JxlUtils.export(tableHeader, xlsName, footer, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 10导出Excel下载统计-门店
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expExcelDownCountByMtarlStore() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = downCountByMtarlStoreData();

		String xlsName = "下载统计(按门店)" + sdf.format(new Date()) + ".xls";// 导出报表名称
		String[] tableHeader = { "id","分公司", "区域","门店","节目单","节目单id","素材","素材id","状态" };// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			JxlUtils.export(tableHeader, xlsName, footer, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// ################exp text

	/**
	 * 1导出Txt播放流水
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expTxtPlayWater() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = playWaterData();
		String txtName = "播放流水" + sdf.format(new Date()) + ".txt";// 导出报表名称
		String tableHeader = "流水id,分公司,区域,门店,门店id,设备描述,设备id ,节目单描述,节目单ID,分屏区,素材描述,素材id,播放时间 ";// 标题栏
		try {
			TxtUtil.export(tableHeader, txtName, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 2导出Txt播放统计
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expTxtPlayCount() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = playCountData();
		String txtName = "播放统计" + sdf.format(new Date()) + ".txt";// 导出报表名称
		String tableHeader = "分公司 ,区域,门店,门店id,素材描述,素材id,播放次数,总播放时长,统计时间";// 标题栏
		try {
			TxtUtil.export(tableHeader, txtName, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 3导出Txt下载流水
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expTxtDownWater() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = downWaterData();
		String txtName = "下载流水" + sdf.format(new Date()) + ".txt";// 导出报表名称
		String tableHeader = "id,分公司,区域,门店,门店id,设备描述,设备id,节目单描述,节目单id,素材描述,素材id,下载完成时间, 素材文件名称,远程文件状态";// 标题栏
		try {
			TxtUtil.export(tableHeader, txtName, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 4导出Txt下载管理
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expTxtDownMana() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = downManaData();
		String txtName = "下载管理" + sdf.format(new Date()) + ".txt";// 导出报表名称
		String tableHeader = "id,分公司,区域, 门店, 门店id,设备描述,设备id,发布描述,发布编号id,节目单描述,节目单id,素材描述,素材id,下载状态,设备id,操作者,操作时间,创建时间";// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			TxtUtil.export(tableHeader, txtName, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 5导出Txt下载统计
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expTxtDownCount() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = downCountData();
		String txtName = "下载统计" + sdf.format(new Date()) + ".txt";// 导出报表名称
		String tableHeader = "时间,应该下载数量,实际下载数量,分公司,区域,门店,门店id,设备id,设备描述";// 标题栏
		Integer footer[] = null; // 是否合计
		try {
			TxtUtil.export(tableHeader, txtName, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 6发布Txt 发布
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expTxtPublish() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = publishData();
		String txtName = "发布" + sdf.format(new Date()) + ".txt";// 导出报表名称
		String tableHeader = "id,分公司,区域,门店,设备名称,节目单,播放类型,插播开始时间,起始时间,结束时间,发布人,发布时间 ,描述";// 标题栏
		try {
			TxtUtil.export(tableHeader, txtName, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 7发布Txt 门店
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expTxtStore() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = storeData();
		String txtName = "门店" + sdf.format(new Date()) + ".txt";// 导出报表名称
		String tableHeader = "分店ID,分公司,区域,分店名称,地址,创建时间";// 标题栏
		try {
			TxtUtil.export(tableHeader, txtName, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	} 

	/**
	 * 8导出Txt播放统计ByMtrlAreat
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expTxtPlayCountByMtrlArea() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = playCounByMtrlAreatData();
		String txtName = "播放统计(按区域)" + sdf.format(new Date()) + ".txt";// 导出报表名称
		String tableHeader = "分公司 ,区域,门店数量,素材描述,素材id,总播放次数,总播放时长,统计时间";// 标题栏
		try {
			TxtUtil.export(tableHeader, txtName, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 9导出Txt下载统计-分公司
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expTxtDownCountByMtarlBranch() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = downCountByMtarlBranchData();

		String txtName = "下载统计(按分公司)" + sdf.format(new Date()) + ".txt";// 导出报表名称
		String tableHeader =  "分公司,素材描述,素材id,应下载数量,实际下载数量";// 标题栏
		try {
			TxtUtil.export(tableHeader, txtName, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 10导出Txt下载统计-门店
	 * 
	 * @return
	 * @throws IOException
	 */
	public String expTxtDownCountByMtarlStore() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List expList = downCountByMtarlStoreData();

		String txtName = "下载统计(按门店)" + sdf.format(new Date()) + ".txt";// 导出报表名称
		String tableHeader = "id,分公司,区域,门店,节目单,节目单id,素材,素材id,状态";// 标题栏
		try {
			TxtUtil.export(tableHeader, txtName, expList, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//################
	
	/**
	 * 
	 * @Description:1 播放统计
	 * @Author:luojungong
	 * @return
	 */
	private List playWaterData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String playTime_start = request.getParameter("playTime_start");
		String playTime_end = request.getParameter("playTime_end");
		String groupid = request.getParameter("groupid");
		String materialId = request.getParameter("materialId");
		logger.info("报表参数 playTime_start" + playTime_start);
		logger.info("报表参数 playTime_end" + playTime_end);
		logger.info("报表参数 groupid" + groupid);
		logger.info("报表参数 materialId" + materialId);

		Map<String, String> QueryPropertyssMap = new HashMap<String, String>();
		if (Utils.isNotEmpty(playTime_start)) {
			QueryPropertyssMap.put("downloadCreateTime_start", playTime_start);
		}
		if (Utils.isNotEmpty(playTime_end)) {
			QueryPropertyssMap.put("downloadCreateTime_end", playTime_end);
		}
		if (Utils.isNotEmpty(groupid)) {
			QueryPropertyssMap.put("groupid", groupid);
		}
		if (Utils.isNotEmpty(materialId)) {
			QueryPropertyssMap.put("materialId", materialId);
		}

		String equipmentIds = "";
		// 通过登录帐号获取 设备id
		Map map = EqX.FindByLoginUid_jsonFmt(request);
		// 是否有设备id
		String isData = String.valueOf(map.get("isData"));
		List expList = null;
		if ("true".equals(isData)) {
			equipmentIds = String.valueOf(map.get("equipmentIds"));
			// 有设备ID 返回为“” 表示为管理员
			if (Utils.isNotEmpty(equipmentIds)) {
				QueryPropertyssMap.put("equipmentIds", equipmentIds);
			}
			expList = expService.playWater(QueryPropertyssMap);
		}
		return expList;
	}

	/**
	 * 
	 * @Description:2 播放统计
	 * @Author:luojungong
	 * @return
	 */
	private List playCountData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String playTime_start = request.getParameter("playTime_start");
		String playTime_end = request.getParameter("playTime_end");
		String groupid = request.getParameter("groupid");
		String materialId = request.getParameter("materialId");
		String timeType = request.getParameter("timeType");
		logger.info("报表参数 playTime_start" + playTime_start);
		logger.info("报表参数 playTime_end" + playTime_end);
		logger.info("报表参数 groupid" + groupid);
		logger.info("报表参数 materialId" + materialId);
		logger.info("报表参数 timeType" + timeType);

		Map<String, String> QueryPropertyssMap = new HashMap<String, String>();
		if (Utils.isNotEmpty(playTime_start)) {
			QueryPropertyssMap.put("downloadCreateTime_start", playTime_start);
		}
		if (Utils.isNotEmpty(playTime_end)) {
			QueryPropertyssMap.put("downloadCreateTime_end", playTime_end);
		}
		if (Utils.isNotEmpty(groupid)) {
			QueryPropertyssMap.put("groupid", groupid);
		}
		if (Utils.isNotEmpty(timeType)) {
			QueryPropertyssMap.put("cycle", timeType);
		} 
		if (Utils.isNotEmpty(materialId)) {
			QueryPropertyssMap.put("materialId", materialId);
		}
		

		String equipmentIds = "";
		// 通过登录帐号获取 设备id
		Map map = EqX.FindByLoginUid_jsonFmt(request);
		// 是否有设备id
		String isData = String.valueOf(map.get("isData"));
		List expList = null;
		if ("true".equals(isData)) {
			equipmentIds = String.valueOf(map.get("equipmentIds"));
			// 有设备ID 返回为“” 表示为管理员
			if (Utils.isNotEmpty(equipmentIds)) {
				QueryPropertyssMap.put("equipmentIds", equipmentIds);
			}
			expList = expService.playCount(QueryPropertyssMap);
		}
		return expList;
	}

	/**
	 * 
	 * @Description:3 下载流水
	 * @Author:luojungong
	 * @return
	 */
	private List downWaterData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String playTime_start = request.getParameter("playTime_start");
		String playTime_end = request.getParameter("playTime_end");
		String groupid = request.getParameter("groupid");
		String materialId = request.getParameter("materialId");
		logger.info("报表参数 playTime_start" + playTime_start);
		logger.info("报表参数 playTime_end" + playTime_end);
		logger.info("报表参数 groupid" + groupid);
		logger.info("报表参数 materialId" + materialId);

		Map<String, String> QueryPropertyssMap = new HashMap<String, String>();
		if (Utils.isNotEmpty(playTime_start)) {
			QueryPropertyssMap.put("downloadCreateTime_start", playTime_start);
		}
		if (Utils.isNotEmpty(playTime_end)) {
			QueryPropertyssMap.put("downloadCreateTime_end", playTime_end);
		}
		if (Utils.isNotEmpty(groupid)) {
			QueryPropertyssMap.put("groupid", groupid);
		}
		if (Utils.isNotEmpty(materialId)) {
			QueryPropertyssMap.put("materialId", materialId);
		}

		String equipmentIds = "";
		// 通过登录帐号获取 设备id
		Map map = EqX.FindByLoginUid_jsonFmt(request);
		// 是否有设备id
		String isData = String.valueOf(map.get("isData"));
		List expList = null;
		if ("true".equals(isData)) {
			equipmentIds = String.valueOf(map.get("equipmentIds"));
			// 有设备ID 返回为“” 表示为管理员
			if (Utils.isNotEmpty(equipmentIds)) {
				QueryPropertyssMap.put("equipmentIds", equipmentIds);
			}
			expList = expService.downWater(QueryPropertyssMap);
		}
		return expList;
	}

	/**
	 * 
	 * @Description:4获取下载管理数据源
	 * @Author:luojungong
	 * @return
	 */
	private List downManaData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String playTime_start = request.getParameter("playTime_start");
		String playTime_end = request.getParameter("playTime_end");
		String groupid = request.getParameter("groupid");
		String materialId = request.getParameter("materialId");
		logger.info("报表参数 playTime_start" + playTime_start);
		logger.info("报表参数 playTime_end" + playTime_end);
		logger.info("报表参数 groupid" + groupid);
		logger.info("报表参数 materialId" + materialId);

		Map<String, String> QueryPropertyssMap = new HashMap<String, String>();
		if (Utils.isNotEmpty(playTime_start)) {
			QueryPropertyssMap.put("downloadCreateTime_start", playTime_start);
		}
		if (Utils.isNotEmpty(playTime_end)) {
			QueryPropertyssMap.put("downloadCreateTime_end", playTime_end);
		}
		if (Utils.isNotEmpty(groupid)) {
			QueryPropertyssMap.put("groupid", groupid);
		}
		if (Utils.isNotEmpty(materialId)) {
			QueryPropertyssMap.put("materialId", materialId);
		}

		String equipmentIds = "";
		// 通过登录帐号获取 设备id
		Map map = EqX.FindByLoginUid_jsonFmt(request);
		// 是否有设备id
		String isData = String.valueOf(map.get("isData"));
		List expList = null;
		if ("true".equals(isData)) {
			equipmentIds = String.valueOf(map.get("equipmentIds"));
			// 有设备ID 返回为“” 表示为管理员
			if (Utils.isNotEmpty(equipmentIds)) {
				QueryPropertyssMap.put("equipmentIds", equipmentIds);
			}
			expList = expService.downMana(QueryPropertyssMap);
		}
		return expList;
	}

	/**
	 * 
	 * @Description:5获取下载统计数据源
	 * @Author:luojungong
	 * @return
	 */
	private List downCountData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String playTime_start = request.getParameter("playTime_start");
		String playTime_end = request.getParameter("playTime_end");
		String groupid = request.getParameter("groupid");
		String materialId = request.getParameter("materialId");
		logger.info("报表参数 playTime_start" + playTime_start);
		logger.info("报表参数 playTime_end" + playTime_end);
		logger.info("报表参数 groupid" + groupid);
		logger.info("报表参数 materialId" + materialId);

		Map<String, String> QueryPropertyssMap = new HashMap<String, String>();
		if (Utils.isNotEmpty(playTime_start)) {
			QueryPropertyssMap.put("downloadCreateTime_start", playTime_start);
		}
		if (Utils.isNotEmpty(playTime_end)) {
			QueryPropertyssMap.put("downloadCreateTime_end", playTime_end);
		}
		if (Utils.isNotEmpty(groupid)) {
			QueryPropertyssMap.put("groupid", groupid);
		}
		if (Utils.isNotEmpty(materialId)) {
			QueryPropertyssMap.put("materialId", materialId);
		}

		String equipmentIds = "";
		// 通过登录帐号获取 设备id
		Map map = EqX.FindByLoginUid_jsonFmt(request);
		// 是否有设备id
		String isData = String.valueOf(map.get("isData"));
		List expList = null;
		if ("true".equals(isData)) {
			equipmentIds = String.valueOf(map.get("equipmentIds"));
			// 有设备ID 返回为“” 表示为管理员
			if (Utils.isNotEmpty(equipmentIds)) {
				QueryPropertyssMap.put("equipmentIds", equipmentIds);
			}
			expList = expService.downCount(QueryPropertyssMap);
		}
		return expList;
	}
	


	/**
	 * 
	 * @Description:6 发布数据源
	 * @Author:luojungong
	 * @return
	 */
	private List publishData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String equipmentName = request.getParameter("equipmentName");
		String publishType = request.getParameter("publishType");
		String progarmmeName = request.getParameter("progarmmeName");
		String publishStatus = request.getParameter("publishStatus"); 
		String mome = request.getParameter("mome");
		String publishManName = request.getParameter("publishManName");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime"); 
		String groupid = request.getParameter("groupid");

		Map<String, String> QueryPropertyssMap = new HashMap<String, String>();
		if (Utils.isNotEmpty(equipmentName)) {
			QueryPropertyssMap.put("equipmentName", equipmentName);
		}
		if (Utils.isNotEmpty(publishType)) {
			QueryPropertyssMap.put("publishType", publishType);
		}
		if (Utils.isNotEmpty(progarmmeName)) {
			QueryPropertyssMap.put("progarmmeName", progarmmeName);
		}
		if (Utils.isNotEmpty(publishStatus)) {
			QueryPropertyssMap.put("publishStatus", publishStatus);
		}
		if (Utils.isNotEmpty(mome)) {
			QueryPropertyssMap.put("mome", mome);
		}
		if (Utils.isNotEmpty(publishManName)) {
			QueryPropertyssMap.put("publishManName", publishManName);
		}
		if (Utils.isNotEmpty(beginTime)) {
			QueryPropertyssMap.put("beginTime", beginTime);
		}
		if (Utils.isNotEmpty(endTime)) {
			QueryPropertyssMap.put("endTime", endTime);
		}
		if (Utils.isNotEmpty(groupid)) {
			QueryPropertyssMap.put("groupid", groupid);
		}

		String equipmentIds = "";
		// 通过登录帐号获取 设备id
		Map map = EqX.FindByLoginUid_jsonFmt(request);
		// 是否有设备id
		String isData = String.valueOf(map.get("isData"));
		List expList = null;
		if ("true".equals(isData)) {
			equipmentIds = String.valueOf(map.get("equipmentIds"));
			// 有设备ID 返回为“” 表示为管理员
			if (Utils.isNotEmpty(equipmentIds)) {
				QueryPropertyssMap.put("equipmentIds", equipmentIds);
			}
			expList = expService.publish(QueryPropertyssMap);
		}
		return expList;
	}

	/**
	 * 
	 * @Description:7 组织架构
	 * @Author:luojungong
	 * @return
	 */
	private List storeData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String groupname = request.getParameter("groupname");
		String groupid = request.getParameter("groupid"); 
		String parentId = request.getParameter("parentId"); 
		logger.info("报表参数 groupname" + groupname);
		logger.info("报表参数 groupid" + groupid); 
		logger.info("报表参数 parentId" + parentId); 

		Map<String, String> QueryPropertyssMap = new HashMap<String, String>();
		if (Utils.isNotEmpty(groupname)) { 
			try {
				groupname=new String(groupname.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			QueryPropertyssMap.put("groupname", groupname);
		}
		if (Utils.isNotEmpty(groupid)) {
			QueryPropertyssMap.put("groupid", groupid);
		} 
		if (Utils.isNotEmpty(parentId)) {
			QueryPropertyssMap.put("parentId", parentId);
		}
		 
		String grpIds = "";
		try {
			//根据登录回话获取 所属组织架构
			grpIds = grpX.FindByLoginUid_retGrpIds(request);
		} catch (isSuperAdminEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (Utils.isNotEmpty(grpIds)) {
			QueryPropertyssMap.put("grpIds", grpIds);
		}
		 
		List expList = null;
		expList = expService.expStore(QueryPropertyssMap); 
		return expList;
	}
	
	/**
	 * 
	 * @Description:8 播放统计By素材区域
	 * @Author:luojungong
	 * @return 
	 */
	private List playCounByMtrlAreatData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String playTime_start = request.getParameter("playTime_start");
		String playTime_end = request.getParameter("playTime_end");
		String groupid = request.getParameter("groupid");
		String materialId = request.getParameter("materialId"); 
		logger.info("报表参数 playTime_start" + playTime_start);
		logger.info("报表参数 playTime_end" + playTime_end);
		logger.info("报表参数 groupid" + groupid);
		logger.info("报表参数 materialId" + materialId); 

		Map<String, String> QueryPropertyssMap = new HashMap<String, String>();
		if (Utils.isNotEmpty(playTime_start)) {
			QueryPropertyssMap.put("downloadCreateTime_start", playTime_start);
		}
		if (Utils.isNotEmpty(playTime_end)) {
			QueryPropertyssMap.put("downloadCreateTime_end", playTime_end);
		}
		if (Utils.isNotEmpty(groupid)) {
			QueryPropertyssMap.put("groupid", groupid);
		} 
		if (Utils.isNotEmpty(materialId)) {
			QueryPropertyssMap.put("materialId", materialId);
		}
		

		String equipmentIds = "";
		// 通过登录帐号获取 设备id
		Map map = EqX.FindByLoginUid_jsonFmt(request);
		// 是否有设备id
		String isData = String.valueOf(map.get("isData"));
		List expList = null;
		if ("true".equals(isData)) {
			equipmentIds = String.valueOf(map.get("equipmentIds"));
			// 有设备ID 返回为"" 表示为管理员
			if (Utils.isNotEmpty(equipmentIds)) {
				QueryPropertyssMap.put("equipmentIds", equipmentIds);
			}
			expList = expService.playCountByMtrlArea(QueryPropertyssMap);
		}
		return expList;
	}

	
	
	/**
	 * 
	 * @Description:9获取下载统计-门店
	 * @Author:luojungong
	 * @return
	 */
	private List downCountByMtarlBranchData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String playTime_start = request.getParameter("playTime_start");
		String playTime_end = request.getParameter("playTime_end");
		String groupid = request.getParameter("groupid");
		String materialId = request.getParameter("materialId");
		logger.info("报表参数 playTime_start" + playTime_start);
		logger.info("报表参数 playTime_end" + playTime_end);
		logger.info("报表参数 groupid" + groupid);
		logger.info("报表参数 materialId" + materialId);

		Map<String, String> QueryPropertyssMap = new HashMap<String, String>();
		if (Utils.isNotEmpty(playTime_start)) {
			QueryPropertyssMap.put("downloadCreateTime_start", playTime_start);
		}
		if (Utils.isNotEmpty(playTime_end)) {
			QueryPropertyssMap.put("downloadCreateTime_end", playTime_end);
		}
		if (Utils.isNotEmpty(groupid)) {
			QueryPropertyssMap.put("groupid", groupid);
		}
		if (Utils.isNotEmpty(materialId)) {
			QueryPropertyssMap.put("materialId", materialId);
		}

		String equipmentIds = "";
		// 通过登录帐号获取 设备id
		Map map = EqX.FindByLoginUid_jsonFmt(request);
		// 是否有设备id
		String isData = String.valueOf(map.get("isData"));
		List expList = null;
		if ("true".equals(isData)) {
			equipmentIds = String.valueOf(map.get("equipmentIds"));
			// 有设备ID 返回为“” 表示为管理员
			if (Utils.isNotEmpty(equipmentIds)) {
				QueryPropertyssMap.put("equipmentIds", equipmentIds);
			}
			expList = expService.downCountByMtarlBranch(QueryPropertyssMap);
		}
		return expList;
	}
	
	/**
	 * 
	 * @Description:10获取下载统计-门店
	 * @Author:luojungong
	 * @return
	 */
	private List downCountByMtarlStoreData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String playTime_start = request.getParameter("playTime_start");
		String playTime_end = request.getParameter("playTime_end");
		String groupid = request.getParameter("groupid");
		String materialId = request.getParameter("materialId");
		String downloadStatus = request.getParameter("downloadStatus");
		//filex.save_SF(downloadStatus, "c:\\exp\\downCountQueryLog\\aaa.txt");
		logger.info("报表参数 playTime_start" + playTime_start);
		logger.info("报表参数 playTime_end" + playTime_end);
		logger.info("报表参数 groupid" + groupid);
		logger.info("报表参数 materialId" + materialId);
		logger.info("报表参数 downloadStatus" + downloadStatus);
		
		Map<String, String> QueryPropertyssMap = new HashMap<String, String>();
		if (Utils.isNotEmpty(playTime_start)) {
			QueryPropertyssMap.put("downloadCreateTime_start", playTime_start);
		}
		if (Utils.isNotEmpty(playTime_end)) {
			QueryPropertyssMap.put("downloadCreateTime_end", playTime_end);
		}
		if (Utils.isNotEmpty(groupid)) {
			QueryPropertyssMap.put("groupid", groupid);
		}
		if (Utils.isNotEmpty(materialId)) {
			QueryPropertyssMap.put("materialId", materialId);
		}
		if (Utils.isNotEmpty(downloadStatus)) {
			QueryPropertyssMap.put("downloadStatus", downloadStatus);
		}

		String equipmentIds = "";
		// 通过登录帐号获取 设备id
		Map map = EqX.FindByLoginUid_jsonFmt(request);
		// 是否有设备id
		String isData = String.valueOf(map.get("isData"));
		List expList = null;
		if ("true".equals(isData)) {
			equipmentIds = String.valueOf(map.get("equipmentIds"));
			// 有设备ID 返回为“” 表示为管理员
			if (Utils.isNotEmpty(equipmentIds)) {
				QueryPropertyssMap.put("equipmentIds", equipmentIds);
			}
			expList = expService.downCountByMtarlStore(QueryPropertyssMap);
		}
		return expList;
	}
	
	
	
	public IExpService getExpService() {
		return expService;
	}

	public void setExpService(IExpService expService) {
		this.expService = expService;
	}

}
