package com.yconme.callphone.Bean;

import java.util.List;

/**
 * Created by saksamaa on 2017/6/20.
 */

public class PhoneBean {


    private String status;
    private String message;
    private String taoken;

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
        private String mobile;
        private String desc;
        private String area;
        private PackageEntity _package;
        private int is_memo;
        private int status;

        public void setId(int id) {
            this.id = id;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void set_package(PackageEntity _package) {
            this._package = _package;
        }

        public void setIs_memo(int is_memo) {
            this.is_memo = is_memo;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public String getMobile() {
            return mobile;
        }

        public String getDesc() {
            return desc;
        }

        public String getArea() {
            return area;
        }

        public PackageEntity get_package() {
            return _package;
        }

        public int getIs_memo() {
            return is_memo;
        }

        public int getStatus() {
            return status;
        }

        public static class PackageEntity {
            private List<String> basis;
            private List<String> other;



            public void setBasis(List<String> basis) {
                this.basis = basis;
            }

            public void setOther(List<String> other) {
                this.other = other;
            }

            public List<String> getBasis() {
                return basis;
            }

            public List<String> getOther() {
                return other;
            }
        }
    }
}
