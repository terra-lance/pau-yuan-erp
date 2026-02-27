package com.terrase.chartjs.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chart {
	public static final String CHART_LINE = "line";

	private String type;
	private ChartData data;
	private ChartOption chartOption;

	public String toJson() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(this);
		} catch (Exception ex) {
			return null;
		}
	}
}
