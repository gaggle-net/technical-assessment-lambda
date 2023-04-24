package net.gaggs.assess.func;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedianFunctionTest {

    IStatsFunction functionInTest = new MedianFunction();

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
        double returnedValue = functionInTest.calculate(new int[] {6});
        assertEquals(6, returnedValue);
    }

    @Test
    public void When_DataSet_with_oddNumbersOfValues_then_ReturnExpectedValue() {
        double returnedValue = functionInTest.calculate(new int[] {1,5,7,4,2,3,6});
        assertEquals(4, returnedValue); //1,2,3,4,5,6,7
    }

    @Test
    public void When_DataSet_with_evenNumbersOfValues_then_ReturnExpectedValue() {
        double returnedValue = functionInTest.calculate(new int[] {10,9,4,22,30,11});
        assertEquals(10.5, returnedValue); //4,9,10,11,22,33
    }

}