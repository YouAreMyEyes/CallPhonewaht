package com.yconme.callphone.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by saksamaa on 2017/6/20.
 */

public class SubmitBean {

    /**
     * status : 1
     * message : 文件成功上传，德新你行！
     * taoken : bb1552a0-559e-11e7-95dd-970c111a70a7
     * data : {"id":36,"tel_id":176,"merchant_id":1,"employee_id":2,"mobile":"18353284442","area":"青岛","package":"100元","is_memo":1,"memo":"999","status":1,"deleted":0,"url":"public/tapes/1/2/ln5sHRMylFnFjGfAV45S1YKazYulARp4UNPs9EH8.3gp","created_at":"2017-06-20 18:01:05","updated_at":"2017-06-20 18:01:05","absurl":"http://192.168.1.252/storage/tapes/1/2/ln5sHRMylFnFjGfAV45S1YKazYulARp4UNPs9EH8.3gp","filetype":null}
     */

    private String status;
    private String message;
    private String taoken;
    /**
     * id : 36
     * tel_id : 176
     * merchant_id : 1
     * employee_id : 2
     * mobile : 18353284442
     * area : 青岛
     * package : 100元
     * is_memo : 1
     * memo : 999
     * status : 1
     * deleted : 0
     * url : public/tapes/1/2/ln5sHRMylFnFjGfAV45S1YKazYulARp4UNPs9EH8.3gp
     * created_at : 2017-06-20 18:01:05
     * updated_at : 2017-06-20 18:01:05
     * absurl : http://192.168.1.252/storage/tapes/1/2/ln5sHRMylFnFjGfAV45S1YKazYulARp4UNPs9EH8.3gp
     * filetype : null
     */

    private DataEntity data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTaoken(String taoken) {
        this.taoken = taoken;
    }

    public void setData(DataEntity data) {
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

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private int id;
        private int tel_id;
        private int merchant_id;
        private int employee_id;
        private String mobile;
        private String area;
        @SerializedName("package")
        private String packageX;
        private int is_memo;
        private String memo;
        private String status;
        private int deleted;
        private String url;
        private String created_at;
        private String updated_at;
        private String absurl;
        private Object filetype;

        public void setId(int id) {
            this.id = id;
        }

        public void setTel_id(int tel_id) {
            this.tel_id = tel_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
        }

        public void setEmployee_id(int employee_id) {
            this.employee_id = employee_id;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public void setIs_memo(int is_memo) {
            this.is_memo = is_memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public void setAbsurl(String absurl) {
            this.absurl = absurl;
        }

        public void setFiletype(Object filetype) {
            this.filetype = filetype;
        }

        public int getId() {
            return id;
        }

        public int getTel_id() {
            return tel_id;
        }

        public int getMerchant_id() {
            return merchant_id;
        }

        public int getEmployee_id() {
            return employee_id;
        }

        public String getMobile() {
            return mobile;
        }

        public String getArea() {
            return area;
        }

        public String getPackageX() {
            return packageX;
        }

        public int getIs_memo() {
            return is_memo;
        }

        public String getMemo() {
            return memo;
        }

        public String getStatus() {
            return status;
        }

        public int getDeleted() {
            return deleted;
        }

        public String getUrl() {
            return url;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public String getAbsurl() {
            return absurl;
        }

        public Object getFiletype() {
            return filetype;
        }
    }
}
