package net.gaggs.assess.model;

public class StatsFuncRequest {
    public String getStatsType() {
        return statsType;
    }

    public void setStatsType(String statsType) {
        this.statsType = statsType;
    }

    public int[] getDataset() {
        return dataset;
    }

    public void setDataset(int[] dataset) {
        this.dataset = dataset;
    }

    String statsType;
    int[] dataset;


}
