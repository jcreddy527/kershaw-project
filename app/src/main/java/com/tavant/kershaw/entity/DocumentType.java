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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "document_type")
public class DocumentType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "document_type_id")
	private int documentTypeId;

	@Column(name = "document_type")
	private String documentType;

	@Column(name = "document_description")
	private String documentDescription;


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "document_type_field_mapping", joinColumns = @JoinColumn(name = "document_type_id", referencedColumnName = "document_type_id"),
		inverseJoinColumns = @JoinColumn(name = "field_id", referencedColumnName = "field_id")) 
	private List<Field> documentField;

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

	public List<Field> getDocumentField() {
		return documentField;
	}

	public void setDocumentField(List<Field> documentField) {
		this.documentField = documentField;
	}

}
