package com.tavant.kershaw.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "document_type")
public class DocumentType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "document_type_id")
	private int documentTypeId;

	@Column(name = "document_type")
	private String documentType;

	@Column(name = "document_description")
	private String documentDescription;
	
	@OneToMany(mappedBy = "documentType",cascade=CascadeType.ALL)
	private Set<DocumentTypeFieldMapping> documentTypeFields;
	

	public Set<DocumentTypeFieldMapping> getDocumentTypeField() {
		return documentTypeFields;
	}

	public void setDocumentTypeField(Set<DocumentTypeFieldMapping> documentTypeField) {
		this.documentTypeFields = documentTypeField;
	}

	public int getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentDescription() {
		return documentDescription;
	}

	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}

}
