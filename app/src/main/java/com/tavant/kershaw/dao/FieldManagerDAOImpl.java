package com.tavant.kershaw.dao;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tavant.kershaw.entity.DocumentType;
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
		
		Set<FieldPossibleValues> fpvSet = new HashSet<FieldPossibleValues>();
		FieldPossibleValues fpv = new FieldPossibleValues();
		String fieldPossibleValues = requestData.getFieldValue();
		List<String> fieldValuesArray = Arrays.asList(fieldPossibleValues.split(","));
		for(String fieldValue: fieldValuesArray){
			fpv.setFieldValue(fieldValue);
			fpv.setFieldId(dt.getDocumentField().get(0).getFieldId());	
			fpvSet.add(fpv);
		}
		fd.setFieldPossibleValue(fpvSet);
		List<Section> sectionList = entityManager.createQuery("from Section sec", Section.class).getResultList();
		Set<Section> setSection = new HashSet(sectionList);
		Set<Section> newSectionSet = new HashSet();
		for(Section sec : setSection){
			if(sec.getSectionName().equalsIgnoreCase(requestData.getSectionName())){
				newSectionSet.add(sec);
			}
		}
		fd.setSections(newSectionSet);
		List<Field> fieldList = dt.getDocumentField();
		fieldList.add(fd);
		dt.setDocumentField(fieldList);
		entityManager.merge(dt);
		
	}	
}
