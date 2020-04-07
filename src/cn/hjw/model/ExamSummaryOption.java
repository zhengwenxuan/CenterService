package cn.hjw.model;

import java.util.Date;

public class ExamSummaryOption {
    private String objectid;

    private String examNum;

    private String name;

    private String checkDoc;

    private Date createDate;

    private Integer showOrder;

    private String content;
private String code;

    public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

	public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid == null ? null : objectid.trim();
    }

    public String getExamNum() {
        return examNum;
    }

    public void setExamNum(String examNum) {
        this.examNum = examNum == null ? null : examNum.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCheckDoc() {
        return checkDoc;
    }

    public void setCheckDoc(String checkDoc) {
        this.checkDoc = checkDoc == null ? null : checkDoc.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}