package cn.hjw.model;

import java.util.Date;

public class ExamItemRefanddang {
    private String objectid;

    private String examItemId;

    private String isRefordang;

    private String valInfo;

    private String valIndex;

    private String createrId;

    private Date createDate;

    private String updaterId;

    private Date updateDate;

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid == null ? null : objectid.trim();
    }

    public String getExamItemId() {
        return examItemId;
    }

    public void setExamItemId(String examItemId) {
        this.examItemId = examItemId == null ? null : examItemId.trim();
    }

    public String getIsRefordang() {
        return isRefordang;
    }

    public void setIsRefordang(String isRefordang) {
        this.isRefordang = isRefordang == null ? null : isRefordang.trim();
    }

    public String getValInfo() {
        return valInfo;
    }

    public void setValInfo(String valInfo) {
        this.valInfo = valInfo == null ? null : valInfo.trim();
    }

    public String getValIndex() {
        return valIndex;
    }

    public void setValIndex(String valIndex) {
        this.valIndex = valIndex == null ? null : valIndex.trim();
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId == null ? null : createrId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId == null ? null : updaterId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}