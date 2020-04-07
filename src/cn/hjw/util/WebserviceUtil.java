package cn.hjw.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hjw.bean.Condition;
import cn.hjw.common.StringUtils;
import cn.hjw.jws.json.AnomalyContentInfo;
import cn.hjw.jws.json.AnomalyInfo;
import cn.hjw.jws.json.BaseJson;
import cn.hjw.jws.json.Category;
import cn.hjw.jws.json.CategoryValue;
import cn.hjw.jws.json.ChargeItem;
import cn.hjw.jws.json.ChargeItemInfo;
import cn.hjw.jws.json.ComboInfo;
import cn.hjw.jws.json.DataDictionaryInfo;
import cn.hjw.jws.json.DepartmentInfo;
import cn.hjw.jws.json.DepartmentType;
import cn.hjw.jws.json.DepartmentTypeOption;
import cn.hjw.jws.json.DeptInfo;
import cn.hjw.jws.json.ErrorType;
import cn.hjw.jws.json.ExamInfo;
import cn.hjw.jws.json.ExamInfo_1;
import cn.hjw.jws.json.ExamInfo_2;
import cn.hjw.jws.json.ExamItem;
import cn.hjw.jws.json.ExaminationItemInfo;
import cn.hjw.jws.json.ItemDetail;
import cn.hjw.model.ChargeItems;
import cn.hjw.model.Combo;
import cn.hjw.model.DataDictionary;
import cn.hjw.model.Department;
import cn.hjw.model.ExamPacsSummaryOption;
import cn.hjw.model.ExamResult;
import cn.hjw.model.ExamSummaryOption;
import cn.hjw.model.ExaminationItem;
import cn.hjw.model.ItemCategory;
import cn.hjw.model.ItemCategory_2;

import com.google.gson.reflect.TypeToken;

public class WebserviceUtil extends BaseUtil {
	public WebserviceUtil() {
		super(WebserviceUtil.class);
	}

	private Map<String, Object> parameterData = null;

	public void setParameterData(Map<String, Object> parameterData) {
		this.parameterData = parameterData;
	}

	private String parameter = null;

	public void setParameterData(String parameter) {
		this.parameter = parameter;
	}

	public BaseJson deleteReportByExamNum() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		ExamInfoUtil examInfoUtil = (ExamInfoUtil) getObject(ExamInfoUtil.class);

