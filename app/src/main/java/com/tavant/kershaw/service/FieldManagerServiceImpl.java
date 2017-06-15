package com.tavant.kershaw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.kershaw.dao.FieldManagerDAO;
import com.tavant.kershaw.helper.RequestData;

@Service
public class FieldManagerServiceImpl implements FieldManagerService{

	@Autowired
	private FieldManagerDAO fieldManagerDAO;
	
	public void createField(RequestData combinedData){
		fieldManagerDAO.createField(combinedData);
	}
}
