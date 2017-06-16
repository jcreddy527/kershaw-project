package com.tavant.kershaw.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.kershaw.dao.DocumentTypeManagerDAO;
import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.entity.DocumentTypeFieldMapping;
import com.tavant.kershaw.vo.DocumentTypeVO;
import com.tavant.kershaw.vo.FieldVO;

@Service
public class DocumentTypeManagerServiceImpl implements DocumentTypeManagerService{

	@Autowired
	private DocumentTypeManagerDAO documentTypeManagerDAO;
	
	public void createDocumentType(DocumentType documentType){
		documentTypeManagerDAO.createDocumentType(documentType);
	}

	@Override
	public List<DocumentTypeVO> getAllDocumentTypes() {
		List<DocumentType> dbDocs =  documentTypeManagerDAO.getAllDocumentTypes();
		List<DocumentTypeVO> responseList = new ArrayList<>();
		dbDocs.stream().forEach((record) -> {
			DocumentTypeVO dt = populateDocumentType(record);
			responseList.add(dt);
		});
		return responseList;
	}

	private DocumentTypeVO populateDocumentType(DocumentType record) {
		DocumentTypeVO dt = new DocumentTypeVO();
		dt.setDocumentTypeId(record.getDocumentTypeId());
		dt.setDocumentType(record.getDocumentType());
		dt.setDocumentDescription(record.getDocumentDescription());
		Set<DocumentTypeFieldMapping> fieldMappings = record.getDocumentTypeField();
		Set<FieldVO> fields = new HashSet<>();
		for(DocumentTypeFieldMapping fieldMapping : fieldMappings){
			FieldVO field = new FieldVO();
			field.setDataType(fieldMapping.getField().getDataType());
			field.setFieldId(fieldMapping.getField().getFieldId());
			field.setSections(fieldMapping.getField().getSections());
			field.setFieldPossibleValues(fieldMapping.getField().getFieldPossibleValues());
			fields.add(field);
		}
		dt.setFields(fields);
		return dt;
	}
	
	@Override
	public DocumentTypeVO getAllDocumentTypesById(Integer documentId) {
		DocumentType document =  documentTypeManagerDAO.getAllDocumentTypesById(documentId);
		return populateDocumentType(document);
	}
}
