package com.chenyf.entity;

/**
 * @author: chenyf
 * @create: 2019-09-26 16:01
 * @description:
 **/
public class ExportParam {

    public static final String SHEET_NAME = "Sheet1";

    private String sheet;

    private boolean hasTitle;

    private int startRowNum;

    public ExportParam() {
        this.sheet = SHEET_NAME;
        this.hasTitle = true;
        this.startRowNum = 1;
    }

    public ExportParam(String sheet, boolean hasTitle) {
        this.sheet = sheet;
        this.hasTitle = hasTitle;
        this.startRowNum = (hasTitle ? 1 : 0);
    }

    public ExportParam(String sheet, boolean hasTitle, int startRowNum) {
        this.sheet = sheet;
        this.hasTitle = hasTitle;
        this.startRowNum = startRowNum;
    }

    public static String getSheetName() {
        return SHEET_NAME;
    }

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public boolean isHasTitle() {
        return hasTitle;
    }

    public void setHasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }
}
