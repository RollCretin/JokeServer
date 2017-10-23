package com.cretin.po;

import java.util.Date;

public class VerificationCode {
    private String smsId;

    private String smstel;

    private String smscontent;

    private Integer smsstatus;

    private String smscode;

    private Date updateTime;

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId == null ? null : smsId.trim();
    }

    public String getSmstel() {
        return smstel;
    }

    public void setSmstel(String smstel) {
        this.smstel = smstel == null ? null : smstel.trim();
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent == null ? null : smscontent.trim();
    }

    public Integer getSmsstatus() {
        return smsstatus;
    }

    public void setSmsstatus(Integer smsstatus) {
        this.smsstatus = smsstatus;
    }

    public String getSmscode() {
        return smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode == null ? null : smscode.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}