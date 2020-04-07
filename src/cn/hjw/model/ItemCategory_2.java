package cn.hjw.model;

import java.util.List;

public class ItemCategory_2 {
    private String objectid;

    private String itemId;

    private String anomalyType;

    private String anomalyTypeName;

    private String categoryId;

    private String categoryicon;

    private String categoryname;
    private String itemNum;
	private String itemName;
    private List<ItemCategoryContent> datas;
    public String getObjectid() {
        return objectid;
    }

    public String getItemNum() {
		return itemNum;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setObjectid(String objectid) {
        this.objectid = objectid == null ? null : objectid.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getAnomalyType() {
        return anomalyType;
    }

    public void setAnomalyType(String anomalyType) {
        this.anomalyType = anomalyType == null ? null : anomalyType.trim();
    }

    public String getAnomalyTypeName() {
        return anomalyTypeName;
    }

    public void setAnomalyTypeName(String anomalyTypeName) {
        this.anomalyTypeName = anomalyTypeName == null ? null : anomalyTypeName.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getCategoryicon() {
        return categoryicon;
    }

    public void setCategoryicon(String categoryicon) {
        this.categoryicon = categoryicon == null ? null : categoryicon.trim();
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

	public List<ItemCategoryContent> getDatas() {
		return datas;
	}

	public void setDatas(List<ItemCategoryContent> datas) {
		this.datas = datas;
	}
    
}