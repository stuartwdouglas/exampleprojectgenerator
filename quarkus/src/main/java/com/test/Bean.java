package com.test;

import javax.enterprise.context.Dependent;

@Dependent
public class Bean {

    public String getMessage() {
        return "a";
    }

}
