package com.yconme.callphone.Bean;

import java.util.List;

/**
 * Created by saksamaa on 2017/6/24.
 */

public class PhonelistRecordBean {

    /**
     * status : 1
     * message : 成功返回统计结果。
     * taoken : b5e30b20-5890-11e7-ba4c-a974f10cb328
     * data : {"total":379,"records":[{"status":-2,"count":4},{"status":1,"count":9},{"status":2,"count":366}]}
     */

    private String status;
    private String message;
    private String taoken;
    /**
     * total : 379
     * records : [{"status":-2,"count":4},{"status":1,"count":9},{"status":2,"count":366}]
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
