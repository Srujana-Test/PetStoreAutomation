package api.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestClass {

    @Test
    public void testMethodPass() {
        System.out.println("Test method that passes");
    }

    @Test
    public void testMethodFail() {
        System.out.println("Test method that fails");
        throw new AssertionError("Failing test deliberately");
    }

    @Test
    public void testMethodSkip() {
        System.out.println("Test method that is skipped");
        throw new SkipException("Skipping test deliberately");
    }
}
