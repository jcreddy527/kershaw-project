package com.tavant.kershaw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tavant.kershaw.entity.DocumentType;

@Repository
public interface DocumentTypeRepository extends CrudRepository<DocumentType, Integer> {
	@Query("select dt.documentTypeId,dt.documentType from DocumentType dt ")
	public List<DocumentType[]> getAllDocuments();
}
