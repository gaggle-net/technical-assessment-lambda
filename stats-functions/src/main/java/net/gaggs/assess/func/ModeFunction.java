package net.gaggs.assess.func;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class ModeFunction implements IStatsFunction {

    @Override
    public double calculate(int[] dataSet) {
        double mode = 0;

        if(dataSet != null && dataSet.length > 0) {
            int[] arrCopy = dataSet.clone();
            // to get larger number as mode if there is a tie
            Arrays.sort(arrCopy);

            Map<Integer, Integer> freq = new TreeMap<>();
            for (int i : arrCopy) {
                if (!freq.containsKey(i))
                    freq.put(i, 1);
                else
                    freq.put(i, freq.get(i) + 1);
            }

            mode = arrCopy[0];
            int maxFreq = 1;

            for (Map.Entry<Integer, Integer> me : freq.entrySet()) {
                if (me.getValue() >= maxFreq) {
                    maxFreq = me.getValue();
                    mode = me.getKey();
                }
            }

        }
        return mode;
    }
}
