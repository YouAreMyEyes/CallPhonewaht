package com.yconme.callphone.Bean;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class Updata {
    private data data;
    private String msg;
    private String error_code;

    public Updata.data getData() {
        return data;
    }

    public void setData(Updata.data data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public class data {
        private String id;
        private String app_version;
        private String app_title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApp_version() {
            return app_version;
        }

        public void setApp_version(String app_version) {
            this.app_version = app_version;
        }

        public String getApp_title() {
            return app_title;
        }

        public void setApp_title(String app_title) {
            this.app_title = app_title;
        }

        public String getApp_description() {
            return app_description;
        }

        public void setApp_description(String app_description) {
            this.app_description = app_description;
        }

        public String getApp_downloadurl() {
            return app_downloadurl;
        }

        public void setApp_downloadurl(String app_downloadurl) {
            this.app_downloadurl = app_downloadurl;
        }

        private String app_description;
        private String app_downloadurl;
    }


}
