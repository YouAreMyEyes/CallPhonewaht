package com.yconme.callphone.Bean;

/**
 * Created by saksamaa on 2017/6/16.
 */

public class LoginBean {
    private String status;
    private String message;

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

    public LoginBean.data getData() {
        return data;
    }

    public void setData(LoginBean.data data) {
        this.data = data;
    }

    private String taoken;
    private data data;

    class data {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private String password;
    }
}
