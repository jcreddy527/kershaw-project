package com.tavant.kershaw.dao;

import java.util.List;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.entity.Field;
import com.tavant.kershaw.entity.FieldPossibleValues;
import com.tavant.kershaw.entity.Section;

public interface DocumentTypeManagerDAO {

	DocumentType createDocumentType(DocumentType documentType);
	public List<DocumentType> getAllDocumentTypes();
	public DocumentType getAllDocumentTypesById(Integer documentId);
	public List<Section> getFieldSections();
	public Field saveField(Field fd);
	public void updateDocumentWithField(Integer docTypeId,Field field,String fieldValue);
	public void saveFieldPossibleValues(FieldPossibleValues fpv);
	public Field getField(Integer fieldId);
	public void updateDocumentFieldWithValue(Integer docTypeId,Field field,String fieldValue);
}

