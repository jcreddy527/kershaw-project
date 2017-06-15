package com.tavant.kershaw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.kershaw.helper.RequestData;
import com.tavant.kershaw.service.FieldManagerService;

@RestController
@RequestMapping(value = "/field")
public class FieldController {

	@Autowired
	private FieldManagerService fieldManagerService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public RequestData createField(@RequestBody RequestData combinedData) {
		fieldManagerService.createField(combinedData);
		return combinedData;
	}
}
