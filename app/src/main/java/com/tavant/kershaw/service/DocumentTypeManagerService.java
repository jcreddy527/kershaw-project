package com.tavant.kershaw.service;

import java.util.List;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.vo.DocumentTypeVO;

public interface DocumentTypeManagerService {
	
	void createDocumentType(DocumentType docType);
	public List<DocumentTypeVO> getAllDocumentTypes();
	public DocumentTypeVO getAllDocumentTypesById(Integer documentId);
}
