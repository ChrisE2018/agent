
package com.chriseliot.agent;

import java.net.URL;
import java.util.*;

public class ClassScanDemo
{
    public static void main (final String[] args)
    {
	final ClassScan classScan = new ClassScan ();
	final List<URL> urls = classScan.getClasses ();
	final Set<URL> selected = new LinkedHashSet<URL> ();
	for (final URL url : urls)
	{
	    final String urlName = url.toString ();
	    if (!urlName.contains ("/Library"))
	    {
		selected.add (url);
	    }
	}
	for (final URL url : selected)
	{
	    System.out.printf ("URL: %s %n", url);
	}
    }
}
