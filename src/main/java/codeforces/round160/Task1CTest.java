/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Task1CTest.java 22.01.2013 21:59:35
 *********************************/
package codeforces.round160;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author starasov
 *
 */
public class Task1CTest {

    /**
     * Test method for {@link codeforces.round160.Task1C#solve(int, int)}.
     */
    @Test
    public void testSolve() {
        Assert.assertEquals(8, new Task1C().solve(4, 17));
        Assert.assertEquals(1, new Task1C().solve(4, 4));
        Assert.assertEquals(141093479, new Task1C().solve(7, 987654321));
        Assert.assertEquals(1000000000, new Task1C().solve(1, 1000000000));
        
    }

}
