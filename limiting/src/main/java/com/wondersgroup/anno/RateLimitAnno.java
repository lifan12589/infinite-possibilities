package com.wondersgroup.anno;

import java.lang.annotation.*;

@Inherited   // 允许子类继承  元注解
@Documented  // 被 javadoc工具记录
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE}) //注解可能出现在Java程序中的语法位置
@Retention(RetentionPolicy.RUNTIME)  //注解保留时间，保留至运行时
public @interface RateLimitAnno {


}
