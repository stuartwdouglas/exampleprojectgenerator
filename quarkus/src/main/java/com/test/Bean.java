package com.test;

import javax.enterprise.context.Dependent;

@Dependent
@SomeQualifier("foo")
public class Bean {

    public String getMessage() {
        return "a";
    }

}
