package com.jinge.helloworld;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Sample to show unit execute order.
 * Method execute order is not predictable. {@link FixMethodOrder}
 * According <a href="https://github.com/junit-team/junit4/wiki/test-execution-order">test-execution-order</a>,
 * From version 4.11, JUnit will by default use a deterministic,
 * but not predictable, order {@link MethodSorters#DEFAULT}.
 * <p>
 * Output of this example is :
 * <pre>
 * ACB
 * test1
 * output is 1
 *
 * ACB
 * test2
 * output is 2
 *
 * ACB
 * test3
 * output is 3
 * </pre>
 */

public class UnitOrderTest {
    private int num = 0;

    @Before
    public void acc() {
        System.out.print("A");
    }

    @Before
    public void ca() {
        System.out.print("C");
    }

    @Before
    public void bb() {
        System.out.print("B");
    }

    @Test
    public void test1() throws InterruptedException {
        System.out.println();
        System.out.println("test1");
        num += 1;
    }

    @Test
    public void test3() {
        System.out.println();
        System.out.println("test3");
        num += 3;
    }

    @Test
    public void test2() throws InterruptedException {
        System.out.println();
        System.out.println("test2");
        Thread.sleep(3000);
        num += 2;
        Thread.sleep(1000);
    }

    @After
    public void tearDown() {
        System.out.println("output is " + num + "\n");
        num = 0;
    }
}
