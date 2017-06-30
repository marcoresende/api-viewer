package com.github.marcoresende.apiviewer.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marcoresende.apiviewer.model.Api;
import com.github.marcoresende.apiviewer.model.Swagger;

@Controller
public class OverviewController {
	
	@RequestMapping("/")
    public String overview(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
			
		model.addAttribute("apis", getApis());
			
        return "overview";
    }
	
	@RequestMapping("/api/{file}/{call}")
    public String redirect(@PathVariable(value="file") String file, @PathVariable(value="call" ) String call, Model model) {
        try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			String response = "";
			for(String line; (line = reader.readLine()) != null; response += line);
        
			model.addAttribute("specFile", response);
			
			reader.close();
			fis.close();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "api";
    }
	
	/*@RequestMapping("/doc/api")
    public String api(Model model) {
		model.addAttribute("name", "bunda");
        return "api";
    }*/
	
	private List<Api> getApis(){
		
		FileInputStream fis;
		File curDir;
		BufferedReader reader;
		List<Api> apis = new ArrayList<Api>();
		try {
			curDir = new File(".");
			File[] files = curDir.listFiles(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			        return name.toLowerCase().endsWith(".json");
			    }
			});
			
			for(File file : files){
				fis = new FileInputStream(file.getName());
				if(fis != null){
					reader = new BufferedReader(new InputStreamReader(fis));
					
					ObjectMapper mapper = new ObjectMapper();
			    	
			    	Swagger swg = mapper.readValue(reader, Swagger.class);
			    	Api api = new Api();
			    	api.setName(swg.getInfo() != null ? swg.getInfo().getTitle() : "");
			    	api.setHost(swg.getHost() == null ? "default" : swg.getHost());
			    	api.setFile(file.getName());
			    	api.setVersion(swg.getInfo() != null ? swg.getInfo().getVersion() : "");

			    	apis.add(api);
			    	
			    	reader.close();
				}
				fis.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apis;
	}

}
