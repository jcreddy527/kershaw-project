package com.tavant.kershaw.vo;

import java.util.Set;

public class DocumentTypeVO {
	
	private int documentTypeId;

	private String documentType;

	private String documentDescription;

	private Set<FieldVO> fields;

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

	public Set<FieldVO> getFields() {
		return fields;
	}

	public void setFields(Set<FieldVO> fields) {
		this.fields = fields;
	}
}
