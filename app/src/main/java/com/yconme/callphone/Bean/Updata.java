package com.yconme.callphone.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class Updata {


    /**
     * version : 0.01
     * note : ["解决了反复拨打同一电话号码的错误。","解决了通话记录时长统计不准的错误。"]
     * url : http://call.ycome.com/storage/app-release.apk
     */

    private String version;
    private String url;
    private List<String> note;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getNote() {
        return note;
    }

    public void setNote(List<String> note) {
        this.note = note;
    }
}
