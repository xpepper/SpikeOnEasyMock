/*
 * Copyright (c) 2001-2007 OFFIS, Tammo Freese.
 * This program is made available under the terms of the MIT License.
 */
package org.easymock.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ObjectMethodsFilter implements InvocationHandler {
    private final Method equalsMethod;

    private final Method hashCodeMethod;

    private final Method toStringMethod;

    private final MockInvocationHandler delegate;

    private final String name;

    public ObjectMethodsFilter(Class toMock, MockInvocationHandler delegate, String name) {
        if (name != null && !Invocation.isJavaIdentifier(name)) {
            throw new IllegalArgumentException(String.format("'%s' is not a valid Java identifier.", name));

        }
        try {
            if (toMock.isInterface()) {
                toMock = Object.class;
            }
            equalsMethod = toMock.getMethod("equals", new Class[] { Object.class });
            hashCodeMethod = toMock.getMethod("hashCode", (Class[]) null);
            toStringMethod = toMock.getMethod("toString", (Class[]) null);
        } catch (NoSuchMethodException e) {
            // ///CLOVER:OFF
            throw new RuntimeException("An Object method could not be found!");
            // ///CLOVER:ON
        }
        this.delegate = delegate;
        this.name = name;
    }

    public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (equalsMethod.equals(method)) {
            return Boolean.valueOf(proxy == args[0]);
        }
        if (hashCodeMethod.equals(method)) {
            return new Integer(System.identityHashCode(proxy));
        }
        if (toStringMethod.equals(method)) {
            return mockToString(proxy);
        }
        return delegate.invoke(proxy, method, args);
    }

    private String mockToString(Object proxy) {
        return (name != null) ? name : "EasyMock for " + mockType(proxy);
    }

    private String mockType(Object proxy) {
        if (Proxy.isProxyClass(proxy.getClass()))
            return proxy.getClass().getInterfaces()[0].toString();
        else
            return proxy.getClass().getSuperclass().toString();
    }

    public MockInvocationHandler getDelegate() {
        return delegate;
    }
}