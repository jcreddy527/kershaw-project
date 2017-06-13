package com.tavant.kershaw.service;

import java.util.List;

import com.tavant.kershaw.entity.DocumentType;

public interface IDocumentTypeManagerService {
	
	void createDocumentType(DocumentType docType);
	public Object getAllDocumentTypes();
}
