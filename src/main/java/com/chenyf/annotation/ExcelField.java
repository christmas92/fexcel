package com.chenyf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenyf
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelField {

    /**
     * 列字段名
     *
     * @return
     */
    String name() default "";

    /**
     * 列
     *
     * @return
     */
    int col() default 0;

}
