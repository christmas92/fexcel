package com.chenyf.utils;

import com.chenyf.annotation.ExcelField;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: chenyf
 * @create: 2019-09-25 11:04
 * @description:
 **/
public class ExcelUtil {

    public <T> List<T> importExcel(InputStream inputStream, Class<T> clazz){
       return ExcelImportUtil.getInstance().importExcel(inputStream, clazz);
    };

    public <T> List<T> importExcel(File file, Class<T> clazz) {
        return ExcelImportUtil.getInstance().importExcel(file, clazz);
    };

    static List<Field> getExcelFields(Class clazz) {
        List<Field> fields = new ArrayList<>();
        Field[] allFields = clazz.getDeclaredFields();
        if (allFields.length == 0) {
            return fields;
        }
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExcelField.class)) {
                field.setAccessible(true);
                fields.add(field);
            }
        }
        return fields;
    }
}
