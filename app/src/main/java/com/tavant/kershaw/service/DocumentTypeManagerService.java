package com.tavant.kershaw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.kershaw.dao.IDocumentTypeManagerDAO;
import com.tavant.kershaw.entity.DocumentType;

@Service
public class DocumentTypeManagerService implements IDocumentTypeManagerService{

	@Autowired
	private IDocumentTypeManagerDAO documentTypeManagerDAO;
	
	public void createDocumentType(DocumentType documentType){
		documentTypeManagerDAO.createDocumentType(documentType);
	}
}
