package com.terrase.chartjs.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChartOption {
	private ChartOptionScale x;
	private ChartOptionScale y;
}
