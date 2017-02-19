package com.ps;

import org.springframework.core.annotation.AliasFor;

/**
 * Created by iuliana.cosmina on 5/2/16.
 */
@DsCfg
public @interface MyDsCfg {

    @AliasFor(annotation = DsCfg.class, attribute = "lTout")
    int timeout() default 200;
}
