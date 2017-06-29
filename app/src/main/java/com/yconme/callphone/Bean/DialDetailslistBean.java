package com.yconme.callphone.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by saksamaa on 2017/6/24.
 */

public class DialDetailslistBean {


    /**
     * status : 1
     * message : 成功返回统计结果。
     * taoken : 58d66ec0-589c-11e7-832e-09f7cade815b
     * data : [{"id":11,"tel_id":11,"merchant_id":1,"employee_id":2,"mobile":"15954811111","package":"100元","is_memo":null,"memo":null,"status":2,"deleted":0,"url":null,"time":0,"created_at":"2017-06-24 10:43:26","updated_at":"2017-06-24 10:43:26"},{"id":12,"tel_id":12,"merchant_id":1,"employee_id":2,"mobile":"17685832999","package":"30元","is_memo":null,"memo":null,"status":2,"deleted":0,"url":null,"time":0,"created_at":"2017-06-24 10:43:26","updated_at":"2017-06-24 10:43:26"},{"id":13,"tel_id":13,"merchant_id":1,"employee_id":2,"mobile":"17685832999","package":"60元","is_memo":null,"memo":null,"status":2,"deleted":0,"url":null,"time":0,"created_at":"2017-06-24 10:43:26","updated_at":"2017-06-24 10:43:26"},{"id":14,"tel_id":14,"merchant_id":1,"employee_id":2,"mobile":"17685832999","package":"100元","is_memo":null,"memo":null,"status":2,"deleted":0,"url":null,"time":0,"created_at":"2017-06-24 10:43:26","updated_at":"2017-06-24 10:43:26"},{"id":15,"tel_id":15,"merchant_id":1,"employee_id":2,"mobile":"15954811111","package":"100元","is_memo":null,"memo":null,"status":2,"deleted":0,"url":null,"time":0,"created_at":"2017-06-24 10:43:26","updated_at":"2017-06-24 10:43:26"},{"id":16,"tel_id":16,"merchant_id":1,"employee_id":2,"mobile":"15864255666","package":"30元","is_memo":null,"memo":null,"status":2,"deleted":0,"url":null,"time":0,"created_at":"2017-06-24 10:43:26","updated_at":"2017-06-24 10:43:26"},{"id":17,"tel_id":17,"merchant_id":1,"employee_id":2,"mobile":"15864255666","package":"30元","is_memo":null,"memo":null,"status":2,"deleted":0,"url":null,"time":0,"created_at":"2017-06-24 10:43:26","updated_at":"2017-06-24 10:43:26"},{"id":18,"tel_id":18,"merchant_id":1,"employee_id":2,"mobile":"15864255666","package":"60元","is_memo":null,"memo":null,"status":2,"deleted":0,"url":null,"time":0,"created_at":"2017-06-24 10:43:26","updated_at":"2017-06-24 10:43:26"},{"id":19,"tel_id":19,"merchant_id":1,"employee_id":2,"mobile":"17685832999","package":"100元","is_memo":null,"memo":null,"status":2,"deleted":0,"url":null,"time":0,"created_at":"2017-06-24 10:43:26","updated_at":"2017-06-24 10:43:26"},{"id":20,"tel_id":20,"merchant_id":1,"employee_id":2,"mobile":"15954811111","package":"60元","is_memo":null,"memo":null,"status":2,"deleted":0,"url":null,"time":0,"created_at":"2017-06-24 10:43:26","updated_at":"2017-06-24 10:43:26"}]
     */

    private String status;
    private String message;
    private String taoken;
    /**
     * id : 11
     * tel_id : 11
     * merchant_id : 1
     * employee_id : 2
     * mobile : 15954811111
     * package : 100元
     * is_memo : null
     * memo : null
     * status : 2
     * deleted : 0
     * url : null
     * time : 0
     * created_at : 2017-06-24 10:43:26
     * updated_at : 2017-06-24 10:43:26
     */

    private List<DataEntity> data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTaoken(String taoken) {
        this.taoken = taoken;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTaoken() {
        return taoken;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String id;
        private String tel_id;
        private String merchant_id;
        private String employee_id;
        private String mobile;
        @SerializedName("package")
        private String packageX;
        private Object is_memo;
        private Object memo;
        private String status;
        private int deleted;
        private Object url;
        private int time;
        private String created_at;
        private String updated_at;

        public void setId(String id) {
            this.id = id;
        }

        public void setTel_id(String tel_id) {
            this.tel_id = tel_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
        }

        public void setEmployee_id(String employee_id) {
            this.employee_id = employee_id;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public void setIs_memo(Object is_memo) {
            this.is_memo = is_memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getId() {
            return id;
        }

        public String getTel_id() {
            return tel_id;
        }

        public String getMerchant_id() {
            return merchant_id;
        }

        public String getEmployee_id() {
            return employee_id;
        }

        public String getMobile() {
            return mobile;
        }

        public String getPackageX() {
            return packageX;
        }

        public Object getIs_memo() {
            return is_memo;
        }

        public Object getMemo() {
            return memo;
        }

        public String getStatus() {
            return status;
        }

        public int getDeleted() {
            return deleted;
        }

        public Object getUrl() {
            return url;
        }

        public int getTime() {
            return time;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }
}
