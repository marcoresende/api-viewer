package com.github.marcoresende.apiviewer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "apis" })
public class ApiList {

	@JsonProperty("apis")
	private List<Api> apis = null;

	@JsonProperty("apis")
	public List<Api> getApis() {
		return apis;
	}

	@JsonProperty("apis")
	public void setApis(List<Api> apis) {
		this.apis = apis;
	}
}
