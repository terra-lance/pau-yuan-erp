package com.terrase.autocount.base;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountPostResponse {
	private List<String> successKeys;
	private Map<String, String> references;
	private List<String> errorMessages;
}
