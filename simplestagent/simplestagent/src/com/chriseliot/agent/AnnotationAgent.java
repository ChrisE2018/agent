
package com.chriseliot.agent;

import java.lang.instrument.Instrumentation;

public class AnnotationAgent
{
    /**
     * Agent premain method.
     *
     * @param args
     * @param instrumentation
     */
    public static void premain (final String args, final Instrumentation instrumentation)
    {
	System.out.println ("AnnotationAgent premain");
    }
}
