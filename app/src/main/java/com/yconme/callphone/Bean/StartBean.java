package com.yconme.callphone.Bean;

import java.util.List;

/**
 * Created by saksamaa on 2017/6/19.
 */

public class StartBean {
    private String status;
    private String message;
    private String taoken;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTaoken() {
        return taoken;
    }

    public void setTaoken(String taoken) {
        this.taoken = taoken;
    }

    public List<StartBean.data> getData() {
        return data;
    }

    public void setData(List<StartBean.data> data) {
        this.data = data;
    }

    private List<data> data;

    public class data {
        private String id;
        private String merchant_id;
        private String title;
        private String desc;
        private String url;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        private String deleted;
        private String created_at;
        private String updated_at;
    }
}
