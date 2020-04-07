package cn.hjw.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExaminationItem {
	private String objectId;

	private String depName;

    private String itemNum;

    private String itemName;

    private String itemEngName;

    private String itemUnitunit;

    private String itemPinyin;

    private String examCodecode;

    private String viewCodecode;

    private String itemCategory;

    private String isPrint;

    private String seqCode;

    private String itemDescription;

    private String defaultValue;

    private String remark;

    private String refImg;

    private String isDetail;

    private String isCompare;

    private String refMmax;

    private String refMmin;

    private String refFmin;

    private String refFmax;

    private String dangFmax;

    private String dangFmin;

    private String dangMmax;

    private String dangMmin;

    private String isactive;

    private String itemType;

    private String deleteFlg;

    private Date createDate;

    private String createrId;

    private Date updateDate;

    private String updaterId;

    private Date deleteDate;

    private String deleterId;

    private String chargeItemId;

    private String deptId;

    private String lineChart;
	private String dang_1;
	private String dang_2;
	private String dang_3;
	private String dang_4;
	private String refer_1;
	private String refer_2;
	private String refer_3;
	private String refer_4;
	private String refer_5;
	private String refer_6;
	private Map<String,Map<String,String>> dangReferMap;
	
	private List<ExamItemRefanddang> refList;
	private List<ExamItemRefanddang> dangList;
	
	public List<ExamItemRefanddang> getRefList() {
		return refList;
	}
	public void setRefList(List<ExamItemRefanddang> refList) {
		this.refList = refList;
	}
	public List<ExamItemRefanddang> getDangList() {
		return dangList;
	}
	public void setDangList(List<ExamItemRefanddang> dangList) {
		this.dangList = dangList;
	}
	public String getDang_1() {
		return dang_1;
	}
	public void setDang_1(String dang_1) {
		this.dang_1 = dang_1;
	}
	public String getDang_2() {
		return dang_2;
	}
	public void setDang_2(String dang_2) {
		this.dang_2 = dang_2;
	}
	public String getDang_3() {
		return dang_3;
	}
	public void setDang_3(String dang_3) {
		this.dang_3 = dang_3;
	}
	public String getDang_4() {
		return dang_4;
	}
	public void setDang_4(String dang_4) {
		this.dang_4 = dang_4;
	}
	public String getRefer_1() {
		return refer_1;
	}
	public void setRefer_1(String refer_1) {
		this.refer_1 = refer_1;
	}
	public String getRefer_2() {
		return refer_2;
	}
	public void setRefer_2(String refer_2) {
		this.refer_2 = refer_2;
	}
	public String getRefer_3() {
		return refer_3;
	}
	public void setRefer_3(String refer_3) {
		this.refer_3 = refer_3;
	}
	public String getRefer_4() {
		return refer_4;
	}
	public void setRefer_4(String refer_4) {
		this.refer_4 = refer_4;
	}
	public String getRefer_5() {
		return refer_5;
	}
	public void setRefer_5(String refer_5) {
		this.refer_5 = refer_5;
	}
	public String getRefer_6() {
		return refer_6;
	}
	public void setRefer_6(String refer_6) {
		this.refer_6 = refer_6;
	}
	public Map<String, Map<String, String>> getDangReferMap() {
		return dangReferMap;
	}
	public void setDangReferMap(Map<String, Map<String, String>> dangReferMap) {
		this.dangReferMap = dangReferMap;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum == null ? null : itemNum.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemEngName() {
        return itemEngName;
    }

    public void setItemEngName(String itemEngName) {
        this.itemEngName = itemEngName == null ? null : itemEngName.trim();
    }

    public String getItemUnitunit() {
        return itemUnitunit;
    }

    public void setItemUnitunit(String itemUnitunit) {
        this.itemUnitunit = itemUnitunit == null ? null : itemUnitunit.trim();
    }

    public String getItemPinyin() {
        return itemPinyin;
    }

    public void setItemPinyin(String itemPinyin) {
        this.itemPinyin = itemPinyin == null ? null : itemPinyin.trim();
    }

    public String getExamCodecode() {
        return examCodecode;
    }

    public void setExamCodecode(String examCodecode) {
        this.examCodecode = examCodecode == null ? null : examCodecode.trim();
    }

    public String getViewCodecode() {
        return viewCodecode;
    }

    public void setViewCodecode(String viewCodecode) {
        this.viewCodecode = viewCodecode == null ? null : viewCodecode.trim();
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory == null ? null : itemCategory.trim();
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint == null ? null : isPrint.trim();
    }

    public String getSeqCode() {
        return seqCode;
    }

    public void setSeqCode(String seqCode) {
        this.seqCode = seqCode == null ? null : seqCode.trim();
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription == null ? null : itemDescription.trim();
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRefImg() {
        return refImg;
    }

    public void setRefImg(String refImg) {
        this.refImg = refImg == null ? null : refImg.trim();
    }

    public String getIsDetail() {
        return isDetail;
    }

    public void setIsDetail(String isDetail) {
        this.isDetail = isDetail == null ? null : isDetail.trim();
    }

    public String getIsCompare() {
        return isCompare;
    }

    public void setIsCompare(String isCompare) {
        this.isCompare = isCompare == null ? null : isCompare.trim();
    }

    public String getRefMmax() {
        return refMmax;
    }

    public void setRefMmax(String refMmax) {
        this.refMmax = refMmax == null ? null : refMmax.trim();
    }

    public String getRefMmin() {
        return refMmin;
    }

    public void setRefMmin(String refMmin) {
        this.refMmin = refMmin == null ? null : refMmin.trim();
    }

    public String getRefFmin() {
        return refFmin;
    }

    public void setRefFmin(String refFmin) {
        this.refFmin = refFmin == null ? null : refFmin.trim();
    }

    public String getRefFmax() {
        return refFmax;
    }

    public void setRefFmax(String refFmax) {
        this.refFmax = refFmax == null ? null : refFmax.trim();
    }

    public String getDangFmax() {
        return dangFmax;
    }

    public void setDangFmax(String dangFmax) {
        this.dangFmax = dangFmax == null ? null : dangFmax.trim();
    }

    public String getDangFmin() {
        return dangFmin;
    }

    public void setDangFmin(String dangFmin) {
        this.dangFmin = dangFmin == null ? null : dangFmin.trim();
    }

    public String getDangMmax() {
        return dangMmax;
    }

    public void setDangMmax(String dangMmax) {
        this.dangMmax = dangMmax == null ? null : dangMmax.trim();
    }

    public String getDangMmin() {
        return dangMmin;
    }

    public void setDangMmin(String dangMmin) {
        this.dangMmin = dangMmin == null ? null : dangMmin.trim();
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive == null ? null : isactive.trim();
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

    public String getChargeItemId() {
        return chargeItemId;
    }

    public void setChargeItemId(String chargeItemId) {
        this.chargeItemId = chargeItemId == null ? null : chargeItemId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getLineChart() {
        return lineChart;
    }

    public void setLineChart(String lineChart) {
        this.lineChart = lineChart == null ? null : lineChart.trim();
    }
}
