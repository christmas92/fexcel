package com.chenyf;

import com.chenyf.utils.ExcelUtil;
import com.chenyf.view.TestView;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/chenyifei/IdeaProjects/fexcel/test.xlsx");

        FileInputStream fis = new FileInputStream(file);

        ExcelUtil excelUtil = new ExcelUtil();

        List<TestView> list = excelUtil.importExcel(file, TestView.class);

        Gson gson = new Gson();
        System.out.println(gson.toJson(list));

    }
}
