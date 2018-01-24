package com.jinge.helloworld;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link MyJavaClass}
 */
public class MyJavaClassTest {
    private MyJavaClass instance;

    @Before
    public void init() {
        instance = new MyJavaClass(MyJavaClass.CODE_OK, "OK");
    }

    @Test
    public void testSuccess() {
        System.out.println("hello world");
        assertTrue(instance.isSuccessful());
    }

    @Test
    public void testMsgEmpty() {
        assertFalse(MyJavaClass.isMsgEmpty(instance.getMsg()));
    }

    @After
    public void tearDown() {
        instance = null;
    }
}