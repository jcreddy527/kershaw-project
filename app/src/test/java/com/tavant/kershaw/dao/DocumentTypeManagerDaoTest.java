package com.tavant.kershaw.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.entity.Section;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DocumentTypeManagerDaoTest {
	

	@Autowired
	private DocumentTypeManagerDAO documentTypeManagerDAO;
	
	private static final String TEST_DOC_NAME = "Test document";
	
	private DocumentType documentType;
	
	@Test
	public void testCreateDocumentType(){
		DocumentType doc = new DocumentType();
		doc.setDocumentType(TEST_DOC_NAME);
		this.documentType = documentTypeManagerDAO.createDocumentType(doc);
		assertTrue(documentType != null);
		assertTrue(documentType.getDocumentType().equals(TEST_DOC_NAME));
		
		DocumentType docFromDB = documentTypeManagerDAO.getAllDocumentTypesById(documentType.getDocumentTypeId());
		assertTrue(docFromDB != null);
		assertTrue(docFromDB.getDocumentType().equals(TEST_DOC_NAME));
		
	}
	
	@Test
	public void testGetAllDocumentTypes(){
		List<DocumentType> docList = documentTypeManagerDAO.getAllDocumentTypes();
		assertTrue(docList.size() > 0);
	}
	
	@Test
	public void testGetFieldSections(){
		List<Section> sections = documentTypeManagerDAO.getFieldSections();
		assertTrue(sections.size() > 0);
	}
	
	/**
	 * TODO
	public Field saveField(Field fd);
	public void updateDocumentWithField(Integer docTypeId,Field field,String fieldValue);
	public void saveFieldPossibleValues(FieldPossibleValues fpv);
	public Field getField(Integer fieldId);
	public void updateDocumentFieldWithValue(Integer docTypeId,Field field,String fieldValue);
	 */

}
