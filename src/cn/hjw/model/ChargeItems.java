package cn.hjw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeItems {
	private String objectid;

	private String itemCode;

	private String itemPinyin;

	private String itemName;

	private String itemCategory;

	private String deptId;
	private String deptName;
	
	private String depCategory;

	private String samDemoId;

	private String samReportDemoId;

	private String sex;

	private Double amount;

	private String isonlyapplyorreport;

	private String itemSeq;

	private String guideCategory;

	private String hisCode;

	private String examCode;

	private String viewCode;

	private String isactive;

	private Long calculationAmount;

	private String interfaceFlag;

	private String itemType;

	private String deleteFlg;

	private Date createDate;

	private String createrId;

	private Date updateDate;

	private String updaterId;	
	private String iscancel;
	private String examSummary;
	private String similarCode;
	private String examAdvice;
	private String baseItem;
	private String tabooCrowd;	
	
	private Date deleteDate;

	private String deleterId;
	private List<ExaminationItem> examItems=new ArrayList<ExaminationItem>();
	private List<ChargingItemExamItem> options=new ArrayList<ChargingItemExamItem>();

	public List<ExaminationItem> getExamItems() {
		return examItems;
	}

	public void setExamItems(List<ExaminationItem> examItems) {
		this.examItems = examItems;
	}

	public List<ChargingItemExamItem> getOptions() {
		return options;
	}

	public void setOptions(List<ChargingItemExamItem> options) {
		this.options = options;
	}

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid == null ? null : objectid.trim();
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode == null ? null : itemCode.trim();
	}

	public String getItemPinyin() {
		return itemPinyin;
	}

	public void setItemPinyin(String itemPinyin) {
		this.itemPinyin = itemPinyin == null ? null : itemPinyin.trim();
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName == null ? null : itemName.trim();
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory == null ? null : itemCategory.trim();
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId == null ? null : deptId.trim();
	}

	public String getDepCategory() {
		return depCategory;
	}

	public void setDepCategory(String depCategory) {
		this.depCategory = depCategory == null ? null : depCategory.trim();
	}

	public String getSamDemoId() {
		return samDemoId;
	}

	public void setSamDemoId(String samDemoId) {
		this.samDemoId = samDemoId == null ? null : samDemoId.trim();
	}

	public String getSamReportDemoId() {
		return samReportDemoId;
	}

	public void setSamReportDemoId(String samReportDemoId) {
		this.samReportDemoId = samReportDemoId == null ? null : samReportDemoId
				.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getIsonlyapplyorreport() {
		return isonlyapplyorreport;
	}

	public void setIsonlyapplyorreport(String isonlyapplyorreport) {
		this.isonlyapplyorreport = isonlyapplyorreport == null ? null
				: isonlyapplyorreport.trim();
	}

	public String getItemSeq() {
		return itemSeq;
	}

	public void setItemSeq(String itemSeq) {
		this.itemSeq = itemSeq == null ? null : itemSeq.trim();
	}

	public String getGuideCategory() {
		return guideCategory;
	}

	public void setGuideCategory(String guideCategory) {
		this.guideCategory = guideCategory == null ? null : guideCategory
				.trim();
	}

	public String getHisCode() {
		return hisCode;
	}

	public void setHisCode(String hisCode) {
		this.hisCode = hisCode == null ? null : hisCode.trim();
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode == null ? null : examCode.trim();
	}

	public String getViewCode() {
		return viewCode;
	}

	public void setViewCode(String viewCode) {
		this.viewCode = viewCode == null ? null : viewCode.trim();
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive == null ? null : isactive.trim();
	}

	public Long getCalculationAmount() {
		return calculationAmount;
	}

	public void setCalculationAmount(Long calculationAmount) {
		this.calculationAmount = calculationAmount;
	}

	public String getInterfaceFlag() {
		return interfaceFlag;
	}

	public void setInterfaceFlag(String interfaceFlag) {
		this.interfaceFlag = interfaceFlag == null ? null : interfaceFlag
				.trim();
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType == null ? null : itemType.trim();
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg == null ? null : deleteFlg.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId == null ? null : createrId.trim();
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId == null ? null : updaterId.trim();
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getDeleterId() {
		return deleterId;
	}

	public void setDeleterId(String deleterId) {
		this.deleterId = deleterId == null ? null : deleterId.trim();
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getIscancel() {
		return iscancel;
	}

	public void setIscancel(String iscancel) {
		this.iscancel = iscancel;
	}

	public String getExamSummary() {
		return examSummary;
	}

	public void setExamSummary(String examSummary) {
		this.examSummary = examSummary;
	}

	public String getSimilarCode() {
		return similarCode;
	}

	public void setSimilarCode(String similarCode) {
		this.similarCode = similarCode;
	}

	public String getExamAdvice() {
		return examAdvice;
	}

	public void setExamAdvice(String examAdvice) {
		this.examAdvice = examAdvice;
	}

	public String getBaseItem() {
		return baseItem;
	}

	public void setBaseItem(String baseItem) {
		this.baseItem = baseItem;
	}

	public String getTabooCrowd() {
		return tabooCrowd;
	}

	public void setTabooCrowd(String tabooCrowd) {
		this.tabooCrowd = tabooCrowd;
	}
	
}