package net.gaggs.assess;


import net.gaggs.assess.func.IStatsFunction;
import net.gaggs.assess.func.MeanFunction;
import net.gaggs.assess.func.MedianFunction;
import net.gaggs.assess.func.ModeFunction;

import java.util.HashMap;

public class StatsFunctionFactory {

    static HashMap<String, IStatsFunction> mapStatsTypeVsFunc;

    static {
        mapStatsTypeVsFunc = new HashMap<String, IStatsFunction>();
        mapStatsTypeVsFunc.put("MEAN", new MeanFunction());
        mapStatsTypeVsFunc.put("MEDIAN", new MedianFunction());
        mapStatsTypeVsFunc.put("MODE", new ModeFunction());
    }

    public static double calculate(String statsType, int[] dataset) {
        IStatsFunction statFunc = mapStatsTypeVsFunc.get(statsType);
        if (statFunc == null) {
            throw new RuntimeException(String.format("Unsupported Function: %s Supported functions are %s", statsType, mapStatsTypeVsFunc.keySet()) );
        }
        else if(dataset.length == 0) {
            throw new RuntimeException(String.format("Failed - Stats Function: %s Empty Dataset: %s", statsType, dataset));
        }
        return statFunc.calculate(dataset);
    }

}