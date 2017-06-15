package com.tavant.kershaw.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tavant.kershaw.entity.DocumentType;

@Transactional
@Repository
public class DocumentTypeManagerDAOImpl implements DocumentTypeManagerDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	public DocumentType createDocumentType(DocumentType documentType){
		entityManager.persist(documentType);
		return documentType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentType> getAllDocumentTypes() {		
		List<DocumentType> documentTypeList = entityManager.createQuery("from DocumentType dt", DocumentType.class).getResultList();
		System.out.println(documentTypeList);
		if (documentTypeList.size() > 0 && documentTypeList != null) {
			return documentTypeList;
		}
		return new ArrayList<>();
		
	}
}
