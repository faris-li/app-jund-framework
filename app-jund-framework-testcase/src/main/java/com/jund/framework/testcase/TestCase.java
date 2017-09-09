package com.jund.framework.testcase;

/**
 * Created by zhijund on 2017/9/2.
 */
public abstract class TestCase {

    abstract void testPage() throws Exception;

    abstract void testFindOne() throws Exception;

    abstract void testSave() throws Exception;

    abstract void testDelete() throws Exception;

}
