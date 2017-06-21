package com.tavant.kershaw.service;

import java.util.List;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.helper.RequestData;
import com.tavant.kershaw.vo.DocumentTypeVO;
import com.tavant.kershaw.vo.SectionVO;

public interface DocumentTypeManagerService {
	
	void createDocumentType(DocumentType docType);
	public List<DocumentTypeVO> getAllDocumentTypes();
	public List<SectionVO> getSectionsByDocumentId(Integer documentId);
	public void updateDocumentWithFields(List<RequestData> requestData);
	public void updateDocumentWithField(RequestData requestData);
	public List<DocumentTypeVO> getDocumentTypesShallow();
	public void updateSectionFieldsValue(List<SectionVO> sections);
}
