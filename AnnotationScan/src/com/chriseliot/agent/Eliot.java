
package com.chriseliot.agent;

import java.lang.annotation.*;

// http://tutorials.jenkov.com/java-reflection/annotations.html
// https://www.mkyong.com/java/java-custom-annotations-example/
@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.TYPE)
public @interface Eliot
{
    // should ignore this test?
    public boolean enabled () default true;

}
