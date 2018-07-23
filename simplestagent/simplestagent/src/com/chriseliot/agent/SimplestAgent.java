
package com.chriseliot.agent;

import java.lang.instrument.Instrumentation;

public class SimplestAgent
{
    /**
     * Agent premain method.
     *
     * @param args
     * @param instrumentation
     */
    public static void premain (final String args, final Instrumentation instrumentation)
    {
	System.out.println ("SimplestAgent premain");
    }
}
