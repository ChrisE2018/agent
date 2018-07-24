
package com.chriseliot.agent;

import java.io.*;
import java.net.URL;
import java.util.*;

//From https://dzone.com/articles/get-all-classes-within-package
public class PackageScan
{
    private static final String DOT_CLASS = ".class";

    /**
     * Scans all classes accessible from the context class loader which belong to the given package
     * and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public List<Class<?>> getClasses (final String packageName) throws ClassNotFoundException, IOException
    {
	final ClassLoader classLoader = Thread.currentThread ().getContextClassLoader ();
	assert classLoader != null;
	final String path = packageName.replace ('.', '/');
	final Enumeration<URL> resources = classLoader.getResources (path);
	final List<File> dirs = new ArrayList<> ();
	while (resources.hasMoreElements ())
	{
	    final URL resource = resources.nextElement ();
	    dirs.add (new File (resource.getFile ()));
	}
	final List<Class<?>> result = new ArrayList<> ();
	for (final File directory : dirs)
	{
	    result.addAll (findClasses (directory, packageName));
	}
	return result;
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private List<Class<?>> findClasses (final File directory, final String packageName) throws ClassNotFoundException
    {
	final List<Class<?>> result = new ArrayList<> ();
	if (directory.exists ())
	{
	    final File[] files = directory.listFiles ();
	    for (final File file : files)
	    {
		final String filename = file.getName ();
		if (file.isDirectory ())
		{
		    result.addAll (findClasses (file, packageName + "." + filename));
		}
		else if (filename.endsWith (DOT_CLASS))
		{
		    final String basename = filename.substring (0, filename.length () - DOT_CLASS.length ());
		    final String classname = packageName + '.' + basename;
		    result.add (Class.forName (classname));
		}
	    }
	}
	return result;
    }
}
