package com.tavant.kershaw.vo;

import java.util.ArrayList;
import java.util.List;

public class SectionVO {
	
	private int documentTypeId;
	private int sectionId;
	private String sectionName;
	private List<FieldVO> fields = new ArrayList<>();
	
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
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
}
