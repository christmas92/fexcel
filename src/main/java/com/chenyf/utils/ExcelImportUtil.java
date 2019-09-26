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
 * @create: 2019-09-26 13:57
 * @description:
 **/
public class ExcelImportUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static ExcelImportUtil instance = new ExcelImportUtil();

    private ExcelImportUtil() {
    }

    static ExcelImportUtil getInstance(){
        return instance;
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

    private <T> List<T> importExcel(Workbook workbook, Class<T> clazz) {
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            throw new RuntimeException("excel import error: sheet is not existed");
        }

        List<T> resultList = new ArrayList<>();
        List<Field> fields = ExcelUtil.getExcelFields(clazz);

        int startRowNum = 0;
        int lastRowNum = sheet.getLastRowNum();
        if (startRowNum > lastRowNum) {
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
                        Object fieldType = field.getType();

                        if (Date.class == fieldType) {
                            if (CellType.STRING.equals(cell.getCellType())) {
                                DateFormat fmt = DATE_FORMAT;
                                if (ef.pattern() != "") {
                                    fmt = new SimpleDateFormat(ef.pattern());
                                }
                                Date date = fmt.parse(cell.getStringCellValue());
                                field.set(obj, date);
                            } else {
                                cell.setCellType(CellType.NUMERIC);
                                field.set(obj, cell.getDateCellValue());
                            }
                        } else if (Long.class == fieldType || Long.TYPE == fieldType) {
                            if (CellType.STRING.equals(cell.getCellType())) {
                                field.set(obj, Long.valueOf(cell.getStringCellValue()));
                            } else {
                                cell.setCellType(CellType.NUMERIC);
                                field.set(obj, (long) cell.getNumericCellValue());
                            }
                        } else if (Integer.class == fieldType || Integer.TYPE == fieldType) {
                            if (CellType.STRING.equals(cell.getCellType())) {
                                field.set(obj, Integer.valueOf(cell.getStringCellValue()));
                            } else {
                                cell.setCellType(CellType.NUMERIC);
                                field.set(obj, (int) cell.getNumericCellValue());
                            }
                        } else if (Short.class == fieldType || Short.TYPE == fieldType) {
                            if (CellType.STRING.equals(cell.getCellType())) {
                                field.set(obj, Short.valueOf(cell.getStringCellValue()));
                            } else {
                                cell.setCellType(CellType.NUMERIC);
                                field.set(obj, (short) cell.getNumericCellValue());
                            }
                        } else if (Double.class == fieldType || Double.TYPE == fieldType) {
                            if (CellType.STRING.equals(cell.getCellType())) {
                                field.set(obj, Double.valueOf(cell.getStringCellValue()));
                            } else {
                                cell.setCellType(CellType.NUMERIC);
                                field.set(obj, cell.getNumericCellValue());
                            }
                        } else if (Float.class == fieldType || Float.TYPE == fieldType) {
                            if (CellType.STRING.equals(cell.getCellType())) {
                                field.set(obj, Float.valueOf(cell.getStringCellValue()));
                            } else {
                                cell.setCellType(CellType.NUMERIC);
                                field.set(obj, (float) cell.getNumericCellValue());
                            }
                        } else if (Boolean.class == fieldType || Boolean.TYPE == fieldType) {
                            if (CellType.BOOLEAN.equals(cell.getCellType())) {
                                field.set(obj, cell.getBooleanCellValue());
                            } else if (CellType.STRING.equals(cell.getCellType())) {
                                cell.setCellType(CellType.BOOLEAN);
                                field.set(obj, cell.getBooleanCellValue());
                            } else if (CellType.NUMERIC.equals(cell.getCellType())) {
                                field.set(obj, cell.getNumericCellValue() != 0);
                            }
                        } else if (String.class == fieldType) {
                            if (CellType.STRING.equals(cell.getCellType())) {
                                field.set(obj, cell.getStringCellValue());
                            }
                        }
                    }
                }
                resultList.add(obj);
            } catch (InstantiationException e) {
                throw new RuntimeException("excel import error: ", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("excel import error: ", e);
            } catch (ParseException e) {
                throw new RuntimeException("excel import error: ", e);
            }
        }
        return resultList;
    }
}
