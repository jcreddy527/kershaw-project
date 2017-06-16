package com.tavant.kershaw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="document_type_field_mapping")
public class DocumentTypeFieldMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="document_field_id")
	private int documentFieldId;
	
	@ManyToOne
    @JoinColumn(name = "document_type_id")  
	private DocumentType documentType;
	
	@ManyToOne
    @JoinColumn(name = "field_id")  
	private Field field;
	
	@Column(name = "field_value")
	private String fieldValue;

	public int getDocumentFieldId() {
		return documentFieldId;
	}

	public void setDocumentFieldId(int documentFieldId) {
		this.documentFieldId = documentFieldId;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
}
