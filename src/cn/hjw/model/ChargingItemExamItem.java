package cn.hjw.model;

public class ChargingItemExamItem {
    private String objectid;

    private String chargingItemId;

    private String examinationItemId;

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid == null ? null : objectid.trim();
    }

    public String getChargingItemId() {
        return chargingItemId;
    }

    public void setChargingItemId(String chargingItemId) {
        this.chargingItemId = chargingItemId == null ? null : chargingItemId.trim();
    }

    public String getExaminationItemId() {
        return examinationItemId;
    }

    public void setExaminationItemId(String examinationItemId) {
        this.examinationItemId = examinationItemId == null ? null : examinationItemId.trim();
    }
}