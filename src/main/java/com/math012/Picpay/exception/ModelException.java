package com.math012.Picpay.exception;

import java.io.Serializable;
import java.util.Date;

public class ModelException implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date timeStamp;
    private String msg;
    private String detail;

    public ModelException(Date timeStamp, String msg, String detail) {
        this.timeStamp = timeStamp;
        this.msg = msg;
        this.detail = detail;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
