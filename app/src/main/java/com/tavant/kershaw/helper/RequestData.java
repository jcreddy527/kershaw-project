package com.tavant.kershaw.helper;

public class RequestData {

	private int documentTypeId;
	private String fieldName;
	private String dataType;
	private String fieldValue;
	private String sectionName;
	private String fieldPossibleValue;
	
	public int getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getFieldPossibleValue() {
		return fieldPossibleValue;
	}
	public void setFieldPossibleValue(String fieldPossibleValue) {
		this.fieldPossibleValue = fieldPossibleValue;
	}
}
