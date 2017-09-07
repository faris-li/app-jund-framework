package com.jund.framework.core.annotation;

import java.lang.annotation.*;

/**
 * Created by zhijund on 2017/9/2.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logger {

    String title();

    String data();
}
