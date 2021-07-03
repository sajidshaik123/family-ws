package com.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.service.FamilyDetailsService;

@RestController
@RequestMapping(value = { "/family" })
public class FamilyDetailsController {

	@Autowired
	FamilyDetailsService familyDetailsService;
	
	

}
