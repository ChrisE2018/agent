
package com.chriseliot.agent;

import java.lang.annotation.*;

@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.TYPE)
public @interface Eliot
{
    public boolean enabled () default true;
}
