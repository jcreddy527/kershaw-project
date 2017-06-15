package com.tavant.kershaw.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "field")
public class Field {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "field_id")
	private int fieldId;

	@Column(name = "field_name")
	private String fieldName;

	@Column(name = "label")
	private String label;

	@Column(name = "data_type")
	private String dataType;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  
	@JoinColumn(name = "field_id")
	private Set<FieldPossibleValues> fieldPossibleValue;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "field_section", joinColumns = @JoinColumn(name = "field_id", referencedColumnName = "field_id"),
	inverseJoinColumns = @JoinColumn(name = "section_id", referencedColumnName = "section_id")) 
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

	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

	public Set<FieldPossibleValues> getFieldPossibleValue() {
		return fieldPossibleValue;
	}

	public void setFieldPossibleValue(Set<FieldPossibleValues> fieldPossibleValue) {
		this.fieldPossibleValue = fieldPossibleValue;
	}
	
}
