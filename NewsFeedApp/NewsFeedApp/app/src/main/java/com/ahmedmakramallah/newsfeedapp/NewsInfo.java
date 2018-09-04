package com.ahmedmakramallah.newsfeedapp;

/**
 * Created by ahmed on 8/11/2017.
 */

public class NewsInfo {
    String mTitle;
    String mSectionName;
    String mDate;
    String mWebUrl;
    String mWebTitle;

    public NewsInfo(String mTitle, String mSectionName, String mDate, String mWebUrl, String mWebTitle) {
        this.mTitle = mTitle;
        this.mSectionName = mSectionName;
        this.mDate = mDate;
        this.mWebUrl = mWebUrl;
        this.mWebTitle = mWebTitle;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public String getmDate() {

        return mDate;
    }

    public String getmSectionName() {

        return mSectionName;
    }

    public String getmTitle() {

        return mTitle;
    }

    public String getmWebTitle() {
        return mWebTitle;
    }
}
