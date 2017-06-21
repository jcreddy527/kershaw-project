package com.tavant.kershaw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
import com.tavant.kershaw.util.TestUtil;
import com.tavant.kershaw.vo.DocumentTypeVO;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DocumentTypeController.class, secure = false)
public class DocumentTypeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DocumentTypeManagerService documentTypeManagerService;
	
	private ObjectMapper map = new ObjectMapper();
	
	
	@Test
	public void testGetDocumentTypesWithFields() throws Exception{
		Mockito.when(documentTypeManagerService.getAllDocumentTypes()).thenReturn(TestUtil.getAllDocumentTypes());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/documentType/withFields").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String response = result.getResponse().getContentAsString();//TODO : json conversion
		assertTrue(response.contains(TestUtil.DOCUMENT_NAME) && response.contains(TestUtil.FIELD_NAME));
	}
	
	@Test
	public void testCreateDocumentType() throws Exception{
		DocumentTypeVO docType = TestUtil.getDocumentTypeVO();
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
		Mockito.when(documentTypeManagerService.getDocumentTypesShallow()).thenReturn(TestUtil.getAllDocumentTypes());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/documentType/shallow").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String response = result.getResponse().getContentAsString();//TODO : json conversion
		assertTrue(response.contains(TestUtil.DOCUMENT_NAME));
	}
	
	@Test
	public void testGetDocumentTypeSections() throws Exception{
		Mockito.when(documentTypeManagerService.getSectionsByDocumentId(Mockito.anyInt())).thenReturn(new ArrayList<>(TestUtil.getSectionVOs()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/documentType/1/sections").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String response = result.getResponse().getContentAsString();//TODO : json conversion
		assertTrue(response.contains(TestUtil.SECTION_NAME));
	}
	
	@Test
	public void testCreateField() throws Exception{
		RequestData requestData = new RequestData();
		requestData.setDocumentTypeId(1);
		requestData.setFieldName(TestUtil.FIELD_NAME);
		requestData.setFieldPossibleValue("ABC/DEF");
		String requestJson = map.writeValueAsString(requestData);
		Mockito.doNothing().when(documentTypeManagerService).updateDocumentWithField(Matchers.any(RequestData.class));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/documentType/field")
				.accept(MediaType.APPLICATION_JSON).content(requestJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(result.getResponse().getStatus(),200);
	}

}
