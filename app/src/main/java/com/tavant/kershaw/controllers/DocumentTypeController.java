package com.tavant.kershaw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DocumentTypeController {

	
	  @RequestMapping(value="/create")
	  @ResponseBody
	  public String create(String email, String name) {
	    return "Document Type Created succesfully!";
	  }
}
