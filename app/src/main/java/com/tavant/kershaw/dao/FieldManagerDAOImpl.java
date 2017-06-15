package com.tavant.kershaw.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.entity.Field;
import com.tavant.kershaw.entity.FieldPossibleValues;
import com.tavant.kershaw.helper.CommonFile;

@Transactional
@Repository
public class FieldManagerDAOImpl implements FieldManagerDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void createField(CommonFile commonData){
		
		Field fd = new Field();
		FieldPossibleValues fpv = new FieldPossibleValues();
		fpv.setFieldValue(commonData.getFieldValue());
		fd.setFieldName(commonData.getFieldName());
		fd.setDataType(commonData.getDataType());
//		fd.setFieldPossibleValue(fpv);
//		fd.setSections(sc);
	
		String sql = "select section_name, section_desc from kershaw_doc_type.section";
		Query query = entityManager.createNativeQuery(sql);
		List ll = query.getResultList();
		System.out.println(ll);
//		entityManager.persist(commonData);
	}	
}
