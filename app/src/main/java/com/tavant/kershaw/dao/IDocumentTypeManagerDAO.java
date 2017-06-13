package com.tavant.kershaw.dao;

import java.util.List;

import com.tavant.kershaw.entity.DocumentType;

public interface IDocumentTypeManagerDAO {

	DocumentType createDocumentType(DocumentType documentType);
	public List<DocumentType> getAllDocumentTypes();
}
