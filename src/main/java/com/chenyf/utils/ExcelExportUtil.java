package com.chenyf.utils;

import com.chenyf.annotation.ExcelField;
import com.chenyf.entity.ExportParam;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: chenyf
 * @create: 2019-09-26 13:57
 * @description:
 **/
public class ExcelExportUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static ExcelExportUtil instance = new ExcelExportUtil();

    private ExcelExportUtil() {
    }

    static ExcelExportUtil getInstance(){
        return instance;
    }

    public <T> File exportExcel(List<T> list, Class<T> clazz, ExportParam param) {
        File file = new File("");
        Workbook wb = new SXSSFWorkbook();
        Sheet sheet = wb.createSheet(param.getSheet());

        if (list == null || list.size() == 0) {

        }



        OutputStream os =



        return wb.write();
    }

    private <T> void setTitle(Sheet sheet, int startRowNum, List<Field> fields) {
        if (fields == null || fields.size() == 0) {
            return;
        }
        Row row = sheet.createRow(startRowNum);
        for(Field field : fields) {



        }





        return wb.write();
    }

}
