package com.github.marcoresende.apiviewer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
"swagger",
"info",
"basePath",
"host"
})
public class Swagger {
	@JsonProperty("swagger")
	private String swagger;
	@JsonProperty("info")
	private InfoSwagger info;
	@JsonProperty("basePath")
	private String basePath;
	@JsonProperty("host")
	private String host;
	
	public String getSwagger() {
		return swagger;
	}
	public void setSwagger(String swagger) {
		this.swagger = swagger;
	}
	public InfoSwagger getInfo() {
		return info;
	}
	public void setInfo(InfoSwagger info) {
		this.info = info;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
}
