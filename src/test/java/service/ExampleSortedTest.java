package service;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/*Showing how to guarantee an execution order of tests.
Try to avoid this and respect the letter I, from 'tests should be (I)ndependent'*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExampleSortedTest {

    private static int counter = 0;

    @Test
    public void start() {
        counter = 1;
    }

    @Test
    public void verify() {
        Assert.assertEquals(1, counter);
    }

}
