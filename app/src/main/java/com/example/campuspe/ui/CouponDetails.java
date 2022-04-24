package com.example.campuspe.ui;

public class CouponDetails {
    String code;
    String desc;
    int off;

    public int getOff() {
        return off;
    }

    public void setOff(int off) {
        this.off = off;
    }

    public CouponDetails() {
    }

    public CouponDetails(String code, String desc, int off) {
        this.code = code;
        this.desc = desc;
        this.off = off;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
