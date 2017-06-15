package com.tavant.kershaw.service;

import java.util.List;

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

	@Override
	public Object getAllDocumentTypes() {
		List<DocumentType> allDocumentList =  documentTypeManagerDAO.getAllDocumentTypes();
		return allDocumentList;
	}
	
	@Override
	public DocumentType getAllDocumentTypesById(Integer documentId) {
		DocumentType document =  documentTypeManagerDAO.getAllDocumentTypesById(documentId);
		return document;
	}
}
