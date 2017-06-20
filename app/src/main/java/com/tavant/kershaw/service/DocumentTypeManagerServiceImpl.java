package com.tavant.kershaw.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tavant.kershaw.dao.DocumentTypeManagerDAO;
import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.entity.DocumentTypeFieldMapping;
import com.tavant.kershaw.entity.Field;
import com.tavant.kershaw.entity.FieldPossibleValues;
import com.tavant.kershaw.entity.Section;
import com.tavant.kershaw.helper.RequestData;
import com.tavant.kershaw.vo.DocumentTypeVO;
import com.tavant.kershaw.vo.FieldVO;
import com.tavant.kershaw.vo.SectionVO;

@Service
public class DocumentTypeManagerServiceImpl implements DocumentTypeManagerService{

	@Autowired
	private DocumentTypeManagerDAO documentTypeManagerDAO;
	
	public void createDocumentType(DocumentType documentType){
		documentTypeManagerDAO.createDocumentType(documentType);
	}

	@Override
	public List<DocumentTypeVO> getAllDocumentTypes() {
		List<DocumentType> dbDocs =  documentTypeManagerDAO.getAllDocumentTypes();
		List<DocumentTypeVO> responseList = new ArrayList<>();
		dbDocs.stream().forEach((record) -> {
			DocumentTypeVO dt = populateDocumentType(record);
			responseList.add(dt);
		});
		return responseList;
	}
	
	@Override
	public List<DocumentTypeVO> getDocumentTypesShallow() {
		List<DocumentType> dbDocs =  documentTypeManagerDAO.getAllDocumentTypes();
		List<DocumentTypeVO> responseList = new ArrayList<>();
		dbDocs.stream().forEach((record) -> {
			DocumentTypeVO dt = new DocumentTypeVO();
			dt.setDocumentTypeId(record.getDocumentTypeId());
			dt.setDocumentType(record.getDocumentType());
			dt.setDocumentDescription(record.getDocumentDescription());
			Map<String, SectionVO>  sectionMap = new HashMap<>();
			for(DocumentTypeFieldMapping fieldMapping : record.getDocumentTypeField()){
				for(Section section : fieldMapping.getField().getSections()){
					 if(!sectionMap.containsKey(section.getSectionName())){
						 SectionVO sectionVO = new SectionVO();
						 sectionVO.setSectionName(section.getSectionName());
						 sectionVO.setSectionId(section.getSectionId());
						 sectionMap.put(section.getSectionName(), sectionVO);
					 }
				}
			}
			dt.setSections(new ArrayList<>(sectionMap.values()));
			responseList.add(dt);
		});
		
		return responseList;
	}

	private DocumentTypeVO populateDocumentType(DocumentType record) {
		DocumentTypeVO dt = new DocumentTypeVO();
		dt.setDocumentTypeId(record.getDocumentTypeId());
		dt.setDocumentType(record.getDocumentType());
		dt.setDocumentDescription(record.getDocumentDescription());
		Set<DocumentTypeFieldMapping> fieldMappings = record.getDocumentTypeField();
		Set<FieldVO> fields = new HashSet<>();
		for(DocumentTypeFieldMapping fieldMapping : fieldMappings){
			FieldVO field = new FieldVO();
			field.setDataType(fieldMapping.getField().getDataType());
			field.setFieldId(fieldMapping.getField().getFieldId());
			field.setFieldName(fieldMapping.getField().getFieldName());
			field.setSections(fieldMapping.getField().getSections());
			field.setFieldPossibleValues(fieldMapping.getField().getFieldPossibleValues());
			field.setFieldValue(fieldMapping.getFieldValue());
			fields.add(field);
		}
		dt.setFields(fields);
		return dt;
	}
	
	@Override
	public List<SectionVO> getSectionsByDocumentId(Integer documentId) {
		DocumentType document =  documentTypeManagerDAO.getAllDocumentTypesById(documentId);
		return getFieldsGroupBySection(document);
	}
	
	private List<SectionVO> getFieldsGroupBySection(DocumentType record) {
		Map<String,SectionVO> fieldsBySection = new HashMap<>();
		for (Section section : documentTypeManagerDAO.getFieldSections()){
			SectionVO secVO = new SectionVO();
			//secVO.setDocumentId(record.getDocumentTypeId());
			secVO.setSectionName(section.getSectionName());
			secVO.setSectionId(section.getSectionId());
			fieldsBySection.put(section.getSectionName(), secVO);
		}
		
		DocumentTypeVO dt = new DocumentTypeVO();
		dt.setDocumentTypeId(record.getDocumentTypeId());
		Set<DocumentTypeFieldMapping> fieldMappings = record.getDocumentTypeField();
		for(DocumentTypeFieldMapping fieldMapping : fieldMappings){
			FieldVO field = new FieldVO();
			field.setDataType(fieldMapping.getField().getDataType());
			field.setFieldName(fieldMapping.getField().getFieldName());
			field.setFieldId(fieldMapping.getField().getFieldId());
			field.setFieldPossibleValues(fieldMapping.getField().getFieldPossibleValues());
			field.setFieldValue(fieldMapping.getFieldValue());
			
			for(Section section : fieldMapping.getField().getSections()){
				fieldsBySection.get(section.getSectionName()).getFields().add(field);
			}
		}
		return new ArrayList<>(fieldsBySection.values());
	}
	
	@Override
	public void updateDocumentWithFields(List<RequestData> requestData){
		for(RequestData data : requestData){
			updateDocumentWithField(data);
		}
	}
	
	@Override
	public void updateDocumentWithField(RequestData requestData) {

		Field fieldToSave = prepareFieldEntity(requestData);
		Field savedField = documentTypeManagerDAO.saveField(fieldToSave);

		documentTypeManagerDAO.updateDocumentWithField(requestData.getDocumentTypeId(), savedField,
				requestData.getFieldValue());
		
		saveFieldPossibleValues(requestData, savedField);
	}

	private void saveFieldPossibleValues(RequestData requestData, Field savedField) {
		if(!StringUtils.isEmpty(requestData.getFieldPossibleValue())){
			String fieldPossibleValues = requestData.getFieldPossibleValue();
			List<String> fieldValuesArray = Arrays.asList(fieldPossibleValues.split("/"));
			for(String fieldValue: fieldValuesArray){
				FieldPossibleValues fpv = new FieldPossibleValues();
				fpv.setFieldValue(fieldValue);
				fpv.setFieldId(savedField.getFieldId());
				documentTypeManagerDAO.saveFieldPossibleValues(fpv);
			}
		}
	}

	private Field prepareFieldEntity(RequestData requestData) {
		Field fd = new Field();
		fd.setFieldName(requestData.getFieldName());
		fd.setDataType(requestData.getDataType());
		
		List<Section> dbSections = documentTypeManagerDAO.getFieldSections();
		Set<Section> sectionsToSave = new HashSet<Section>();
		for(Section sec : dbSections){
			if(sec.getSectionName().equalsIgnoreCase(requestData.getSectionName())){
				sectionsToSave.add(sec);
			}
		}
		
		fd.setSections(sectionsToSave);
		return fd;
	}
}
