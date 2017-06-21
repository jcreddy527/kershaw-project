package com.tavant.kershaw.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tavant.kershaw.dao.DocumentTypeManagerDAO;
import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentTypeManagerServiceTest {

	@Mock
	private DocumentTypeManagerDAO documentTypeManagerDAO;
	
	@Mock
	private DocumentTypeManagerService documentTypeManagerService;
	
	@Test
	public void testCreateDocumentType(){
		Mockito.when(documentTypeManagerDAO.createDocumentType(Mockito.any(DocumentType.class))).
			thenReturn(TestUtil.getDocumentType());
		documentTypeManagerService.createDocumentType(TestUtil.getDocumentType());
	}
}
