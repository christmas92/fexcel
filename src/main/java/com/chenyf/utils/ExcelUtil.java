package com.chenyf.utils;

import com.chenyf.annotation.ExcelField;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: chenyf
 * @create: 2019-09-25 11:04
 * @description:
 **/
public class ExcelUtil {

    public <T> List<T> importExcel(File file, Class<T> clazz) {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(file);
        } catch (IOException e) {
            throw new RuntimeException("excel import error: ", e);
        }
        if (wb == null) {
            throw new RuntimeException("excel import error: Workbook is not existed");
        }
        return importExcel(wb, clazz);
    }

    public <T> List<T> importExcel(InputStream inputStream, Class<T> clazz) {
        Workbook wb = null;

        try {
            wb = WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("excel import error: ", e);
        }
        if (wb == null) {
            throw new RuntimeException("excel import error: Workbook is not existed");
        }
        return importExcel(wb, clazz);
    }

    private List<Field> getExcelFields(Class clazz) {
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

    private <T> List<T> importExcel(Workbook workbook, Class<T> clazz) {
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            throw new RuntimeException("excel import error: sheet is not existed");
        }

        List<T> resultList = new ArrayList<>();
        List<Field> fields = getExcelFields(clazz);

        int startRowNum = 0;
        int lastRowNum = sheet.getLastRowNum();
        if (startRowNum == lastRowNum) {
            return resultList;
        }

        for (int i = startRowNum; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            try {
                T obj = clazz.newInstance();
                if (row != null && fields.size() > 0) {
                    for (Field field : fields) {

                        ExcelField ef = field.getAnnotation(ExcelField.class);
                        int col = ef.col();


                        Cell cell = row.getCell(col);

                        if (java.util.Date.class == field.getType()) {
                            Date value = cell.getDateCellValue();


                        }


                        switch (cell.getCellType()) {
                            case NUMERIC:



                                break;
                            default:
                                break;
                        }

                        if (Date.class == field.getType()) {
                            field.set(obj, cell.getDateCellValue());
                        }

//                        if (String.class == field.getType()) {
//                            field.set(obj, cell.getStringCellValue());
//                        } else if ((Integer.TYPE == field.getType()) || (Integer.class == field.getType())) {
//                            field.set(obj, cell.getNumericCellValue());
//                        } else if ((Long.TYPE == field.getType()) || (Long.class == field.getType())) {
//                            field.set(obj, cell.getNumericCellValue());
//                        } else if ((Float.TYPE == field.getType()) || (Float.class == field.getType())) {
//                            field.set(obj, cell.getNumericCellValue());
//                        } else if ((Short.TYPE == field.getType()) || (Short.class == field.getType())) {
//                            field.set(obj, cell.getNumericCellValue());
//                        } else if ((Double.TYPE == field.getType()) || (Double.class == field.getType())) {
//                            field.set(obj, cell.getNumericCellValue());
//                        } else if (Character.TYPE == field.getType()) {
//                            cell.get
//                            if ((c != null) && (c.length() > 0)) {
//                                field.set(obj, Character
//                                        .valueOf(c.charAt(0)));
//                            }
//                        } else if () {
//
//                        }
//
//
//                        field.set(obj, cell.getStringCellValue());
                    }
                }
                resultList.add(obj);
            } catch (InstantiationException e) {
                throw new RuntimeException("excel import error: ", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("excel import error: ", e);
            }
        }
        return resultList;
    }
}
