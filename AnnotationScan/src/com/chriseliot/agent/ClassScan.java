
package com.chriseliot.agent;

import java.net.*;
import java.util.*;

//From https://softbork.com/blog/index.php?id=8
public class ClassScan
{
    /**
     * Find base URLS of all class locations. This will find all jar files and directories on the
     * class path. These could be opened up and searched recursively to find class files.
     */
    public List<URL> getClasses ()
    {
	final List<URL> result = new ArrayList<> ();
	final Set<ClassLoader> classLoaders = new LinkedHashSet<> ();
	classLoaders.add (getClass ().getClassLoader ());
	classLoaders.add (Thread.currentThread ().getContextClassLoader ());
	for (final ClassLoader cl : classLoaders)
	{
	    if (cl instanceof URLClassLoader)
	    {
		@SuppressWarnings ("resource")
		final URLClassLoader urlClassLoader = (URLClassLoader)cl;
		result.addAll (Arrays.asList (urlClassLoader.getURLs ()));
	    }
	    else
	    {
		throw new RuntimeException ("classLoader is not an instanceof URLClassLoader");
	    }
	}
	return result;
    }

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
