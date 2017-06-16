package com.tavant.kershaw.vo;

import java.util.List;

public class SectionVO {
	
	private int documentId;
	private String sectionName;
	private List<FieldVO> fields;
	
	public int getDocumentId() {
		return documentId;
	}
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public List<FieldVO> getFields() {
		return fields;
	}
	public void setFields(List<FieldVO> fields) {
		this.fields = fields;
	}
}
