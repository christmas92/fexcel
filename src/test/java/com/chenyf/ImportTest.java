package com.chenyf;

import com.chenyf.utils.ExcelUtil;
import com.chenyf.view.TestView;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author: chenyf
 * @create: 2019-09-26 09:50
 * @description:
 **/
public class ImportTest {

    public static void main(String[] args) throws IOException {
        File file = new File("");
        String filePath = file.getCanonicalPath();

        file = new File(filePath + File.separator + "test.xlsx");

        FileInputStream fis = new FileInputStream(file);

        ExcelUtil excelUtil = new ExcelUtil();

        List<TestView> list = excelUtil.importExcel(fis, TestView.class);

        Gson gson = new Gson();
        System.out.println(gson.toJson(list));
    }
}
