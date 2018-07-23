
package com.chriseliot.agent;

import java.lang.instrument.*;
import java.security.ProtectionDomain;

public class ClassTransformer implements ClassFileTransformer
{
    @Override
    public byte[] transform (final ClassLoader loader, final String className, final Class<?> classBeingRedefined,
            final ProtectionDomain protectionDomain, final byte[] classfileBuffer) throws IllegalClassFormatException
    {
	if (!AnnotationAgent.ignoreClass (className))
	{
	    System.out.println ("AnnotationAgent ClassTransformer: " + className);
	}
	return classfileBuffer;
    }
}
