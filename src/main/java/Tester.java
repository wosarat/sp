import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;

/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Tester.java 14.06.2013 18:05:33
 *********************************/

/**
 * @author starasov
 *
 */
public class Tester {
    private final static String PACKAGE_PREFIX="codeforces.round";
    enum Task{
        A,
        B,
        C,
        D,
        E;
    }
    public Tester(int round, Task task) throws Exception{
        @SuppressWarnings("unchecked")
        Class<? extends Runnable> taskClass = (Class<? extends Runnable>) Class.forName(PACKAGE_PREFIX+round+"."+task);
        Constructor<? extends Runnable> taskConstructor = taskClass.getConstructor(Reader.class, Writer.class);
    }
    public static void main(String[] args) throws Exception {
        new Tester(188, Task.A);
    }

}
