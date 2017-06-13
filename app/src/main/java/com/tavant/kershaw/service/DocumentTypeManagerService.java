package com.tavant.kershaw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.kershaw.dao.IDocumentTypeManagerDAO;
import com.tavant.kershaw.entity.DocumentType;

@Service
public class DocumentTypeManagerService implements IDocumentTypeManagerService{

	@Autowired
	private IDocumentTypeManagerDAO documentTypeManagerDAO;
	
	public void createDocumentType(DocumentType docType){
		documentTypeManagerDAO.createDocumentType(docType);
	}
	
	public Object getAllDocumentTypes(){	
		return documentTypeManagerDAO.getAllDocumentTypes();
	}
}
