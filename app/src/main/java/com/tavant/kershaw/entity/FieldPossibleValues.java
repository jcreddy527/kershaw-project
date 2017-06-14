package com.tavant.kershaw.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="field_possible_values")
public class FieldPossibleValues {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="field_value_id")
	private int fieldValueId;
	
	@Column(name="field_id")
	private int fieldId;
	
	@Column(name="field_value")
	private String fieldValue;
	
	public int getFieldValueId() {
		return fieldValueId;
	}
	public void setFieldValueId(int fieldValueId) {
		this.fieldValueId = fieldValueId;
	}
	public int getFieldId() {
		return fieldId;
	}
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
}
