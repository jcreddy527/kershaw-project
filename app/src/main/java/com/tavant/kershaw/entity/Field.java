package com.tavant.kershaw.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	
	@Column(name = "field_value")
	private String fieldValue;

	@ManyToMany(mappedBy = "documentField" , cascade = CascadeType.ALL) 
	private List<DocumentType> documentType;
	
	@OneToMany(fetch = FetchType.LAZY)  
	@JoinColumn(name = "field_value_id")
	private FieldPossibleValues fieldPossibleValue;

	@ManyToOne(fetch = FetchType.LAZY)
	private List<Section> sections;
	
	public List<DocumentType> getDocumentType() {
		return documentType;
	}

	public void setDocumentType(List<DocumentType> documentType) {
		this.documentType = documentType;
	}

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

	public FieldPossibleValues getFieldPossibleValue() {
		return fieldPossibleValue;
	}

	public void setFieldPossibleValue(FieldPossibleValues fieldPossibleValue) {
		this.fieldPossibleValue = fieldPossibleValue;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

}
