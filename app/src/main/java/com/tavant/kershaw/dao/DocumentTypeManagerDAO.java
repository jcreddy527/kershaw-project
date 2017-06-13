package com.tavant.kershaw.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tavant.kershaw.entity.DocumentType;

@Transactional
@Repository
public class DocumentTypeManagerDAO implements IDocumentTypeManagerDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void createDocumentType(DocumentType documentType){
		entityManager.persist(documentType);
	}
}
