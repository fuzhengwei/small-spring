package cn.bugstack.springframework.test.bean;

import java.util.Date;

public class Husband {

    private String wifiName;

    private Date marriageDate;

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public Date getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(Date marriageDate) {
        this.marriageDate = marriageDate;
    }

    @Override
    public String toString() {
        return "Husband{" +
                "wifiName='" + wifiName + '\'' +
                ", marriageDate=" + marriageDate +
                '}';
    }

}
