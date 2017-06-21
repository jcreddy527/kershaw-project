package com.tavant.kershaw.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tavant.kershaw.dao.DocumentTypeManagerDAO;
import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.entity.Field;
import com.tavant.kershaw.entity.FieldPossibleValues;
import com.tavant.kershaw.helper.RequestData;
import com.tavant.kershaw.util.TestUtil;
import com.tavant.kershaw.vo.DocumentTypeVO;
import com.tavant.kershaw.vo.SectionVO;

@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentTypeManagerServiceTest {

	@Mock
	private DocumentTypeManagerDAO documentTypeManagerDAO;
	
	@InjectMocks
	private DocumentTypeManagerServiceImpl documentTypeManagerService;
	
	@Test
	public void testCreateDocumentType(){
		Mockito.when(documentTypeManagerDAO.createDocumentType(Mockito.any(DocumentType.class))).
			thenReturn(TestUtil.getDocumentType());
		documentTypeManagerService.createDocumentType(TestUtil.getDocumentType());
	}
	
	@Test
	public void testGetAllDocumentTypes(){
		Mockito.when(documentTypeManagerDAO.getAllDocumentTypes()).thenReturn(TestUtil.getAllDocumentTypes());
		List<DocumentTypeVO> docTypes = documentTypeManagerService.getAllDocumentTypes();
		assertTrue(docTypes.size() == 1);
		assertTrue(docTypes.get(0).getDocumentTypeId() == 1);
		assertTrue(docTypes.get(0).getDocumentType() == TestUtil.DOCUMENT_NAME);
	}
	
	@Test
	public void testGetDocumentTypeShallow(){
		Mockito.when(documentTypeManagerDAO.getAllDocumentTypes()).thenReturn(TestUtil.getAllDocumentTypes());
		List<DocumentTypeVO> docTypes = documentTypeManagerService.getDocumentTypesShallow();
		assertTrue(docTypes.size() == 1);
		assertTrue(docTypes.get(0).getDocumentTypeId() == 1);
		assertTrue(docTypes.get(0).getFields()== null);
	}
	
	@Test
	public void testGetSectionsByDocumentId(){
		Mockito.when(documentTypeManagerDAO.getAllDocumentTypesById(Mockito.anyInt())).thenReturn(TestUtil.getDocumentType());
		Mockito.when(documentTypeManagerDAO.getFieldSections()).thenReturn(TestUtil.getSections());
		List<SectionVO> sections = documentTypeManagerService.getSectionsByDocumentId(1);
		assertTrue(sections.size() > 0);
		assertTrue(sections.get(0).getFields().size() > 0);
		
	}
}
