package com.tavant.kershaw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="section")
public class Section {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="section_id")
	private int sectionId;
	
	@Column(name="section_name")
	private String sectionName;
	
	@Column(name="section_desc")
	private String sectionDesc;
	
	@ManyToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name = "field_id")
	private Field fields;
	
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSectionDesc() {
		return sectionDesc;
	}
	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}
	public Field getFields() {
		return fields;
	}
	public void setFields(Field fields) {
		this.fields = fields;
	}
	
}
