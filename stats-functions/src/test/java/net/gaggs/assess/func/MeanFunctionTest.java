package net.gaggs.assess.func;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeanFunctionTest {

    IStatsFunction functionInTest = new MeanFunction();

    @Test
    public void When_NullDataSet_then_ReturnZero() {
        double returnedValue = functionInTest.calculate(null);
        assertEquals(0, returnedValue);
    }

    @Test
    public void When_EmptyDataSet_then_ReturnZero() {
        double returnedValue = functionInTest.calculate(new int[] {});
        assertEquals(0, returnedValue);
    }

    @Test
    public void When_DataSet_with_zero_AsTheOnlyValue_then_ReturnZero() {
        double returnedValue = functionInTest.calculate(new int[] {0});
        assertEquals(0, returnedValue);
    }

    @Test
    public void When_DataSet_with_One_AsTheOnlyValue_then_ReturnOne() {
        double returnedValue = functionInTest.calculate(new int[] {1});
        assertEquals(1, returnedValue);
    }

    @Test
    public void When_DataSet_with_moreThanOneData_then_ReturnExpectedValue() {
        double returnedValue = functionInTest.calculate(new int[] {2, -5, 6, 8, 10});
        assertEquals(4.2, returnedValue);
    }

}