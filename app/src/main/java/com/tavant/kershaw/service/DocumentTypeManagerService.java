package com.tavant.kershaw.service;

import java.util.List;
import java.util.Map;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.helper.RequestData;
import com.tavant.kershaw.vo.DocumentTypeVO;
import com.tavant.kershaw.vo.FieldVO;

public interface DocumentTypeManagerService {
	
	void createDocumentType(DocumentType docType);
	public List<DocumentTypeVO> getAllDocumentTypes();
	public Map<String,List<FieldVO>> getDocumentTypeById(Integer documentId);
	public void updateDocumentWithFields(List<RequestData> requestData);
	public void updateDocumentWithField(RequestData requestData);
}
