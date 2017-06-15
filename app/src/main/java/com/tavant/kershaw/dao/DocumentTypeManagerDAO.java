package com.tavant.kershaw.dao;

import java.util.List;

import com.tavant.kershaw.entity.DocumentType;

public interface DocumentTypeManagerDAO {

	DocumentType createDocumentType(DocumentType documentType);
	public List<DocumentType> getAllDocumentTypes();
	public DocumentType getAllDocumentTypesById(Integer documentId);
}

