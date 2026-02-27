package com.terrase.chartjs.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChartData {
	private List<String> labels;
	private List<ChartDataset> datasets;
	
	public ChartData() {
		labels = new ArrayList<>();
		datasets = new ArrayList<>();
	}
	
	public void addDataset(ChartDataset chartDataset) {
		datasets.add(chartDataset);
	}
}
