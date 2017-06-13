package com.tavant.kershaw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.kershaw.dao.DocumentTypeManagerDAO;
import com.tavant.kershaw.entity.DocumentType;

@Service
public class DocumentTypeManagerServiceImpl implements DocumentTypeManagerService{

	@Autowired
	private DocumentTypeManagerDAO documentTypeManagerDAO;
	
	public void createDocumentType(DocumentType documentType){
		documentTypeManagerDAO.createDocumentType(documentType);
	}
}
