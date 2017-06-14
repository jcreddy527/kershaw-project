package com.tavant.kershaw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
		String sql = "SELECT document_type_id,document_type,document_description FROM kershaw_doc_type.document_type";
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
		
	}
}
