package com.terrase.chartjs.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChartOptionScale {
	private Boolean beginAtZero;
	private Boolean offset;
	private Boolean reverse;
	private Boolean stacked;
}
