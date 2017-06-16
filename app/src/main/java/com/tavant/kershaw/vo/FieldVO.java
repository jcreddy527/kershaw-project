package com.tavant.kershaw.vo;

import java.util.Set;

import com.tavant.kershaw.entity.FieldPossibleValues;
import com.tavant.kershaw.entity.Section;

public class FieldVO {

	private int fieldId;

	private String fieldName;

	private String dataType;
	
	private Set<FieldPossibleValues> fieldPossibleValues;
	
	private Set<Section> sections;

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
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

	public Set<FieldPossibleValues> getFieldPossibleValues() {
		return fieldPossibleValues;
	}

	public void setFieldPossibleValues(Set<FieldPossibleValues> fieldPossibleValues) {
		this.fieldPossibleValues = fieldPossibleValues;
	}

	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
	
}
