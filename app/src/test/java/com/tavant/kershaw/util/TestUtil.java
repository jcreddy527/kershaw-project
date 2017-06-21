package com.tavant.kershaw.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.entity.DocumentTypeFieldMapping;
import com.tavant.kershaw.entity.Field;
import com.tavant.kershaw.entity.Section;
import com.tavant.kershaw.vo.DocumentTypeVO;
import com.tavant.kershaw.vo.FieldVO;
import com.tavant.kershaw.vo.SectionVO;

public class TestUtil {

	public static final String FIELD_NAME = "Field1";
	
	public static final String DOCUMENT_NAME = "Document Type1";
	
	public static final String SECTION_NAME = "Document Section 1";
	
	
	public static List<DocumentTypeVO> getAllDocumentTypeVOs(){
		List<DocumentTypeVO> docTypesList = new ArrayList<>();
		DocumentTypeVO docType = getDocumentTypeVO();
		docType.setFields(getFieldVOs());
		docTypesList.add(docType);
		return docTypesList;
	}
	
	
	public static DocumentTypeVO getDocumentTypeVO(){
		DocumentTypeVO docType = new DocumentTypeVO();
		docType.setDocumentTypeId(1);
		docType.setDocumentType(DOCUMENT_NAME);
		return docType;
	}
	
	public static FieldVO getFieldVO(){
		FieldVO field = new FieldVO();
		field.setFieldId(1);
		field.setFieldName(FIELD_NAME);
		return field;
	}
	
	public static Set<FieldVO> getFieldVOs(){
		Set<FieldVO> fields = new HashSet<>();
		fields.add(getFieldVO());
		return fields;
	}
	
	public static SectionVO getSectionVO(){
		SectionVO section = new SectionVO();
		section.setSectionId(1);
		section.setDocumentTypeId(1);
		section.setSectionName(SECTION_NAME);
		return section;
	}
	
	public static Set<SectionVO> getSectionVOs(){
		Set<SectionVO> sections = new HashSet<>();
		sections.add(getSectionVO());
		return sections;
	}
	
	public static DocumentType getDocumentType(){
		DocumentType docType = new DocumentType();
		docType.setDocumentTypeId(1);
		docType.setDocumentType(DOCUMENT_NAME);
		
		Field fd = new Field();
		fd.setFieldId(1);
		fd.setFieldName(FIELD_NAME);
		
		Set<Section> sections = new HashSet<>();
		fd.setSections(sections);
		
		DocumentTypeFieldMapping docFieldMapping = new DocumentTypeFieldMapping();
		docFieldMapping.setField(fd);
		docFieldMapping.setDocumentType(docType);
		Set<DocumentTypeFieldMapping> mappingList = new HashSet<>();
		mappingList.add(docFieldMapping);
		
		fd.setDocumentTypeField(mappingList);
		docType.setDocumentTypeField(mappingList);
		return docType;
	}
	
	public static  List<DocumentType> getAllDocumentTypes(){
		List<DocumentType> docTypesList = new ArrayList<>();
		docTypesList.add(getDocumentType());
		return docTypesList;
	}
	
	public static List<Section> getSections(){
		Section section1 = new Section();
		section1.setSectionId(1);
		section1.setSectionName("Document Section 1");
		
		Section section2 = new Section();
		section2.setSectionId(2);
		section2.setSectionName("Document Section 2");
		List<Section> sections = new ArrayList<>();
		sections.add(section1);
		sections.add(section2);
		
		return sections;
	}
	
}
