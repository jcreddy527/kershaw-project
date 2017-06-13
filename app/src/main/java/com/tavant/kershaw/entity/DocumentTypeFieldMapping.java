package com.tavant.kershaw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="document_type_field_mapping")
public class DocumentTypeFieldMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="document_field_id")
	private int documentFieldId;
	
	@Column(name="document_type_id")
	private int documentTypeId;
	
	@Column(name="field_id")
	private int fieldId;
	
	public int getDocumentFieldId() {
		return documentFieldId;
	}
	public void setDocumentFieldId(int documentFieldId) {
		this.documentFieldId = documentFieldId;
	}
	public int getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public int getFieldId() {
		return fieldId;
	}
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
	
	
}
