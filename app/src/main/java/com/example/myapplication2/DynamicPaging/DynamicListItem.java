package com.example.myapplication2.DynamicPaging;

import org.json.JSONObject;

public class DynamicListItem {
    Integer bronze , silver , gold , accountId;
    boolean isEmployee;
    String userType , location , websiteUrl, displayName , profileURL;

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setBronze(Integer bronze) {
        this.bronze = bronze;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public void setSilver(Integer silver) {
        this.silver = silver;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Integer getBronze() {
        return bronze;
    }

    public Integer getGold() {
        return gold;
    }

    public Integer getSilver() {
        return silver;
    }

    public String getUserType() {
        return userType;
    }

    public String getLocation() {
        return location;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

}
