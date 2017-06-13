package com.tavant.kershaw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="field")
public class Field {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="field_id")
	private int fieldId;
	
	@Column(name="field_name")
	private String fieldName;
	
	@Column(name="label")
	private String label;
	
	@Column(name="data_type")
	private String dataType;
	
	@Column(name="section_id")
	private int sectionId;
	
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	
	
}
