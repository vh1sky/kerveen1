package com.mysiteforme.admin.mqtt;

/**
 * @author Iwen
 * @date 2019/6/1 9:13
 * @Version 1.0
 */
//@Component
//@ConfigurationProperties(prefix = "onenet")
public class OneNetConfig {

    private String url;
    private String productId;
    private String userId;
    private String masterApiKey;
    private String accesskey;
    private String deviceCode;
    private String excel;
    private String excelPath;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMasterApiKey() {
        return masterApiKey;
    }

    public void setMasterApiKey(String masterApiKey) {
        this.masterApiKey = masterApiKey;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExcel() {
        return excel;
    }

    public void setExcel(String excel) {
        this.excel = excel;
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }
}
