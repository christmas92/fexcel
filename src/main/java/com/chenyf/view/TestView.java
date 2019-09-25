package com.chenyf.view;

import com.chenyf.annotation.ExcelField;

import java.util.Date;

/**
 * @author: chenyf
 * @create: 2019-09-25 14:45
 * @description:
 **/
public class TestView {

    @ExcelField(name = "a", col = 0)
    private String a;
    @ExcelField(name = "b", col = 1)
    private Integer b;
    @ExcelField(name = "c", col = 2)
    private Long c;
    @ExcelField(name = "d", col = 3)
    private Double d;
    @ExcelField(name = "e", col = 4)
    private Boolean e;
    @ExcelField(name = "f", col = 5)
    private Date date;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Long getC() {
        return c;
    }

    public void setC(Long c) {
        this.c = c;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Boolean getE() {
        return e;
    }

    public void setE(Boolean e) {
        this.e = e;
    }
}
