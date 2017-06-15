package com.tavant.kershaw.dao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
		
		Field fd = new Field();
		fd.setFieldName(requestData.getFieldName());
		fd.setDataType(requestData.getDataType());
		
		DocumentType dt = new DocumentType();
		dt = entityManager.find(DocumentType.class, requestData.getDocumentTypeId());
		List<DocumentType> dtList = new ArrayList<DocumentType>();
		dtList.add(dt);
		
		List<Section> sectionList = entityManager.createQuery("from Section sec", Section.class).getResultList();
		Set setSection = new HashSet(sectionList);
		fd.setSections(setSection);
		
		List<Field> fieldList = new ArrayList<>();
		fieldList.add(fd);
		dt.setDocumentField(fieldList);
		entityManager.merge(dt);
		
		Set<FieldPossibleValues> fpvSet = new HashSet<FieldPossibleValues>();
		FieldPossibleValues fpv = new FieldPossibleValues();
		fpv.setFieldValue(requestData.getFieldValue());
		fpv.setFieldId(dt.getDocumentField().get(0).getFieldId());
		fpvSet.add(fpv);
		Iterator iter = fpvSet.iterator();
		while (iter.hasNext()) {
			entityManager.merge(iter.next());
		}
		
	}	
}
