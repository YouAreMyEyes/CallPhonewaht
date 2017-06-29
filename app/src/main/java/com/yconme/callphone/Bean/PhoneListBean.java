package com.yconme.callphone.Bean;

import android.widget.ProgressBar;

import java.util.List;

/**
 * Created by saksamaa on 2017/6/16.
 */

public class PhoneListBean {
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

    public PhoneListBean.data getData() {
        return data;
    }

    public void setData(PhoneListBean.data data) {
        this.data = data;
    }

    private data data;

    public class data {
        private String id;
        private String mobile;
        private String desc;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public PhoneListBean.data._package get_package() {
            return _package;
        }

        public void set_package(PhoneListBean.data._package _package) {
            this._package = _package;
        }

        public String getIs_memo() {
            return is_memo;
        }

        public void setIs_memo(String is_memo) {
            this.is_memo = is_memo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        private String area;
        private _package _package;

        public class _package {
            private List<basis> basis;

            public List<PhoneListBean.data._package.other> getOther() {
                return other;
            }

            public void setOther(List<PhoneListBean.data._package.other> other) {
                this.other = other;
            }

            public List<PhoneListBean.data._package.basis> getBasis() {
                return basis;
            }

            public void setBasis(List<PhoneListBean.data._package.basis> basis) {
                this.basis = basis;
            }

            public class basis {

            }

            private List<other> other;

            public class other {

            }
        }

        private String is_memo;
        private String status;
    }

}
