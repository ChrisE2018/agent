
package com.chriseliot.agent;

import java.net.*;
import java.util.*;

//From https://softbork.com/blog/index.php?id=8
public class ClassScan
{
    public List<URL> getClasses ()
    {
	final List<URL> result = new ArrayList<> ();
	final ClassLoader contextClassLoader = Thread.currentThread ().getContextClassLoader ();
	final ClassLoader[] classloaders = {getClass ().getClassLoader (), contextClassLoader};
	for (int i = 0; i < classloaders.length; i++)
	{
	    if (classloaders[i] instanceof URLClassLoader)
	    {
		final URLClassLoader urlClassLoader = (URLClassLoader)classloaders[i];
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
