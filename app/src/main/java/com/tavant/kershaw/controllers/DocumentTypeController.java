package com.tavant.kershaw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.service.DocumentTypeManagerService;

@Controller
public class DocumentTypeController {

	@Autowired
	private DocumentTypeManagerService documentTypeManagerService;
	
	  @RequestMapping(value="/create")
	  @ResponseBody
	  public String create() {
		  
		//Document Type will come from Client ,below code will be changed 
		DocumentType docType = new DocumentType();
		docType.setDocumentType("PayStub");
		docType.setDocumentDescription("PayStub");
		documentTypeManagerService.createDocumentType(docType);
	    return "Document Type Created succesfully!";
	  }
}
