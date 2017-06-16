package com.tavant.kershaw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.service.DocumentTypeManagerService;
import com.tavant.kershaw.vo.DocumentTypeVO;

@RestController
@RequestMapping(value = "/documentType")
public class DocumentTypeController {

	@Autowired
	private DocumentTypeManagerService documentTypeManagerService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public DocumentType createDocumentType(@RequestBody DocumentType docType) {
		documentTypeManagerService.createDocumentType(docType);
		return docType;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getDocumentTypes", method = RequestMethod.GET)
	public List<DocumentTypeVO> getAllDocumentTypes() {
		List<DocumentTypeVO> documentTypeList = documentTypeManagerService.getAllDocumentTypes();
		return documentTypeList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/getDocumentTypesById/{documentTypeId}", method = RequestMethod.GET)
	public DocumentTypeVO getAllDocumentTypesById(@PathVariable("documentTypeId") Integer documentTypeId) {
		DocumentTypeVO documentTypeList = documentTypeManagerService.getAllDocumentTypesById(documentTypeId);
		return documentTypeList;
	}
}
