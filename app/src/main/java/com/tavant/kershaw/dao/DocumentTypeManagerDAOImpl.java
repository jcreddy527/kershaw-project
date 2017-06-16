package com.tavant.kershaw.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.entity.DocumentTypeFieldMapping;
import com.tavant.kershaw.entity.Field;
import com.tavant.kershaw.entity.FieldPossibleValues;
import com.tavant.kershaw.entity.Section;

@Transactional
@Repository
public class DocumentTypeManagerDAOImpl implements DocumentTypeManagerDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public DocumentType createDocumentType(DocumentType documentType){
		entityManager.persist(documentType);
		return documentType;
	}
	
	@Override
	public List<DocumentType> getAllDocumentTypes() {		
		List<DocumentType> documentTypeList = entityManager.createQuery("from DocumentType dt", DocumentType.class).getResultList();
		if (!CollectionUtils.isEmpty(documentTypeList)) {
			return documentTypeList;
		}
		return new ArrayList<>();
		
	}
	
	@Override
	public DocumentType getAllDocumentTypesById(Integer documentId) {		
		DocumentType documentType = entityManager.find(DocumentType.class, documentId);
		return documentType;
	}
	
	@Override
	public void updateDocumentWithField(Integer docTypeId,Field field,String fieldValue){
		DocumentType dt = entityManager.find(DocumentType.class, docTypeId);
		
		DocumentTypeFieldMapping docFieldMapping = new DocumentTypeFieldMapping();
		docFieldMapping.setDocumentType(dt);
		docFieldMapping.setField(field);
		docFieldMapping.setFieldValue(fieldValue);
		dt.getDocumentTypeField().add(docFieldMapping);
		entityManager.merge(dt);
	}

	@Override
	public void saveFieldPossibleValues(FieldPossibleValues fpv) {
		entityManager.merge(fpv);
	}

	@Override
	public List<Section> getFieldSections() {
		List<Section> dbSections = entityManager.createQuery("from Section sec", Section.class).getResultList();
		return dbSections;
	}	
	
	@Override
	public Field saveField(Field fd) {
		Field savedField = entityManager.merge(fd);
		return savedField;
	}
}
