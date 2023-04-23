package net.gaggs.assess.func;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModeFunctionTest {

    IStatsFunction functionInTest = new ModeFunction();

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
        double returnedValue = functionInTest.calculate(new int[] {1,4,5,7,4,2,5,3,6,5});
        assertEquals(5, returnedValue); //1,2,3,4,5,6,7
    }

    @Test
    public void When_DataSet_with_moreThenOneNumberHaveSameOccuranceFreq_then_ReturnTheBiggerValue() {
        double returnedValue = functionInTest.calculate(new int[] {1,8,4,5,7,4,8,2,5,3,6,8,5});
        assertEquals(8, returnedValue); //4,9,10,11,22,33
    }

}