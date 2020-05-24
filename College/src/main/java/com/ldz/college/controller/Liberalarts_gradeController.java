package com.ldz.college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldz.college.biz.ILiberalarts_gradeBiz;

@RestController
@RequestMapping("/myurl")
public class Liberalarts_gradeController {
	@Autowired 
	private ILiberalarts_gradeBiz liberalarts_gradeBiz;
}
