package com.tavant.kershaw.vo;

import java.util.List;
import java.util.Set;

public class DocumentTypeVO {
	
	private int documentTypeId;

	private String documentType;

	private String documentDescription;

	private Set<FieldVO> fields;
	
	private List<SectionVO> sections;
	
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

	public List<SectionVO> getSections() {
		return sections;
	}

	public void setSections(List<SectionVO> sections) {
		this.sections = sections;
	}

}