		try {
			Object examNumObject = (Object) this.parameterData.get("examNums");
			String centerCode = (String) this.parameterData.get("centerCode");
			if (examNumObject != null) {

				List<String> examNums = (List<String>) examNumObject;

				Map<String, Object> para = new HashMap<String, Object>();
				para.put("examNums", examNums);
				para.put("centerCode", centerCode);
				int r = examInfoUtil.deleteReportByExamNum(para);
				if (r >= 0) {
					data.setRetCode("0");
					data.setRetMsg("删除成功!");
				} else {
					data.setRetCode("1");
					data.setRetMsg("删除失败!");
				}
			}
			/**
			 * Type type = new TypeToken<Department>() { }.getType(); Department
			 * dept = StringUtils.gson.fromJson(this.parameter, type);
			 * dept.setObjectid(StringUtils.getUUID()); dept.setCreateDate(new
			 * Date()); util.insert(dept);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	/**
	 * for 报告对比
	 * 
	 * @return
	 */
	public Map<String, ExamInfo_1> getChargeItemInfo() {
		Map<String, ExamInfo_1> resultMap = new HashMap<String, ExamInfo_1>();
		ExamResultUtil util = (ExamResultUtil) getObject(ExamResultUtil.class);
		try {

			ChargeItem chargeItemBean = null;
			List<ExamItem> chargeItemValues = null;
			// ExamItem examItemBean=null;
			String examNumstr = (String) this.parameterData.get("examNums");
			String centerCode = (String) this.parameterData.get("centerCode");

			String flag = (String) this.parameterData.get("flag");
			ExamInfoUtil examInfoUtil = (ExamInfoUtil) getObject(ExamInfoUtil.class);

			if (StringUtils.isNotEmpty(examNumstr)) {

				String[] examNums = null;
				if (examNumstr.indexOf("-") > -1) {
					examNums = examNumstr.split("-");
				} else {
					examNums = new String[1];
					examNums[0] = examNumstr;
				}

				for (String examNum : examNums) {

					Map<String, Object> para = new HashMap<String, Object>();
					para.put("examNum", examNum);
					para.put("centerCode", centerCode);
					List<?> list = examInfoUtil.selectExamInfos(para);
					if (list != null && !list.isEmpty()) {

						ExamInfo_1 examInfo = new ExamInfo_1();
						List<ExamResult> chargeItems = util.selectChargeItems(para);
						List<ExamResult> checkItems = null;
						Condition condition = null;
						List<ChargeItem> chargeItemBeans = new ArrayList<ChargeItem>();
						for (ExamResult ci : chargeItems) {// 收费项目
							chargeItemBean = (ChargeItem) getObject(ChargeItem.class);
							chargeItemBean.setChargeItemId(StringUtils.notNull(ci.getChargingId()));
							chargeItemBean.setChargeItemName(StringUtils.notNull(ci.getChargeItemName()));

							condition = (Condition) getObject(Condition.class);
							condition.setChargingId(ci.getChargingId());
							condition.setExamNum(examNum);
							condition.setFlag(flag);
							condition.setCenterCode(centerCode);
							checkItems = util.selectCheckItems(condition);
							if (checkItems == null || checkItems.isEmpty()) {
								continue;
							}
							chargeItemValues = new ArrayList<ExamItem>();
							for (ExamResult checkItem : checkItems) {// 检查项目
								chargeItemValues.add(this.getExamItem(checkItem));
							}
							chargeItemBean.setChargeItemValues(chargeItemValues);
							chargeItemBeans.add(chargeItemBean);
						}

						examInfo.setAnomalyData(chargeItemBeans);
						resultMap.put(examNum, examInfo);
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);

		}
		return resultMap;
	}

	/**
	 * 6.12 体检报告--总检结论
	 * 
	 * @return
	 */
	public ExamInfo_1 getExamInfo_1() {
		ExamInfo_1 examInfo = (ExamInfo_1) getObject(ExamInfo_1.class);
		ExamResultUtil util = (ExamResultUtil) getObject(ExamResultUtil.class);
		try {
			log.info("============getExamInfo_1=========");
			List<ChargeItem> chargeItemBeans = new ArrayList<ChargeItem>();
			ChargeItem chargeItemBean = null;
			List<ExamItem> chargeItemValues = null;
			// ExamItem examItemBean=null;
			String examNum = (String) this.parameterData.get("examNum");
			String centerCode = (String) this.parameterData.get("centerCode");

			String flag = (String) this.parameterData.get("flag");
			ExamInfoUtil examInfoUtil = (ExamInfoUtil) getObject(ExamInfoUtil.class);

			Map<String, Object> para = new HashMap<String, Object>();
			para.put("examNum", examNum);
			para.put("centerCode", centerCode);
			List<?> list = examInfoUtil.selectExamInfos(para);
			if (list == null || list.isEmpty()) {
				examInfo.setRetMsg("体检报告不存在!");
				examInfo.setAnomalyData(chargeItemBeans);
				return examInfo;

			}

			List<ExamResult> chargeItems = util.selectChargeItems(para);

			List<ExamResult> checkItems = null;
			Condition condition = null;
			for (ExamResult ci : chargeItems) {// 收费项目
				chargeItemBean = (ChargeItem) getObject(ChargeItem.class);
				chargeItemBean.setChargeItemId(StringUtils.notNull(ci.getChargingId()));
				chargeItemBean.setChargeItemName(StringUtils.notNull(ci.getChargeItemName()));

				condition = (Condition) getObject(Condition.class);
				condition.setChargingId(ci.getChargingId());
				condition.setExamNum(examNum);
				condition.setFlag(flag);
				condition.setCenterCode(centerCode);
				checkItems = util.selectCheckItems(condition);
				if (checkItems == null || checkItems.isEmpty()) {
					continue;
				}
				chargeItemValues = new ArrayList<ExamItem>();
				for (ExamResult checkItem : checkItems) {// 检查项目
					chargeItemValues.add(this.getExamItem(checkItem));
				}
				chargeItemBean.setChargeItemValues(chargeItemValues);
				chargeItemBeans.add(chargeItemBean);
			}

			examInfo.setAnomalyData(chargeItemBeans);

			List<ExamSummaryOption> examSummarys = util.selectExamSummary(para);
			List<Map<String, String>> resultValues = new ArrayList<Map<String, String>>();
			Map<String, String> summaryMap = null;
			String code = "";
			String resultName = "";
			String resultContent = "";
			boolean isDiff = false;
			int index = 0;
			for (ExamSummaryOption summary : examSummarys) {
				index++;
				if (examSummarys.size() == 1) {
					summaryMap = new HashMap<String, String>();
					summaryMap.put("resultName", StringUtils.notNull(summary.getName()));
					summaryMap.put("resultContent", StringUtils.notNull(summary.getContent()));
					resultValues.add(summaryMap);
				} else {
					if (code.length() == 0) {
						resultName = StringUtils.notNull(summary.getName());
						resultContent = StringUtils.notNull(summary.getContent()) + "\n";
					} else {
						if (code.equals(summary.getCode())) {
							resultContent += StringUtils.notNull(summary.getContent()) + "\n";
						} else {
							isDiff = true;
						}
					}
					if (isDiff) {
						summaryMap = new HashMap<String, String>();
						summaryMap.put("resultName", resultName);
						summaryMap.put("resultContent", resultContent);
						resultValues.add(summaryMap);

						resultName = StringUtils.notNull(summary.getName());
						resultContent = StringUtils.notNull(summary.getContent()) + "\n";
						isDiff = false;
					}

					code = summary.getCode();
				}
				if (index == examSummarys.size()) {
					summaryMap = new HashMap<String, String>();
					summaryMap.put("resultName", resultName);
					summaryMap.put("resultContent", resultContent);
					resultValues.add(summaryMap);
				}

				/**
				 * summaryMap=new HashMap<String,String>();
				 * summaryMap.put("resultName",
				 * StringUtils.notNull(summary.getName()));
				 * summaryMap.put("resultContent",
				 * StringUtils.notNull(summary.getContent()));
				 * resultValues.add(summaryMap);
				 */

			}
			/**
			 * String suggest = ""; String summary = ""; if (examSummary != null
			 * && !examSummary.isEmpty()) { summary =
			 * examSummary.get(0).getFinalExamResult(); for (ExamResult e :
			 * examSummary) { if (!StringUtils.isEmpty(e.getSuggest())) {
			 * suggest += e.getSuggest() + "\n\n"; }
			 * 
			 * } } examInfo.setExamSuggest(suggest);
			 * examInfo.setExamSummary(summary);
			 */
			examInfo.setResultValues(resultValues);

			examInfo.setRetCode("0");
			examInfo.setRetMsg("获取成功!");

			log.info("============getExamInfo_2=========");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			examInfo.setRetMsg("系统异常!");
		}
		return examInfo;
	}

	/**
	 * 6.13 体检报告--报告详情
	 * 
	 * @return
	 */
	public ExamInfo_2 getExamInfo_2() {
		ExamInfo_2 examInfo = (ExamInfo_2) getObject(ExamInfo_2.class);
		ExamResultUtil util = (ExamResultUtil) getObject(ExamResultUtil.class);
		try {
			List<DeptInfo> deptInfoBeans = new ArrayList<DeptInfo>();
			DeptInfo deptInfoBean = null;
			List<ChargeItem> chargeItemBeans = null;

			// ExamItem examItemBean=null;
			String examNum = (String) this.parameterData.get("examNum");
			String centerCode = (String) this.parameterData.get("centerCode");
			String ispacs = (String) this.parameterData.get("ispacs");

			ExamInfoUtil examInfoUtil = (ExamInfoUtil) getObject(ExamInfoUtil.class);
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("examNum", examNum);
			para.put("centerCode", centerCode);
			List<?> list = examInfoUtil.selectExamInfos(para);
			if (list == null || list.isEmpty()) {
				examInfo.setRetMsg("体检报告不存在!");
				examInfo.setData(deptInfoBeans);
				return examInfo;

			}
			ChargeItem chargeItemBean = null;
			List<ExamItem> chargeItemValues = null;
			List<ExamResult> examDeptInfoSummary = util.selectDeptInfo(para);
			Condition condition = null;
			List<ExamResult> checkItems = null;
			for (ExamResult dept : examDeptInfoSummary) {// 科室
				chargeItemBeans = new ArrayList<ChargeItem>();
				deptInfoBean = (DeptInfo) getObject(DeptInfo.class);
				deptInfoBean.setDepId(StringUtils.notNull(dept.getDepId()));
				deptInfoBean.setDepName(StringUtils.notNull(dept.getDepName()));
				deptInfoBean.setDeptType(StringUtils.notNull(dept.getDeptType()));
				deptInfoBean.setDepSummary(StringUtils.notNull(dept.getSuggest()));
				condition = (Condition) getObject(Condition.class);
				condition.setDepId(deptInfoBean.getDepId());
				condition.setExamNum(examNum);
				condition.setCenterCode(centerCode);
				List<ExamResult> chargeItems = util.selectDeptChargeItems(condition);
				if (chargeItems == null || chargeItems.isEmpty()) {
					continue;
				}
				for (ExamResult ci : chargeItems) {// 收费项目
					chargeItemBean = (ChargeItem) getObject(ChargeItem.class);
					chargeItemBean.setChargeItemId(StringUtils.notNull(ci.getChargingId()));
					chargeItemBean.setChargeItemName(StringUtils.notNull(ci.getChargeItemName()));
					chargeItemBean.setExamDoctor(StringUtils.notNull(ci.getExamDoctor()));
					chargeItemBean.setApprover(StringUtils.notNull(ci.getApprover()));

					condition = (Condition) getObject(Condition.class);
					condition.setChargingId(ci.getChargingId());
					condition.setExamNum(examNum);
					condition.setDepId(deptInfoBean.getDepId());
					condition.setCenterCode(centerCode);
					ExamPacsSummaryOption cons = (ExamPacsSummaryOption) getObject(ExamPacsSummaryOption.class);
					cons.setChargingId(ci.getChargingId());
					cons.setDeptCode(deptInfoBean.getDepId());
					cons.setCenterCode(centerCode);
					cons.setExamNum(examNum);
					if (ispacs != null && ispacs.equals("3")) {
						if (StringUtils.isNotEmpty(cons.getChargingId()) && StringUtils.isNotEmpty(cons.getDeptCode())
								&& StringUtils.isNotEmpty(cons.getCenterCode())
								&& StringUtils.isNotEmpty(cons.getExamNum())) {

							List<ExamPacsSummaryOption> examPacsSummaryOptions = util.selectExamPacsSummaryOption(cons);
							if (examPacsSummaryOptions != null && !examPacsSummaryOptions.isEmpty()) {
								chargeItemValues = new ArrayList<ExamItem>();
								for (ExamPacsSummaryOption checkItem : examPacsSummaryOptions) {
									chargeItemValues.add(this.getExamItem(checkItem));
								}
							}
						}
					} else if (ispacs != null && (ispacs.equals("1") || ispacs.equals("2"))) {
						checkItems = util.selectDeptCheckItems(condition);
						if (checkItems == null || checkItems.isEmpty()) {
							continue;
						}
						chargeItemValues = new ArrayList<ExamItem>();
						for (ExamResult checkItem : checkItems) {// 检查项目
							chargeItemValues.add(this.getExamItem(checkItem));

						}
					} else {
						List<ExamPacsSummaryOption> examPacsSummaryOptions = util.selectExamPacsSummaryOption(cons);
						if (examPacsSummaryOptions != null && !examPacsSummaryOptions.isEmpty()) {
							chargeItemValues = new ArrayList<ExamItem>();
							for (ExamPacsSummaryOption checkItem : examPacsSummaryOptions) {
								chargeItemValues.add(this.getExamItem(checkItem));
							}
						} else {

							checkItems = util.selectDeptCheckItems(condition);
							if (checkItems == null || checkItems.isEmpty()) {
								continue;
							}
							chargeItemValues = new ArrayList<ExamItem>();
							for (ExamResult checkItem : checkItems) {// 检查项目
								chargeItemValues.add(this.getExamItem(checkItem));

							}

						}
					}
					if (chargeItemValues == null || chargeItemValues.isEmpty()) {
						continue;
					}
					chargeItemBean.setChargeItemValues(chargeItemValues);
					chargeItemBeans.add(chargeItemBean);
				}
				if (chargeItemBeans == null || chargeItemBeans.isEmpty()) {
					continue;
				}
				deptInfoBean.setDepValues(chargeItemBeans);
				deptInfoBeans.add(deptInfoBean);
			}
			examInfo.setData(deptInfoBeans);
			examInfo.setRetCode("0");
			examInfo.setRetMsg("获取成功!");

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			examInfo.setRetMsg("系统异常!");
		}
		return examInfo;
	}

	/**
	 * 总检结论 /体检报告报告详情 for web
	 * 
	 * @return
	 */
	public Map<String, Object> getExamInfo() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("examSummary", this.getExamSummary());
		// this.parameterData.put("ispacs", "1");

		// this.parameterData.put("ispacs", "3");

		ExamInfo_2 examInfo = this.getExamInfo_2();
		result.put("examInfo", examInfo);
		/**
		 * List<ChargeItem> chargeItems=new ArrayList<ChargeItem>(); List
		 * <ChargeItem> chargeItems2=new ArrayList<ChargeItem>(); List
		 * <DeptInfo> deptInfos=examInfo.getData(); for(DeptInfo d:deptInfos){
		 * List<ChargeItem> depValues=d.getDepValues();
		 * if(depValues!=null&&!depValues.isEmpty()){ for(ChargeItem
		 * c:depValues){ List<ExamItem> examItems=c.getChargeItemValues();
		 * if(examItems!=null&&!examItems.isEmpty()){
		 * if("3".equals(examItems.get(0).getExamItemType())){
		 * chargeItems2.add(c); } else{ chargeItems.add(c); } }
		 * 
		 * }
		 * 
		 * } } result.put("examInfo", chargeItems); result.put("examInfo2",
		 * chargeItems2);
		 */
		return result;
	}

	/**
	 * 总检结论 for web
	 * 
	 * @return
	 */
	private ExamInfo_1 getExamSummary() {
		ExamInfo_1 examInfo = (ExamInfo_1) getObject(ExamInfo_1.class);
		ExamResultUtil util = (ExamResultUtil) getObject(ExamResultUtil.class);
		try {

			// ExamItem examItemBean=null;
			String examNum = (String) this.parameterData.get("examNum");
			String centerCode = (String) this.parameterData.get("centerCode");

			ExamInfoUtil examInfoUtil = (ExamInfoUtil) getObject(ExamInfoUtil.class);

			Map<String, Object> para = new HashMap<String, Object>();
			para.put("examNum", examNum);
			para.put("centerCode", centerCode);
			List<?> list = examInfoUtil.selectExamInfos(para);
			if (list == null || list.isEmpty()) {
				examInfo.setRetMsg("体检报告不存在!");
				examInfo.setAnomalyData(new ArrayList<ChargeItem>());
				return examInfo;

			}

			List<ExamSummaryOption> examSummarys = util.selectExamSummary(para);
			List<Map<String, String>> resultValues = new ArrayList<Map<String, String>>();
			Map<String, String> summaryMap = null;
			String code = "";
			String resultName = "";
			String resultContent = "";
			boolean isDiff = false;
			int index = 0;
			for (ExamSummaryOption summary : examSummarys) {
				index++;
				if (examSummarys.size() == 1) {
					summaryMap = new HashMap<String, String>();
					summaryMap.put("resultName", StringUtils.notNull(summary.getName()));
					summaryMap.put("resultContent", StringUtils.notNull(summary.getContent()));
					resultValues.add(summaryMap);
				} else {
					if (code.length() == 0) {
						resultName = StringUtils.notNull(summary.getName());
						resultContent = StringUtils.notNull(summary.getContent()) + "\n";
					} else {
						if (code.equals(summary.getCode())) {
							resultContent += StringUtils.notNull(summary.getContent()) + "\n";
							if("体检建议".equals(resultName)) {
								resultContent += "\n";
							}
						} else {
							isDiff = true;
						}
					}
					if (isDiff) {
						summaryMap = new HashMap<String, String>();
						summaryMap.put("resultName", resultName);
						summaryMap.put("resultContent", resultContent);
						resultValues.add(summaryMap);

						resultName = StringUtils.notNull(summary.getName());
						resultContent = StringUtils.notNull(summary.getContent()) + "\n";
						if("体检建议".equals(resultName)) {
							resultContent += "\n";
						}
						isDiff = false;
					}

					code = summary.getCode();
				}
				if (index == examSummarys.size()) {
					summaryMap = new HashMap<String, String>();
					summaryMap.put("resultName", resultName);
					summaryMap.put("resultContent", resultContent);
					resultValues.add(summaryMap);
				}

			}

			examInfo.setResultValues(resultValues);

			examInfo.setRetCode("0");
			examInfo.setRetMsg("获取成功!");

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			examInfo.setRetMsg("系统异常!");
		}
		return examInfo;
	}

	/**
	 * 6.14 体检报告--指标详情
	 * 
	 * @return
	 */
	public ItemDetail itemDetail() {
		ItemDetail itemDetail = (ItemDetail) getObject(ItemDetail.class);
		List<ErrorType> anomalyTypes = new ArrayList<ErrorType>();
		itemDetail.setAnomalyTypes(anomalyTypes);
		ExaminationItemUtil util = (ExaminationItemUtil) getObject(ExaminationItemUtil.class);
		try {
			String itemId = (String) this.parameterData.get("itemId");

			ExaminationItem examinationItem = util.selectExaminationItems(itemId);
			if (examinationItem != null) {
				itemDetail.setAbbr(StringUtils.notNull(examinationItem.getItemEngName()));
				itemDetail.setDepName(StringUtils.notNull(examinationItem.getDepName()));
				itemDetail.setItemDesc(StringUtils.notNull(examinationItem.getItemDescription()));
				itemDetail.setItemName(StringUtils.notNull(examinationItem.getItemName()));
				itemDetail.setRefImg(StringUtils.notNull(examinationItem.getRefImg()));

				itemId = examinationItem.getObjectId();
				List<ItemCategory> categoryTypes = util.selectAnomalyTypes(itemId);
				anomalyTypes = new ArrayList<ErrorType>();
				ErrorType errorType = null;
				ItemCategory condition = null;
				for (ItemCategory t : categoryTypes) {
					errorType = (ErrorType) getObject(ErrorType.class);
					errorType.setTypeName(t.getAnomalyTypeName());
					condition = (ItemCategory) getObject(ItemCategory.class);
					condition.setItemId(itemId);
					condition.setAnomalyType(t.getAnomalyType());
					List<ItemCategory> categoryies = util.selectCategories(condition);
					List<Category> categoryList = new ArrayList<Category>();
					Category cc = null;
					for (ItemCategory c : categoryies) {
						cc = (Category) getObject(Category.class);
						cc.setCategoryIcon(c.getCategoryIcon());
						cc.setCategoryName(c.getCategoryName());

						List<ItemCategory> categoryValues = util.selectCategoryValues(c.getObjectId());
						List<CategoryValue> categoryValueList = new ArrayList<CategoryValue>();
						CategoryValue value = null;
						for (ItemCategory v : categoryValues) {
							value = (CategoryValue) getObject(CategoryValue.class);
							value.setContent(v.getContent());
							value.setTitle(v.getTitle());
							categoryValueList.add(value);
						}
						cc.setCategoryValues(categoryValueList);
						categoryList.add(cc);
					}
					errorType.setCategories(categoryList);
					anomalyTypes.add(errorType);
				}
				itemDetail.setAnomalyTypes(anomalyTypes);
				itemDetail.setRetCode("0");
				itemDetail.setRetMsg("获取成功!");
			} else {
				itemDetail.setRetMsg("该检查项目不存在!");

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			itemDetail.setRetMsg("系统异常!");
		}
		return itemDetail;
	}

	// /中心平台-体检中心
	// /==============科室管理 start===================
	/**
	 * 获得科室类型
	 * 
	 * @return
	 */
	public DepartmentType departmentTypeList() {
		DepartmentType data = new DepartmentType();
		List<DepartmentTypeOption> datas = new ArrayList<DepartmentTypeOption>();
		DepartmentTypeUtil util = (DepartmentTypeUtil) getObject(DepartmentTypeUtil.class);
		try {
			List<cn.hjw.model.DepartmentType> list = util.selectDepartmentType();
			DepartmentTypeOption dto = null;
			for (cn.hjw.model.DepartmentType d : list) {
				dto = new DepartmentTypeOption();
				dto.setCode(StringUtils.trim(d.getTypeCode()));
				dto.setName(StringUtils.trim(d.getName()));
				dto.setObjectid(d.getObjectid());
				datas.add(dto);
			}
			data.setDatas(datas);
			data.setRetCode("0");
			data.setRetMsg("获取成功!");

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	/**
	 * 科室列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DepartmentInfo departList() {

		DepartmentInfo deptinfo = (DepartmentInfo) getObject(DepartmentInfo.class);
		List<Department> list = null;
		DepartmentUtil util = (DepartmentUtil) getObject(DepartmentUtil.class);
		try {
			String deptCode = (String) this.parameterData.get("deptCode");
			String deptName = (String) this.parameterData.get("deptName");
			String deptType = (String) this.parameterData.get("deptType");
			Object pageNumO = this.parameterData.get("pageNum");
			Object pageSizeO = this.parameterData.get("pageSize");

			if (!StringUtils.isEmpty(((String) pageNumO)) && !StringUtils.isEmpty(((String) pageSizeO))) {
				Integer pageNum = Integer.valueOf((String) pageNumO);
				Integer pageSize = Integer.valueOf((String) pageSizeO);
				Department dept = (Department) getObject(Department.class);
				dept.setDeptName(deptName);
				dept.setDeptType(deptType);
				dept.setDeptCode(deptCode);
				list = (List<Department>) util.selectByCondition(dept, pageNum, pageSize);
				if (list != null && !list.isEmpty()) {
					deptinfo.setDatas(list);
					deptinfo.setRetCount("" + util.getTotalCount());
				} else {
					deptinfo.setRetCount("0");
				}
				deptinfo.setRetMsg("取得成功！");
				deptinfo.setRetCode("0");

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			deptinfo.setRetCode("1");
			deptinfo.setRetMsg("系统异常!");

		}
		return deptinfo;
	}

	/**
	 * 新增科室
	 * 
	 * @return
	 */
	public BaseJson insertDept() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		DepartmentUtil util = (DepartmentUtil) getObject(DepartmentUtil.class);
		try {
			Type type = new TypeToken<Department>() {
			}.getType();
			Department dept = StringUtils.gson.fromJson(this.parameter, type);
			dept.setObjectid(StringUtils.getUUID());
			dept.setCreateDate(new Date());
			util.insert(dept);
			data.setRetCode("0");
			data.setRetMsg("科室添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	/**
	 * 编辑科室
	 * 
	 * @return
	 */
	public BaseJson updateDept() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		DepartmentUtil util = (DepartmentUtil) getObject(DepartmentUtil.class);
		try {
			Type type = new TypeToken<Department>() {
			}.getType();
			Department dept = StringUtils.gson.fromJson(this.parameter, type);
			dept.setUpdateDate(new Date());
			util.update(dept);
			data.setRetCode("0");
			data.setRetMsg("科室修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	/**
	 * 删除科室
	 * 
	 * @return
	 */
	public BaseJson deleteDept() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		DepartmentUtil util = (DepartmentUtil) getObject(DepartmentUtil.class);
		try {
			Type type = new TypeToken<List<String>>() {
			}.getType();
			List<String> objectids = StringUtils.gson.fromJson(this.parameter, type);
			util.delete(objectids);
			data.setRetCode("0");
			data.setRetMsg("科室删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	// /==============科室管理 end===================
	private BaseJson insert(Class<?> cls, Object o, BaseJson data, String message) {
		UtilIterface util = (UtilIterface) getObject(cls);
		try {
			util.insert(o);
			data.setRetCode("0");
			data.setRetMsg(message);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	// /==============收费项目管理 start===================
	/**
	 * 收费项目列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ChargeItemInfo chargeItemList() {

		ChargeItemInfo chargeItemInfo = (ChargeItemInfo) getObject(ChargeItemInfo.class);
		List<ChargeItems> list = null;
		UtilIterface util = (ChargeItemUtil) getObject(ChargeItemUtil.class);
		try {
			String itemCode = (String) this.parameterData.get("itemCode");
			String itemName = (String) this.parameterData.get("itemName");

			Object pageNumO = this.parameterData.get("pageNum");
			Object pageSizeO = this.parameterData.get("pageSize");
			if (!StringUtils.isEmpty(((String) pageNumO)) && !StringUtils.isEmpty(((String) pageSizeO))) {
				Integer pageNum = Integer.valueOf((String) pageNumO);
				Integer pageSize = Integer.valueOf((String) pageSizeO);
				ChargeItems chargeItem = (ChargeItems) getObject(ChargeItems.class);
				chargeItem.setItemCode(itemCode);
				chargeItem.setItemName(itemName);
				list = (List<ChargeItems>) util.selectByCondition(chargeItem, pageNum, pageSize);
				if (list != null && !list.isEmpty()) {
					chargeItemInfo.setDatas(list);
					chargeItemInfo.setRetCount("" + util.getTotalCount());
				} else {
					chargeItemInfo.setRetCount("0");
				}
				chargeItemInfo.setRetMsg("取得成功！");
				chargeItemInfo.setRetCode("0");

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			chargeItemInfo.setRetCode("1");
			chargeItemInfo.setRetMsg("系统异常!");

		}
		return chargeItemInfo;
	}

	public BaseJson insertChargeItem() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		try {
			Type type = new TypeToken<ChargeItems>() {
			}.getType();
			ChargeItems chargeItem = StringUtils.gson.fromJson(this.parameter, type);
			chargeItem.setCreateDate(new Date());
			chargeItem.setObjectid(StringUtils.getUUID());
			return insert(ChargeItemUtil.class, chargeItem, data, "收费项目添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	public BaseJson updateChargeItem() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		ChargeItemUtil util = (ChargeItemUtil) getObject(ChargeItemUtil.class);
		try {

			Type type = new TypeToken<ChargeItems>() {
			}.getType();
			ChargeItems chargeItem = StringUtils.gson.fromJson(this.parameter, type);
			chargeItem.setUpdateDate(new Date());
			util.update(chargeItem);
			data.setRetCode("0");
			data.setRetMsg("收费项目修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	public BaseJson deleteChargeItem() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		ChargeItemUtil util = (ChargeItemUtil) getObject(ChargeItemUtil.class);
		try {
			Type type = new TypeToken<List<String>>() {
			}.getType();
			List<String> objectids = StringUtils.gson.fromJson(this.parameter, type);
			util.delete(objectids);
			data.setRetCode("0");
			data.setRetMsg("收费项目删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	// /==============收费项目管理 end===================

	// /==============数字字典 start===================
	@SuppressWarnings("unchecked")
	public DataDictionaryInfo dataDictionary() {

		DataDictionaryInfo dataDictionaryInfo = (DataDictionaryInfo) getObject(DataDictionaryInfo.class);
		List<DataDictionary> list = null;
		DataDictionaryUtil util = (DataDictionaryUtil) getObject(DataDictionaryUtil.class);
		try {
			String dataCode = (String) this.parameterData.get("dataCode");
			Integer pageNum = Integer.valueOf((String) this.parameterData.get("pageNum"));
			Integer pageSize = Integer.valueOf((String) this.parameterData.get("pageSize"));

			DataDictionary condition = (DataDictionary) getObject(DataDictionary.class);
			condition.setDataCode(dataCode);
			list = (List<DataDictionary>) util.selectByCondition(condition, pageNum, pageSize);
			if (list != null && !list.isEmpty()) {
				dataDictionaryInfo.setDatas(list);
				dataDictionaryInfo.setRetCount("" + util.getTotalCount());
			} else {
				dataDictionaryInfo.setRetCount("0");
			}
			dataDictionaryInfo.setRetMsg("取得成功！");
			dataDictionaryInfo.setRetCode("0");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			dataDictionaryInfo.setRetCode("1");
			dataDictionaryInfo.setRetMsg("系统异常!");

		}
		return dataDictionaryInfo;
	}
	// /==============数字字典 end===================

	// /==============检查项目管理 start===================
	@SuppressWarnings("unchecked")
	public ExaminationItemInfo examinationItemList() {
		ExaminationItemInfo examinationItemInfo = (ExaminationItemInfo) getObject(ExaminationItemInfo.class);
		List<ExaminationItem> list = null;
		ExaminationItemUtil util = (ExaminationItemUtil) getObject(ExaminationItemUtil.class);
		try {
			String itemNum = (String) this.parameterData.get("itemNum");
			String itemName = (String) this.parameterData.get("itemName");
			Object pageNumO = this.parameterData.get("pageNum");
			Object pageSizeO = this.parameterData.get("pageSize");
			if (!StringUtils.isEmpty(((String) pageNumO)) && !StringUtils.isEmpty(((String) pageSizeO))) {
				Integer pageNum = Integer.valueOf((String) pageNumO);
				Integer pageSize = Integer.valueOf((String) pageSizeO);
				ExaminationItem examinationItem = (ExaminationItem) getObject(ExaminationItem.class);
				examinationItem.setItemNum(itemNum);
				examinationItem.setItemName(itemName);
				list = (List<ExaminationItem>) util.selectByCondition(examinationItem, pageNum, pageSize);
				if (list != null && !list.isEmpty()) {
					examinationItemInfo.setDatas(list);
					examinationItemInfo.setRetCount("" + util.getTotalCount());
				} else {
					examinationItemInfo.setRetCount("0");
				}
				examinationItemInfo.setRetMsg("取得成功！");
				examinationItemInfo.setRetCode("0");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			examinationItemInfo.setRetCode("1");
			examinationItemInfo.setRetMsg("系统异常!");

		}
		return examinationItemInfo;
	}

	public BaseJson insertExaminationItem() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		ExaminationItemUtil util = (ExaminationItemUtil) getObject(ExaminationItemUtil.class);
		try {
			Type type = new TypeToken<ExaminationItem>() {
			}.getType();
			ExaminationItem examinationItem = StringUtils.gson.fromJson(this.parameter, type);
			examinationItem.setCreateDate(new Date());
			examinationItem.setObjectId(StringUtils.getUUID());
			util.insert(examinationItem);
			data.setRetCode("0");
			data.setRetMsg("检查项目添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	public BaseJson updateExaminationItem() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		ExaminationItemUtil util = (ExaminationItemUtil) getObject(ExaminationItemUtil.class);
		try {

			Type type = new TypeToken<ExaminationItem>() {
			}.getType();
			ExaminationItem examinationItem = StringUtils.gson.fromJson(this.parameter, type);
			examinationItem.setUpdateDate(new Date());
			util.update(examinationItem);
			data.setRetCode("0");
			data.setRetMsg("检查项目修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	public BaseJson deleteExaminationItem() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		ExaminationItemUtil util = (ExaminationItemUtil) getObject(ExaminationItemUtil.class);
		try {
			Type type = new TypeToken<List<String>>() {
			}.getType();
			List<String> objectids = StringUtils.gson.fromJson(this.parameter, type);
			util.delete(objectids);
			data.setRetCode("0");
			data.setRetMsg("检查项目删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	// /==============检查项目管理 end===================
	@SuppressWarnings("unchecked")
	public ComboInfo packagesFilter() {
		ComboInfo comboInfo = (ComboInfo) getObject(ComboInfo.class);
		List<Combo> list = null;
		ComboUtil util = (ComboUtil) getObject(ComboUtil.class);
		try {
			String pType = (String) this.parameterData.get("pType");
			String pSort = (String) this.parameterData.get("pSort");
			String pFilter = (String) this.parameterData.get("pFilter");
			Object pageNumO = this.parameterData.get("pageNum");
			Object pageSizeO = this.parameterData.get("pageSize");
			if (!StringUtils.isEmpty(((String) pageNumO)) && !StringUtils.isEmpty(((String) pageSizeO))) {
				Integer pageNum = Integer.valueOf((String) pageNumO);
				Integer pageSize = Integer.valueOf((String) pageSizeO);

				Map<String, Object> condition = new HashMap<String, Object>();
				String sortString = "";
				String sex = "";
				String amountedF = "";
				String amountedT = "";
				if (pFilter.equals("1")) {
					sex = "男";
				} else if (pFilter.equals("2")) {
					sex = "女";
				} else if (pFilter.equals("3")) {
					amountedT = "1000";
				} else if (pFilter.equals("4")) {
					amountedF = "1000";
					amountedT = "2000";
				} else if (pFilter.equals("5")) {
					amountedF = "2000";
				}

				if (pSort.equals("0")) {
					sortString = "create_date.desc";
				} else if (pSort.equals("1")) {
					sortString = "amounted.asc";
				} else if (pSort.equals("2")) {
					sortString = "amounted.desc";
				} else if (pSort.equals("3")) {
					sortString = "";

				} else if (pSort.equals("4")) {
					sortString = "discount.asc";
				}

				condition.put("sex", sex);
				condition.put("amountedF", amountedF);
				condition.put("amountedT", amountedT);
				condition.put("comboType", pType);

				list = (List<Combo>) util.selectByCondition(condition, sortString, pageNum, pageSize);
				if (list != null && !list.isEmpty()) {
					comboInfo.setDatas(list);
					comboInfo.setRetCount("" + util.getTotalCount());
				} else {
					comboInfo.setRetCount("0");
				}
				comboInfo.setRetMsg("取得成功！");
				comboInfo.setRetCode("0");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			comboInfo.setRetCode("1");
			comboInfo.setRetMsg("系统异常!");

		}
		return comboInfo;
	}

	@SuppressWarnings("unchecked")
	public ComboInfo comboList() {
		ComboInfo comboInfo = (ComboInfo) getObject(ComboInfo.class);
		List<Combo> list = null;
		ComboUtil util = (ComboUtil) getObject(ComboUtil.class);
		try {
			String name = (String) this.parameterData.get("name");
			String dateFrom = (String) this.parameterData.get("dateFrom");
			String dateTo = (String) this.parameterData.get("dateTo");
			String dateTwoFrom = (String) this.parameterData.get("dateTwoFrom");
			String dateTwoTo = (String) this.parameterData.get("dateTwoTo");

			Object pageNumO = this.parameterData.get("pageNum");
			Object pageSizeO = this.parameterData.get("pageSize");
			if (!StringUtils.isEmpty(((String) pageNumO)) && !StringUtils.isEmpty(((String) pageSizeO))) {
				Integer pageNum = Integer.valueOf((String) pageNumO);
				Integer pageSize = Integer.valueOf((String) pageSizeO);
				Condition condition = (Condition) getObject(Condition.class);
				condition.setName(name);
				condition.setDateFrom(dateFrom);
				condition.setDateTo(dateTo);
				condition.setDateTwoFrom(dateTwoFrom);
				condition.setDateTwoTo(dateTwoTo);

				list = (List<Combo>) util.selectByCondition(condition, pageNum, pageSize);
				if (list != null && !list.isEmpty()) {
					comboInfo.setDatas(list);
					comboInfo.setRetCount("" + util.getTotalCount());
				} else {
					comboInfo.setRetCount("0");
				}
				comboInfo.setRetMsg("取得成功！");
				comboInfo.setRetCode("0");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			comboInfo.setRetCode("1");
			comboInfo.setRetMsg("系统异常!");

		}
		return comboInfo;
	}

	public BaseJson insertCombo() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		ComboUtil util = (ComboUtil) getObject(ComboUtil.class);
		try {
			Type type = new TypeToken<Combo>() {
			}.getType();
			Combo combo = StringUtils.gson.fromJson(this.parameter, type);
			combo.setCreateDate(new Date());
			combo.setObjectid(StringUtils.getUUID());
			util.insert(combo);
			data.setRetCode("0");
			data.setRetMsg("套餐添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	public BaseJson updateCombo() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		ComboUtil util = (ComboUtil) getObject(ComboUtil.class);
		try {

			Type type = new TypeToken<Combo>() {
			}.getType();
			Combo combo = StringUtils.gson.fromJson(this.parameter, type);
			combo.setUpdateDate(new Date());
			util.update(combo);
			data.setRetCode("0");
			data.setRetMsg("套餐修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	public BaseJson deleteCombo() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		ComboUtil util = (ComboUtil) getObject(ComboUtil.class);
		try {
			Type type = new TypeToken<List<String>>() {
			}.getType();
			List<String> objectids = StringUtils.gson.fromJson(this.parameter, type);
			util.delete(objectids);
			data.setRetCode("0");
			data.setRetMsg("套餐删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	public Object getDataById() {

		try {
			String flag = (String) this.parameterData.get("flag");
			String objectid = (String) this.parameterData.get("objectid");
			if ("1".equals(flag)) {// 科室
				DepartmentInfo deptinfo = (DepartmentInfo) getObject(DepartmentInfo.class);
				DepartmentUtil util = (DepartmentUtil) getObject(DepartmentUtil.class);
				Department department = util.selectByPrimaryKey(objectid);
				deptinfo.setData(department);
				deptinfo.setRetMsg("取得成功！");
				deptinfo.setRetCode("0");
				return deptinfo;
			}
			if ("2".equals(flag)) {// 收费项目
				ChargeItemInfo chargeItemInfo = (ChargeItemInfo) getObject(ChargeItemInfo.class);
				UtilIterface util = (ChargeItemUtil) getObject(ChargeItemUtil.class);
				Object o = util.selectByPrimaryKey(objectid);
				if (o != null) {
					chargeItemInfo.setData((ChargeItems) o);
				}
				chargeItemInfo.setRetCode("0");
				chargeItemInfo.setRetMsg("取得成功！");
				return chargeItemInfo;
			}
			if ("3".equals(flag)) {// 检查项目
				ExaminationItemInfo examinationItemInfo = (ExaminationItemInfo) getObject(ExaminationItemInfo.class);
				UtilIterface util = (ExaminationItemUtil) getObject(ExaminationItemUtil.class);
				Object o = util.selectByPrimaryKey(objectid);
				if (o != null) {
					examinationItemInfo.setData((ExaminationItem) o);
				}
				examinationItemInfo.setRetCode("0");
				examinationItemInfo.setRetMsg("取得成功！");
				return examinationItemInfo;
			}
			if ("4".equals(flag)) {// 套餐
				ComboInfo chargeItemInfo = (ComboInfo) getObject(ComboInfo.class);
				UtilIterface util = (ComboUtil) getObject(ComboUtil.class);
				Object o = util.selectByPrimaryKey(objectid);
				if (o != null) {
					chargeItemInfo.setData((Combo) o);
				}
				chargeItemInfo.setRetCode("0");
				chargeItemInfo.setRetMsg("取得成功！");
				return chargeItemInfo;
			}
			if ("7".equals(flag)) {// 获取套餐详情
				ComboInfo chargeItemInfo = (ComboInfo) getObject(ComboInfo.class);
				ComboUtil util = (ComboUtil) getObject(ComboUtil.class);
				String itemCategory = (String) this.parameterData.get("itemCategory");
				Object o = util.selectByPrimaryKey(objectid, itemCategory);
				if (o != null) {
					chargeItemInfo.setData((Combo) o);
				}
				chargeItemInfo.setRetCode("0");
				chargeItemInfo.setRetMsg("取得成功！");
				return chargeItemInfo;
			}
			if ("5".equals(flag) || "6".equals(flag)) {// 检查项目异常类型
				AnomalyInfo chargeItemInfo = (AnomalyInfo) getObject(AnomalyInfo.class);
				UtilIterface util = (AnomalyContentUtil) getObject(AnomalyContentUtil.class);
				Object o = util.selectByPrimaryKey(this.parameterData);
				if (o != null) {
					chargeItemInfo.setData((ItemCategory_2) o);
				}
				chargeItemInfo.setRetCode("0");
				chargeItemInfo.setRetMsg("取得成功！");
				return chargeItemInfo;
			}

			else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			BaseJson j = new BaseJson();
			j.setRetCode("1");
			j.setRetMsg("系统异常!");
			return j;

		}
	}

	/**
	 * 检查项目异常类型 check
	 * 
	 * @return
	 */
	public BaseJson checkAnomalyType() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		AnomalyContentUtil util = (AnomalyContentUtil) getObject(AnomalyContentUtil.class);
		try {

			String r = util.checkExist(this.parameterData);
			if (r == null) {
				data.setRetCode("0");
			} else {
				data.setRetMsg(r);
				data.setRetCode("2");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	/**
	 * 检查项目异常类型列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public AnomalyContentInfo anomalyContentList() {

		AnomalyContentInfo anomalyContentInfo = (AnomalyContentInfo) getObject(AnomalyContentInfo.class);
		List<ItemCategory> list = null;
		AnomalyContentUtil util = (AnomalyContentUtil) getObject(AnomalyContentUtil.class);
		try {
			String anomalyType = (String) this.parameterData.get("anomalyType");
			String itemNum = (String) this.parameterData.get("itemNum");
			String categoryId = (String) this.parameterData.get("categoryId");

			Object pageNumO = this.parameterData.get("pageNum");
			Object pageSizeO = this.parameterData.get("pageSize");

			if (!StringUtils.isEmpty(((String) pageNumO)) && !StringUtils.isEmpty(((String) pageSizeO))) {
				Integer pageNum = Integer.valueOf((String) pageNumO);
				Integer pageSize = Integer.valueOf((String) pageSizeO);
				ItemCategory c = (ItemCategory) getObject(ItemCategory.class);
				c.setAnomalyType(anomalyType);
				c.setItemNum(itemNum);
				c.setCategoryId(categoryId);
				list = (List<ItemCategory>) util.selectByCondition(c, pageNum, pageSize);
				if (list != null && !list.isEmpty()) {
					anomalyContentInfo.setDatas(list);
					anomalyContentInfo.setRetCount("" + util.getTotalCount());
				} else {
					anomalyContentInfo.setRetCount("0");
				}
				anomalyContentInfo.setRetMsg("取得成功！");
				anomalyContentInfo.setRetCode("0");

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			anomalyContentInfo.setRetCode("1");
			anomalyContentInfo.setRetMsg("系统异常!");

		}
		return anomalyContentInfo;
	}

	public BaseJson insertAnomalyContent() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		try {
			Type type = new TypeToken<ItemCategory_2>() {
			}.getType();
			ItemCategory_2 itemCategory = StringUtils.gson.fromJson(this.parameter, type);
			// chargeItem.setCreateDate(new Date());
			itemCategory.setObjectid(StringUtils.getUUID());
			return insert(AnomalyContentUtil.class, itemCategory, data, "检查项目异常类型添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	public BaseJson updateAnomalyContent() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		AnomalyContentUtil util = (AnomalyContentUtil) getObject(AnomalyContentUtil.class);
		try {

			Type type = new TypeToken<ItemCategory_2>() {
			}.getType();
			ItemCategory_2 itemCategory = StringUtils.gson.fromJson(this.parameter, type);
			// chargeItem.setUpdateDate(new Date());
			util.update(itemCategory);
			data.setRetCode("0");
			data.setRetMsg("检查项目异常类型修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	public BaseJson deleteAnomalyContent() {
		BaseJson data = (BaseJson) getObject(BaseJson.class);
		AnomalyContentUtil util = (AnomalyContentUtil) getObject(AnomalyContentUtil.class);
		try {
			Type type = new TypeToken<List<String>>() {
			}.getType();
			List<String> objectids = StringUtils.gson.fromJson(this.parameter, type);
			util.delete(objectids);
			data.setRetCode("0");
			data.setRetMsg("检查项目异常类型删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			data.setRetCode("1");
			data.setRetMsg("系统异常!");

		}
		return data;
	}

	private String getFlag(String temp) {
		String r = "";
		switch (temp) {
		case "Z":
			r = "0";
			break;
		case "↑":
			r = "1";
			break;
		case "↓":
			r = "2";
			break;
		case "+":
			r = "3";
			break;
		case "Y":
			r = "4";
			break;
		case "W":
			r = "5";
			break;
		case "":
			r = "0";
			break;
		default:
			r = temp;
			break;

		}
		return r;
	}

	private ExamItem getExamItem(Object o) {
		ExamItem examItemBean = new ExamItem();
		if (o instanceof ExamResult) {
			ExamResult checkItem = (ExamResult) o;
			// ExamItem examItemBean = (ExamItem) getObject(ExamItem.class);
			examItemBean.setExamItemId(StringUtils.notNull(checkItem.getExamItemId()));
			examItemBean.setExamItemName(StringUtils.notNull(checkItem.getItemName()));
			examItemBean.setExamItemType(StringUtils.notNull(checkItem.getExamItemType()));
			examItemBean.setExamItemValue(StringUtils.notNull(checkItem.getExamResult()));
			examItemBean.setFlag(this.getFlag(StringUtils.notNull(checkItem.getFlag())));
			examItemBean.setIsDetail(StringUtils.notNull(checkItem.getIsDetail()));
			examItemBean.setItemnit(StringUtils.notNull(checkItem.getItemUnit()));
			examItemBean.setRef(StringUtils.notNull(checkItem.getRefValue()));
			examItemBean.setLineChart(StringUtils.notNull(checkItem.getLineChart()));
			examItemBean.setIsCompare(StringUtils.notNull(checkItem.getIsCompare()));
		}
		if (o instanceof ExamPacsSummaryOption) {
			ExamPacsSummaryOption checkItem = (ExamPacsSummaryOption) o;
			// ExamItem examItemBean = (ExamItem) getObject(ExamItem.class);
			examItemBean.setExamItemId("");
			examItemBean.setExamItemName(StringUtils.notNull(checkItem.getName()));
			examItemBean.setExamItemType("3");
			examItemBean.setExamItemValue(StringUtils.notNull(checkItem.getContent()));
			examItemBean.setFlag("");
			examItemBean.setIsDetail("0");
			examItemBean.setItemnit("");
			examItemBean.setRef("");
			examItemBean.setLineChart("0");
			examItemBean.setIsCompare("0");
		}

		return examItemBean;
	}

	/**
	 * 查询体检用户 @Title: examUserInfoList @Description: TODO(查询体检用户) @param @return
	 * 参数说明 @return String 返回类型 @throws
	 */
	public ExamInfo examUserInfoList() {

		// 结果
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		ExamInfoUtil util = (ExamInfoUtil) getObject(ExamInfoUtil.class);
		ExamInfo info = new ExamInfo();
		try {
			Object pageNumO = this.parameterData.get("pageNum");
			Object pageSizeO = this.parameterData.get("pageSize");

			if (!StringUtils.isEmpty(((String) pageNumO)) && !StringUtils.isEmpty(((String) pageSizeO))) {
				Integer pageNum = Integer.valueOf((String) pageNumO);
				Integer pageSize = Integer.valueOf((String) pageSizeO);
				result = (List<Map<String, Object>>) util.selectByCondition(this.parameterData, pageNum, pageSize);
				if (result != null && !result.isEmpty()) {
					info.setDatas(result);
					info.setRetCount("" + util.getTotalCount());
				} else {
					info.setRetCount("0");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return info;
	}
}
