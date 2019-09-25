package com.chenyf;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: chenyf
 * @create: 2019-07-15 14:05
 * @description:
 **/
public class Teest {


    public static void main(String[] args) throws IOException {
        File file = new File("./test.xlsx");

        FileInputStream fis = new FileInputStream(file);

        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheetAt(0);

        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);

        System.out.println(cell.getCellType());

//        ExcelUtil excelUtil = new ExcelUtil();
//        List<TestView> list = excelUtil.importExcel(file, TestView.class);
//
//        Gson gson = new Gson();
//        System.out.println(gson.toJson(list));

    }
}
