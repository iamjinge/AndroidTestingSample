package com.jinge.helloworld;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Sample to show unit execute order.
 * For {@link Before}s, reverse alphabet order.
 * For {@link Test}s and {@link After}, alphabet order.
 * <p>
 * Output of this example is :
 * <pre>
 * CBA
 * test1
 * output is 3
 *
 * CBA
 * test2
 * output is 4
 *
 * CBA
 * test3
 * output is 5
 * </pre>
 */

public class UnitOrderTest {
    private int num = 0;

    @Before
    public void a() {
        System.out.println("A");
    }

    @Before
    public void c() {
        System.out.print("C");
    }

    @Before
    public void b() {
        System.out.print("B");
    }

    @Test
    public void test1() throws InterruptedException {
        System.out.println("test1");
        num += 1;
    }

    @Test
    public void test3() {
        System.out.println("test3");
        num += 3;
    }

    @Test
    public void test2() throws InterruptedException {
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
