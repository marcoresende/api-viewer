package com.github.marcoresende.apiviewer.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.marcoresende.apiviewer.model.Api;
import com.github.marcoresende.apiviewer.model.Swagger;

public class ApiUtil {

	private static final String ROOT = ".";
	private static final String JSON_EXT = ".json";
	private static final String YML_EXT = ".yml";
	private static final String YAML_EXT = ".yaml";
	
	public static List<Api> getAvailableApis() {
		List<Api> apis = new ArrayList<Api>();

		for (File file : getSpecListFromPath()) {
			try {
				Api api = convertApiFromFile(file);

				if (api != null) {
					apis.add(api);
				}
			} catch (IllegalArgumentException ia) {
				ia.printStackTrace();
			}
		}
		return apis;
	}
	
	public static String getSpecForSwagger(String fileName){
		String spec = "";
		try {
			if(!fileName.toLowerCase().endsWith(JSON_EXT)){
				spec = convertYamlFileToJson(fileName);
			} else {
				spec = convertJsonFileToString(fileName);
			}
        } catch (Exception e) {
			e.printStackTrace();
		} 
		
		return spec;
	}
	
	public static String convertYamlFileToJson(String filePath) throws IOException {

	    ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
	    Object obj = yamlReader.readValue(new File(filePath), Object.class);

	    ObjectMapper jsonWriter = new ObjectMapper();
	    return jsonWriter.writeValueAsString(obj);
	}
	
	public static String convertJsonFileToString(String fileName) throws IOException {

	    ObjectMapper mapper = new ObjectMapper();
	    Object spec = mapper.readValue(new File(fileName), Object.class);

	    return mapper.writeValueAsString(spec);
	}
	
	public static String convertYamlFileToString(String filePath) throws IOException {
		ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
		Object obj = yamlReader.readValue(new File(filePath), Object.class);
	    
	    return yamlReader.writeValueAsString(obj);
	}
	
	public static List<File> getSpecListFromPath(){
		File rootPath = new File(ROOT);
		List<File> specFiles = new ArrayList<File>();
		
		if(rootPath.exists() && rootPath.isDirectory()){
			File[] files = rootPath.listFiles(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			        return name.toLowerCase().endsWith(JSON_EXT)
			        		|| name.toLowerCase().endsWith(YML_EXT)
			        		|| name.toLowerCase().endsWith(YAML_EXT);
			    }
			});
			
			if(files != null && files.length > 0){
				specFiles = Arrays.asList(files);
			}
		} else {
			System.out.println("WARNING: Path invalid or not exists.");
		}
		
		return specFiles;
	}
	
	public static Api convertApiFromFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException(
					"Illegal argument " + ApiUtil.class.getName() + " - convertApiFromFile(File file): null");
		}

		Api api = null;
		try {
			ObjectMapper mapper = file.getName().toLowerCase().endsWith(JSON_EXT) 
					? new ObjectMapper() : new ObjectMapper(new YAMLFactory()); 

			Swagger swg = mapper.readValue(file, Swagger.class);

			api = new Api();
			api.setName(swg.getInfo() != null ? swg.getInfo().getTitle() : "");
			api.setHost(swg.getHost() == null ? "localhost" : swg.getHost());
			api.setFile(URLEncoder.encode(file.getName(), "UTF-8"));
			api.setVersion(swg.getInfo() != null ? swg.getInfo().getVersion() : "");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return api;
	}
}
