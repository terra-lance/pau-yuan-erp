package com.terrase.chartjs.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChartDataset {
	private String label;
	private List<Object> data;
	private int borderWidth;
	
	public ChartDataset() {
		data = new ArrayList<>();
		borderWidth = 1;
	}
}
