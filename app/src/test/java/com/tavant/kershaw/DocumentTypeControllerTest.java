package com.tavant.kershaw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavant.kershaw.controllers.DocumentTypeController;
import com.tavant.kershaw.entity.DocumentType;
import com.tavant.kershaw.helper.RequestData;
import com.tavant.kershaw.service.DocumentTypeManagerService;
import com.tavant.kershaw.vo.DocumentTypeVO;
import com.tavant.kershaw.vo.FieldVO;
import com.tavant.kershaw.vo.SectionVO;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DocumentTypeController.class, secure = false)
public class DocumentTypeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DocumentTypeManagerService documentTypeManagerService;
	
	private ObjectMapper map = new ObjectMapper();
	
	private  List<DocumentTypeVO> docTypesList = new ArrayList<>();
	
	private List<SectionVO> sections = new ArrayList<>();
	
	private static final String FIELD_NAME = "Field1";
	
	private static final String DOCUMENT_NAME = "Document Type1";
	
	private static final String SECTION_NAME = "Document Section 1";
	
	@Before
	public  void setup(){
		DocumentTypeVO docType = new DocumentTypeVO();
		docType.setDocumentTypeId(1);
		docType.setDocumentType(DOCUMENT_NAME);
		FieldVO field = new FieldVO();
		field.setFieldId(1);
		field.setFieldName(FIELD_NAME);
		Set<FieldVO> fields = new HashSet<>();
		fields.add(field);
		docType.setFields(fields);
		docTypesList.add(docType);
		
		SectionVO section = new SectionVO();
		section.setSectionId(1);
		section.setDocumentTypeId(1);
		section.setSectionName(SECTION_NAME);
		section.getFields().addAll(fields);
		sections.add(section);
		
	}
	
	
	@Test
	public void testGetDocumentTypesWithFields() throws Exception{
		Mockito.when(documentTypeManagerService.getAllDocumentTypes()).thenReturn(docTypesList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/documentType/withFields").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String response = result.getResponse().getContentAsString();//TODO : json conversion
		assertTrue(response.contains(DOCUMENT_NAME) && response.contains(FIELD_NAME));
	}
	
	@Test
	public void testCreateDocumentType() throws Exception{
		DocumentTypeVO docType = docTypesList.get(0);
		String requestJson = map.writeValueAsString(docType);
		Mockito.doNothing().when(documentTypeManagerService).createDocumentType(Matchers.any(DocumentType.class));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/documentType/create")
				.accept(MediaType.APPLICATION_JSON).content(requestJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(result.getResponse().getStatus(),200);
	}
	
	@Test
	public void testGetDocumentTypesShallow() throws Exception{
		Mockito.when(documentTypeManagerService.getDocumentTypesShallow()).thenReturn(docTypesList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/documentType/shallow").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String response = result.getResponse().getContentAsString();//TODO : json conversion
		assertTrue(response.contains(DOCUMENT_NAME));
	}
	
	@Test
	public void testGetDocumentTypeSections() throws Exception{
		Mockito.when(documentTypeManagerService.getSectionsByDocumentId(Mockito.anyInt())).thenReturn(sections);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/documentType/sections/1").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String response = result.getResponse().getContentAsString();//TODO : json conversion
		assertTrue(response.contains(SECTION_NAME) && response.contains(FIELD_NAME));
	}
	
	@Test
	public void testCreateField() throws Exception{
		RequestData requestData = new RequestData();
		requestData.setDocumentTypeId(1);
		requestData.setFieldName(FIELD_NAME);
		requestData.setFieldPossibleValue("ABC/DEF");
		String requestJson = map.writeValueAsString(requestData);
		Mockito.doNothing().when(documentTypeManagerService).updateDocumentWithField(Matchers.any(RequestData.class));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/documentType/create/field")
				.accept(MediaType.APPLICATION_JSON).content(requestJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(result.getResponse().getStatus(),200);
	}

}
