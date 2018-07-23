
package com.chriseliot.agent;

import java.lang.reflect.Field;
import java.util.Vector;

public class Demo
{
    public static void main (final String[] args)
    {
	System.out.println ("Agent Demo main method");
	// final Demo demo = new Demo ();
	// demo.listFiles ();
	// AnnotationAgent.report (Demo.class);
	listClasses ();
	System.out.println (new Blah ());
	AnnotationAgent.report (Demo.class);
	AnnotationAgent.reportAnnotations ();
    }

    public static void listClasses ()
    {
	try
	{
	    final Field f = ClassLoader.class.getDeclaredField ("classes");
	    f.setAccessible (true);
	    final ClassLoader classLoader = Thread.currentThread ().getContextClassLoader ();
	    @SuppressWarnings ("unchecked")
	    final Vector<Class<?>> classes = (Vector<Class<?>>)f.get (classLoader);

	    for (final Class<?> cls : classes)
	    {
		System.out.println (cls.getName ());
	    }
	}
	catch (final Exception e)
	{

	    e.printStackTrace ();
	}
    }

    // private void listFiles ()
    // {
    // try
    // {
    // // create new file
    // final File file = new File ("AgentTest");
    //
    // // returns pathnames for files and directory
    // final File[] paths = file.listFiles ();
    // if (paths != null)
    // {
    // // for each pathname in pathname array
    // for (final File path : paths)
    // {
    // // prints file and directory paths
    // System.out.println (path);
    // }
    // }
    // }
    // catch (final Exception e)
    // {
    // // if any error occurs
    // e.printStackTrace ();
    // }
    // }

    @Override
    public String toString ()
    {
	final StringBuilder buffer = new StringBuilder ();
	buffer.append ("#<");
	buffer.append (getClass ().getSimpleName ());
	buffer.append (" ");
	buffer.append (System.identityHashCode (this));
	buffer.append (">");
	return buffer.toString ();
    }
}
