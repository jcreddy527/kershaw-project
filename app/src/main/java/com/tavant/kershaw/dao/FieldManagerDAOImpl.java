package com.tavant.kershaw.dao;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.entity.DocumentTypeFieldMapping;
import com.tavant.kershaw.entity.Field;
import com.tavant.kershaw.entity.FieldPossibleValues;
import com.tavant.kershaw.entity.Section;
import com.tavant.kershaw.helper.RequestData;

@Transactional
@Repository
public class FieldManagerDAOImpl implements FieldManagerDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void createField(RequestData requestData){
		
		
		DocumentType dt = entityManager.find(DocumentType.class, requestData.getDocumentTypeId());
		
		Field fd = new Field();
		fd.setFieldName(requestData.getFieldName());
		fd.setDataType(requestData.getDataType());
		
		List<Section> dbSections = entityManager.createQuery("from Section sec", Section.class).getResultList();
		Set<Section> sectionsToSave = new HashSet();
		for(Section sec : dbSections){
			if(sec.getSectionName().equalsIgnoreCase(requestData.getSectionName())){
				sectionsToSave.add(sec);
			}
		}
		
		fd.setSections(sectionsToSave);
		Field savedField = entityManager.merge(fd);
		
		DocumentTypeFieldMapping docFieldMapping = new DocumentTypeFieldMapping();
		docFieldMapping.setDocumentType(dt);
		docFieldMapping.setField(savedField);
		docFieldMapping.setFieldValue(requestData.getFieldValue());
		dt.getDocumentTypeField().add(docFieldMapping);
		
		entityManager.merge(dt);
		
		if(!StringUtils.isEmpty(requestData.getFieldPossibleValue())){
			String fieldPossibleValues = requestData.getFieldPossibleValue();
			Set<FieldPossibleValues> fpvSet = new HashSet<FieldPossibleValues>();
			List<String> fieldValuesArray = Arrays.asList(fieldPossibleValues.split("/"));
			for(String fieldValue: fieldValuesArray){
				FieldPossibleValues fpv = new FieldPossibleValues();
				fpv.setFieldValue(fieldValue);
				fpv.setFieldId(savedField.getFieldId());
				entityManager.merge(fpv);
			}
		}
	}	
}
