package com.example.codedemo;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
// https://blog.csdn.net/qq_16778399/article/details/103957226
public class DataBean implements Serializable {
    private Integer rc;

    private Integer rt;

    private Data data;

    private Integer lt;

    private String dlmkts;

    private Long svr;

    private Integer full;

    public Integer getRc() {
        return this.rc;
    }

    public void setRc(Integer rc) {
        this.rc = rc;
    }

    public Integer getRt() {
        return this.rt;
    }

    public void setRt(Integer rt) {
        this.rt = rt;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getLt() {
        return this.lt;
    }

    public void setLt(Integer lt) {
        this.lt = lt;
    }

    public String getDlmkts() {
        return this.dlmkts;
    }

    public void setDlmkts(String dlmkts) {
        this.dlmkts = dlmkts;
    }

    public Long getSvr() {
        return this.svr;
    }

    public void setSvr(Long svr) {
        this.svr = svr;
    }

    public Integer getFull() {
        return this.full;
    }

    public void setFull(Integer full) {
        this.full = full;
    }

    public static class Data implements Serializable {
        private Double f43;

        private Double f45;

        private Double f44;

        private String f58;

        private Double f46;
        private Double f50;

        public Double getF116() {
            return f116;
        }

        public void setF116(Double f116) {
            this.f116 = f116;
        }

        private Double f116;

        public Double getF50() {
            return f50;
        }

        public void setF50(Double f50) {
            this.f50 = f50;
        }

        public Double getF43() {
            return this.f43;
        }

        public void setF43(Double f43) {
            this.f43 = f43;
        }

        public Double getF45() {
            return this.f45;
        }

        public void setF45(Double f45) {
            this.f45 = f45;
        }

        public Double getF44() {
            return this.f44;
        }

        public void setF44(Double f44) {
            this.f44 = f44;
        }

        public String getF58() {
            return this.f58;
        }

        public void setF58(String f58) {
            this.f58 = f58;
        }

        public Double getF46() {
            return this.f46;
        }

        public void setF46(Double f46) {
            this.f46 = f46;
        }
    }
}
