package com.honjal.honjal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.honjal.honjal.model.ContentListDTO;
import com.honjal.honjal.service.ContentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {
	
	protected final ContentService contentService;
	
	@RequestMapping(value = {"/",""}, method = RequestMethod.GET)
	public String home(Model model,
						@RequestParam(value="pageNum", required=false, defaultValue="1") String pageNum) {
		
//		List<ContentListDTO> list = contentService.contentAll();
		int intPageNum = Integer.valueOf(pageNum);
		if(intPageNum > 0) {
			model.addAttribute("PAGE_NUM", intPageNum);
		}
		contentService.contentMenuAllPage("", intPageNum, model);
		
//		model.addAttribute("CONTENTS",list);
		return "home";
	}
	
	@RequestMapping(value = "/scrap", method = RequestMethod.GET)
	public String scrap(Model model) {
		
		model.addAttribute("BODY", "SCRAP");
		return "home";
	}
}