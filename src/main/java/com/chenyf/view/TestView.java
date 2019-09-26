package com.chenyf.view;

import com.chenyf.annotation.ExcelField;

import java.util.Date;

/**
 * @author: chenyf
 * @create: 2019-09-25 14:45
 * @description:
 **/
public class TestView {

    @ExcelField(name = "a", col = 0, pattern = "yyyy-MM-dd HH:mm:ss")
    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

}
