package com.ps;

/**
 * Created by iuliana.cosmina on 2/19/17.
 */

import org.springframework.core.annotation.AliasFor;

public @interface DsCfg {
    int loginTimeout() default 3600;

    @AliasFor(attribute = "loginTimeout")
    int lTout() default 3600;
}
