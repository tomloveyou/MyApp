package com.yl.myapp.bean;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class ControlBean extends LitePalSupport implements Serializable {
    private String  RESOURCE_ID;
    private String RESOURCE_NAME;
    private String RESOURCE_CODE;
    private String RESOURCE_TYPE;
    private String URL;
    private String PID;
    private String LEVEL_NO;
    private String SORT_NO;
    private String DESCRIPTION;
    private String PNAME;
    private String LOCATION_FLAG;
    private String RESOURCE_CLASS;
    private String ENABLED;
    private String APP_ID;
    private String RRID;
    private String ROLE_ID;

    private String URID;
    private String USER_ID;

    public String getRESOURCE_ID() {
        return RESOURCE_ID;
    }

    public void setRESOURCE_ID(String RESOURCE_ID) {
        this.RESOURCE_ID = RESOURCE_ID;
    }

    public String getRESOURCE_NAME() {
        return RESOURCE_NAME;
    }

    public void setRESOURCE_NAME(String RESOURCE_NAME) {
        this.RESOURCE_NAME = RESOURCE_NAME;
    }

    public String getRESOURCE_CODE() {
        return RESOURCE_CODE;
    }

    public void setRESOURCE_CODE(String RESOURCE_CODE) {
        this.RESOURCE_CODE = RESOURCE_CODE;
    }

    public String getRESOURCE_TYPE() {
        return RESOURCE_TYPE;
    }

    public void setRESOURCE_TYPE(String RESOURCE_TYPE) {
        this.RESOURCE_TYPE = RESOURCE_TYPE;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getLEVEL_NO() {
        return LEVEL_NO;
    }

    public void setLEVEL_NO(String LEVEL_NO) {
        this.LEVEL_NO = LEVEL_NO;
    }

    public String getSORT_NO() {
        return SORT_NO;
    }

    public void setSORT_NO(String SORT_NO) {
        this.SORT_NO = SORT_NO;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getPNAME() {
        return PNAME;
    }

    public void setPNAME(String PNAME) {
        this.PNAME = PNAME;
    }

    public String getLOCATION_FLAG() {
        return LOCATION_FLAG;
    }

    public void setLOCATION_FLAG(String LOCATION_FLAG) {
        this.LOCATION_FLAG = LOCATION_FLAG;
    }

    public String getRESOURCE_CLASS() {
        return RESOURCE_CLASS;
    }

    public void setRESOURCE_CLASS(String RESOURCE_CLASS) {
        this.RESOURCE_CLASS = RESOURCE_CLASS;
    }

    public String getENABLED() {
        return ENABLED;
    }

    public void setENABLED(String ENABLED) {
        this.ENABLED = ENABLED;
    }

    public String getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(String APP_ID) {
        this.APP_ID = APP_ID;
    }

    public String getRRID() {
        return RRID;
    }

    public void setRRID(String RRID) {
        this.RRID = RRID;
    }

    public String getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getURID() {
        return URID;
    }

    public void setURID(String URID) {
        this.URID = URID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
}
