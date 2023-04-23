package net.gaggs.assess.func;

public class MeanFunction implements IStatsFunction {

    @Override
    public double calculate(int[] dataSet) {
        double sum = 0, mean = 0;
        if(dataSet != null && dataSet.length > 0) {
            for (int i = 0; i < dataSet.length; i++) {
                sum += dataSet[i];
            }
            mean = (double) sum / dataSet.length;
        }
        return mean;
    }
}
