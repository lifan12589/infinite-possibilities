package com.infinitePossibilities.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询方式的自定义注解
 *
 * @author 154594742@qq.com
 * @date 2021/2/23 11:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface QueryMethod {

    /**
     * 数据表字段名
     */
    String field() default "";

    /**
     * 匹配方式
     */
    String method() default "";
}
