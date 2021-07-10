package com.honjal.honjal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honjal.honjal.model.ContentDTO;
import com.honjal.honjal.model.ContentListDTO;
import com.honjal.honjal.model.ContentVO;
import com.honjal.honjal.model.MemberVO;
import com.honjal.honjal.service.ContentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/tip")
@Controller
public class TipController {
	
	protected final ContentService contentService;
	
	@RequestMapping(value={"/",""}, method=RequestMethod.GET)
	public String main(Model model) {
		List<ContentListDTO> list = contentService.menuContent("TIP-1");
		model.addAttribute("CONTENTS", list);
		model.addAttribute("BODY", "BOARD_MAIN");
		model.addAttribute("MENU", "TIP");
		return "home";
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String read(String content_num, Model model) {
		ContentDTO contentDTO = contentService.findByIdContent(content_num);
		model.addAttribute("R",contentDTO);
		model.addAttribute("BODY", "READ");
		model.addAttribute("MENU", "TIP");
		return "home";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(String content_num, Model model) {
		ContentDTO contentDTO = contentService.findByIdContent(content_num);
		model.addAttribute("content_num", content_num);
		model.addAttribute("U",contentDTO);
		model.addAttribute("BODY", "UPDATE");
		model.addAttribute("MENU", "TIP");
		return "home";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(ContentVO contentVO, Model model) throws Exception {
		contentService.update(contentVO);
		model.addAttribute("R",contentVO);
		model.addAttribute("BODY", "READ");
		model.addAttribute("MENU", "TIP");
		return "home";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(Model model, HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute("MEMBER");
		
		ContentVO contentVO = ContentVO.builder().member_num(memberVO.getMember_num()).member_nname(memberVO.getMember_nname()).board_code("TIP-1").content_date(null).content_time(null).content_view(0).content_good(0).build();
		
		model.addAttribute("W",contentVO);
		model.addAttribute("BODY", "WRITE");
		model.addAttribute("MENU", "TIP");
		return "home";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session, ContentVO contentVO) throws Exception {
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");
		String curDate = sd.format(date);
		String curTime = st.format(date);
		
		contentVO = ContentVO.builder().content_date(curDate).content_time(curTime).build();
		
		contentService.insert(contentVO);
		return "redirect:/tip";
	}
	
	
	
}
