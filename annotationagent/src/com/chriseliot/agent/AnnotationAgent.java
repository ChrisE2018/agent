
package com.chriseliot.agent;

import java.lang.annotation.Annotation;
import java.lang.instrument.Instrumentation;

public class AnnotationAgent
{
    private static final String[] IGNORE = {"java", "sun", "[", "com.sun", "com/sun"};

    /** Saved instrumentation object. */
    @SuppressWarnings ("unused")
    private static volatile Instrumentation instrumentation;

    /**
     * Agent premain method.
     *
     * @param args
     * @param instrumentation
     */
    public static void premain (final String args, final Instrumentation inst)
    {
	AnnotationAgent.instrumentation = inst;
	System.out.println ("AnnotationAgent premain");
	final Class<?>[] classes = instrumentation.getAllLoadedClasses ();
	for (final Class<?> c : classes)
	{
	    if (!ignoreClass (c))
	    {
		System.out.println ("Loaded Class: " + c.getName ());
	    }
	}
	final ClassLoader loader = inst.getClass ().getClassLoader ();
	for (final Class<?> c : instrumentation.getInitiatedClasses (loader))
	{
	    if (!ignoreClass (c))
	    {
		System.out.println ("Initiated Class: " + c.getName ());
	    }
	}
	inst.addTransformer (new ClassTransformer ());
    }

    public static boolean ignoreClass (final Class<?> cls)
    {
	return ignoreClass (cls.getName ());
    }

    public static boolean ignoreClass (final String className)
    {
	for (final String ignore : IGNORE)
	{
	    if (className.startsWith (ignore))
	    {
		return true;
	    }
	}
	return false;
    }

    public static void report (final Class<?> cls)
    {
	if (instrumentation == null)
	{
	    System.out.println ("Agent not initialized - premain did not run");
	    return;
	}
	final ClassLoader loader = cls.getClassLoader ();
	for (final Class<?> c : instrumentation.getInitiatedClasses (loader))
	{
	    if (!ignoreClass (c))
	    {
		System.out.println ("Report Initiated Class: " + c.getName ());
	    }
	}
	for (final Class<?> c : instrumentation.getAllLoadedClasses ())
	{
	    if (!ignoreClass (c))
	    {
		System.out.println ("Report Loaded Class: " + c.getName ());
	    }
	}
    }

    public static void reportAnnotations ()
    {
	if (instrumentation == null)
	{
	    System.out.println ("Agent not initialized - premain did not run");
	    return;
	}
	for (final Class<?> cls : instrumentation.getAllLoadedClasses ())
	{
	    final Annotation[] annotations = cls.getAnnotations ();
	    if (annotations.length > 0)
	    {
		if (!ignoreClass (cls))
		{
		    System.out.println ("Annotated Class: " + cls.getName ());
		    for (final Annotation annotation : annotations)
		    {
			System.out.println ("   Annotation: " + annotation);
		    }
		}
	    }
	}
    }
}
