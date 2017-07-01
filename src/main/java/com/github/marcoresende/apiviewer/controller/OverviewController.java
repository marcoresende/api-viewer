package com.github.marcoresende.apiviewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.marcoresende.apiviewer.util.ApiUtil;

@Controller
public class OverviewController {
	
	@RequestMapping("/")
    public String overview(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
			
		model.addAttribute("apis", ApiUtil.getAvailableApis());
			
        return "overview";
    }
	
	@RequestMapping("/api/{file}")
    public String redirect(@PathVariable(value="file") String file, Model model) {
        try {
			model.addAttribute("specFile", ApiUtil.getSpecForSwagger(file));
        } catch (Exception e) {
			e.printStackTrace();
		}
        return "api";
    }
	
}
