package net.gaggs.assess.func;

import java.util.Arrays;

public class MedianFunction implements IStatsFunction {

    @Override
    public double calculate(int[] dataSet) {
        double median = 0;

        if(dataSet != null && dataSet.length > 0) {
            int[] arrCopy = dataSet.clone();

            // could be unsorted dataset
            Arrays.sort(arrCopy);
            int n = arrCopy.length;

            if (n % 2 == 1) {
                median = arrCopy[n / 2];
            } else { //then it is even
                median = (arrCopy[n / 2] + arrCopy[(n / 2) - 1]) / 2.0;
            }
        }

        return median;
    }
}
