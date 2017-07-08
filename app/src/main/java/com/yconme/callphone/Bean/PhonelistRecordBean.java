package com.yconme.callphone.Bean;

import java.util.List;

/**
 * Created by saksamaa on 2017/6/24.
 */

public class PhonelistRecordBean {



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
        private String total;
        /**
         * status : -2
         * count : 4
         */

        private List<RecordsEntity> records;

        public void setTotal(String total) {
            this.total = total;
        }

        public void setRecords(List<RecordsEntity> records) {
            this.records = records;
        }

        public String getTotal() {
            return total;
        }

        public List<RecordsEntity> getRecords() {
            return records;
        }

        public static class RecordsEntity {
            private String status;
            private String count;

            public void setStatus(String status) {
                this.status = status;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getStatus() {
                return status;
            }

            public String getCount() {
                return count;
            }
        }
    }
}
