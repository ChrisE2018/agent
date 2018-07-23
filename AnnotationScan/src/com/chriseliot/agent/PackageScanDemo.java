
package com.chriseliot.agent;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

public class PackageScanDemo
{
    public static void main (final String[] args) throws ClassNotFoundException, IOException
    {
	final PackageScan packageScan = new PackageScan ();
	final List<Class<?>> classes = packageScan.getClasses ("com.chriseliot");
	for (final Class<?> cls : classes)
	{

	    final Annotation[] annotations = cls.getAnnotations ();
	    if (annotations.length > 0)
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
