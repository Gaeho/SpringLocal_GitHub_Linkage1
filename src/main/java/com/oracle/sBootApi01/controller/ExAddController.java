package com.oracle.sBootApi01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "Ex")
public class ExAddController {
	
	@RequestMapping(value = "Ex.do")
	public int ExAdd() {
		
		int result = 1;
		
		return result;
	}

}
