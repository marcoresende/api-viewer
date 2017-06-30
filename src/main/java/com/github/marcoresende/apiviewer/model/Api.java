package com.github.marcoresende.apiviewer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "version", "host", "file", "enabled" })
public class Api {

	@JsonProperty("name")
	private String name;
	@JsonProperty("version")
	private String version;
	@JsonProperty("host")
	private String host;
	@JsonProperty("file")
	private String file;
	@JsonProperty("enabled")
	private String enabled;

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	@JsonProperty("host")
	public String getHost() {
		return host;
	}

	@JsonProperty("host")
	public void setHost(String host) {
		this.host = host;
	}

	@JsonProperty("file")
	public String getFile() {
		return file;
	}

	@JsonProperty("file")
	public void setFile(String file) {
		this.file = file;
	}

	@JsonProperty("enabled")
	public String getEnabled() {
		return enabled;
	}

	@JsonProperty("enabled")
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

}
