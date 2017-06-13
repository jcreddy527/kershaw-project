package com.tavant.kershaw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.service.DocumentTypeManagerService;

@RestController
@RequestMapping(value = "/documentType")
public class DocumentTypeController {

	@Autowired
	private DocumentTypeManagerService documentTypeManagerService;

	@CrossOrigin(origins ="*")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public DocumentType createDocumentType(@RequestBody DocumentType docType) {
		documentTypeManagerService.createDocumentType(docType);
		return docType;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getDocumentTypes", method = RequestMethod.GET)
	public Object getAllDocumentTypes() {
		Object documentTypeList = documentTypeManagerService.getAllDocumentTypes();
		return documentTypeList;
	}
}
