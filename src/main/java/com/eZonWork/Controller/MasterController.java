package com.eZonWork.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eZonWork.Service.MasterService;

@RestController
@RequestMapping("/master")
public class MasterController {

	@Autowired
	private MasterService masterService;
	
	
	
	
	
	
	
	
}
